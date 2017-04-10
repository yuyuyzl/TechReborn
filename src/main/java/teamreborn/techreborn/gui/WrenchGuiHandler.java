package teamreborn.techreborn.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import reborncore.common.logic.LogicContainer;
import reborncore.common.logic.LogicController;

import javax.annotation.Nullable;

/**
 * Created by Prospector
 */
public class WrenchGuiHandler implements IGuiHandler {
	@Nullable
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (world.getTileEntity(new BlockPos(x, y, z)) != null && world.getTileEntity(new BlockPos(x, y, z)) instanceof LogicController) {
			LogicController machine = (LogicController) world.getTileEntity(new BlockPos(x, y, z));
			return new LogicContainer(player, machine);
		}
		return null;
	}

	@Nullable
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (world.getTileEntity(new BlockPos(x, y, z)) != null && world.getTileEntity(new BlockPos(x, y, z)) instanceof LogicController) {
			LogicController machine = (LogicController) world.getTileEntity(new BlockPos(x, y, z));
			return new GuiWrench(player, machine);
		}
		return null;
	}
}
