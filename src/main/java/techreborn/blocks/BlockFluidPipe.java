package techreborn.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import reborncore.common.blocks.BlockMachineBase;

import techreborn.client.TechRebornCreativeTab;
import techreborn.grid.GridManager;
import techreborn.tiles.TileFluidPipe;

public class BlockFluidPipe extends BlockMachineBase {

	public BlockFluidPipe() {
		this.setUnlocalizedName("techreborn.fluidpipe");
		this.setCreativeTab(TechRebornCreativeTab.instance);
	}

	@Override
	public void neighborChanged(final IBlockState state, final World w, final BlockPos pos, final Block block,
			final BlockPos posNeighbor) {
	}

	@Override
	public boolean onBlockActivated(final World w, final BlockPos pos, final IBlockState state,
			final EntityPlayer player, final EnumHand hand, final EnumFacing side, final float hitX, final float hitY,
			final float hitZ) {
		if (!w.isRemote)
			System.out.println(((TileFluidPipe) w.getTileEntity(pos)).getGrid());
		return false;
	}

	@Override
	public void onBlockPlacedBy(final World w, final BlockPos pos, final IBlockState state,
			final EntityLivingBase placer, final ItemStack stack) {
		super.onBlockPlacedBy(w, pos, state, placer, stack);

		if (!w.isRemote)
			GridManager.getInstance().connectCable((TileFluidPipe) w.getTileEntity(pos));
	}

	@Override
	public void breakBlock(final World w, final BlockPos pos, final IBlockState state) {
		if (!w.isRemote)
			GridManager.getInstance().disconnectCable((TileFluidPipe) w.getTileEntity(pos));

		super.breakBlock(w, pos, state);
	}

	@Override
	public TileEntity createTileEntity(final World w, final IBlockState state) {
		return new TileFluidPipe();
	}
}
