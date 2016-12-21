package techreborn.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

import reborncore.api.IListInfoProvider;
import reborncore.common.tile.TileLegacyMachineBase;

import techreborn.grid.GridManager;
import techreborn.grid.ITileCable;

import java.util.EnumMap;
import java.util.List;

public class TileFluidPipe extends TileLegacyMachineBase implements IListInfoProvider, ITileCable {

	private int grid;
	private final EnumMap<EnumFacing, ITileCable> connections;

	public TileFluidPipe() {
		this.connections = new EnumMap<>(EnumFacing.class);
		this.grid = -1;
	}

	@Override
	public void addInfo(final List<String> list, final boolean arg1) {
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

	@Override
	public void setGrid(final int gridIdentifier) {
		this.grid = gridIdentifier;
	}

	@Override
	public boolean isInput() {
		return false;
	}

	@Override
	public boolean isOutput() {
		return false;
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
}
