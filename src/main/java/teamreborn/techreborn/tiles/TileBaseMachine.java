package teamreborn.techreborn.tiles;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import reborncore.common.logic.LogicBlock;
import reborncore.common.logic.LogicController;
import teamreborn.techreborn.CreativeTabTR;
import teamreborn.techreborn.TechReborn;

/**
 * Created by Gigabit101 on 09/04/2017.
 */

public abstract class TileBaseMachine extends LogicController implements ITickable {
	@Override
	public void update() {
		sync();
	}

	@Override
	public void initBlock(LogicBlock block) {
		super.initBlock(block);
		block.setCreativeTab(CreativeTabTR.INSTANCE);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (playerIn.getHeldItem(hand).getItem() == TechReborn.wrench) {
			LogicController machine = (LogicController) worldIn.getTileEntity(pos);
			playerIn.openGui(TechReborn.INSTANCE, 0, machine.getWorld(), machine.getPos().getX(), machine.getPos().getY(), machine.getPos().getZ());
			return true;
		}
		if (!hasGui()) {
			return false;
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, side, hitX, hitY, hitZ);
	}

	public boolean hasGui() {
		return true;
	}
}
