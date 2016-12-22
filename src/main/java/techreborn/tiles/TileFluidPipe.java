package techreborn.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

import reborncore.api.IListInfoProvider;
import reborncore.common.tile.TileLegacyMachineBase;

import techreborn.grid.GridManager;
import techreborn.grid.IFluidPipe;
import techreborn.grid.ITileCable;
import techreborn.grid.PipeGrid;

import java.util.EnumMap;
import java.util.List;

public class TileFluidPipe extends TileLegacyMachineBase implements IListInfoProvider, IFluidPipe {

	private int grid;
	private final EnumMap<EnumFacing, ITileCable> connections;
	private final EnumMap<EnumFacing, IFluidHandler> adjacentFluidHandler;

	public TileFluidPipe() {
		this.connections = new EnumMap<>(EnumFacing.class);
		this.adjacentFluidHandler = new EnumMap<>(EnumFacing.class);
		this.grid = -1;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if (!this.world.isRemote && this.getGridObject() != null)
			this.getGridObject().tick();
	}

	@Override
	public boolean hasCapability(final Capability<?> capability, final EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			return true;
		}
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(final Capability<T> capability, final EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			return (T) this.getGridObject().getTank();
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public void addInfo(final List<String> list, final boolean arg1) {

		list.add("Contains: " + this.getGridObject().getTank().getFluidType().getName());
		list.add("Buffer: " + this.getGridObject().getTank().getFluidAmount() + " / "
				+ this.getGridObject().getTank().getCapacity() + " mb");
		list.add("Grid: " + this.grid);
		this.connections.forEach((facing, cable) -> list.add(facing + ": " + (cable != null)));
	}

	@Override
	public EnumFacing[] getConnections() {
		return this.connections.keySet().toArray(new EnumFacing[0]);
	}

	@Override
	public ITileCable getConnected(final EnumFacing facing) {
		return this.connections.get(facing);
	}

	@Override
	public int getGrid() {
		return this.grid;
	}

	public PipeGrid getGridObject() {
		return (PipeGrid) GridManager.getInstance().getGrid(this.getGrid());
	}

	@Override
	public void setGrid(final int gridIdentifier) {
		this.grid = gridIdentifier;
	}

	@Override
	public void readFromNBT(final NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);

		for (final EnumFacing facing : EnumFacing.VALUES) {
			if (tagCompound.hasKey("connection:" + facing)) {
				if (tagCompound.getBoolean("connection:" + facing)) {
					final TileEntity tile = this.world.getTileEntity(this.getPos().add(facing.getDirectionVec()));
					if (tile instanceof ITileCable)
						this.connections.put(facing, (ITileCable) tile);
				}
			}
		}
	}

	@Override
	public NBTTagCompound writeToNBT(final NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);
		this.connections.forEach((facing, cable) -> tagCompound.setBoolean("connection:" + facing, cable != null));
		return tagCompound;
	}

	@Override
	public void onDataPacket(final NetworkManager net, final SPacketUpdateTileEntity packet) {
		this.world.markBlockRangeForRenderUpdate(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(),
				this.getPos().getX(), this.getPos().getY(), this.getPos().getZ());
		this.readFromNBT(packet.getNbtCompound());
	}

	@Override
	public void onChunkUnload() {
		GridManager.getInstance().disconnectCable(this);
	}

	@Override
	public void connect(final EnumFacing facing, final ITileCable to) {
		this.connections.put(facing, to);
	}

	@Override
	public void disconnect(final EnumFacing facing) {
		this.connections.remove(facing);
	}

	public void scanFluidHandlers(final BlockPos posNeighbor) {
		final TileEntity tile = this.world.getTileEntity(posNeighbor);

		final BlockPos substract = posNeighbor.subtract(this.pos);
		final EnumFacing facing = EnumFacing.getFacingFromVector(substract.getX(), substract.getY(), substract.getZ())
				.getOpposite();

		if (this.adjacentFluidHandler.containsKey(facing.getOpposite())) {
			if (tile == null || !tile.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, facing)) {
				this.adjacentFluidHandler.remove(facing.getOpposite());
				if (this.adjacentFluidHandler.isEmpty())
					this.getGridObject().removeInput(this);
			}
		} else {
			if (tile != null) {
				if (tile.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, facing)) {
					System.out.println("FLUID EXTRACT " + facing);

					this.adjacentFluidHandler.put(facing.getOpposite(),
							tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, facing));
					this.getGridObject().addInput(this);
					System.out.println("FLUID APPROVED " + facing);
				}
			}
		}
	}

	@Override
	public void drainNeighbors() {

		for (final IFluidHandler fluidHandler : this.adjacentFluidHandler.values()) {

			final FluidStack simulated = fluidHandler
					.drain(this.getGridObject().getCapacity() - this.getGridObject().getTank().getFluidAmount(), false);
			System.out.println(this.getGridObject().getTank().getFluidAmount());
			if (simulated != null && simulated.getFluid() != null && simulated.amount > 0
					&& (simulated.getFluid().equals(this.getGridObject().getFluid())
							|| this.getGridObject().getFluid() == null)) {

				this.getGridObject().getTank()
						.fill(fluidHandler.drain(
								this.getGridObject().getCapacity() - this.getGridObject().getTank().getFluidAmount(),
								true), true);
			}
		}
	}
}
