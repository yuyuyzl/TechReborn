package teamreborn.techreborn;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import teamreborn.techreborn.client.guis.AdvancedGui;
import teamreborn.techreborn.inventorys.AdvancedContainer;
import teamreborn.techreborn.tiles.AdvancedTile;

/**
 * Created by Gigabit101 on 08/04/2017.
 */
public class GuiHandler implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(world.getTileEntity(new BlockPos(x, y, z)) != null && world.getTileEntity(new BlockPos(x, y, z)) instanceof AdvancedTile)
        {
            AdvancedTile machine = (AdvancedTile) world.getTileEntity(new BlockPos(x, y, z));
            return new AdvancedContainer(player, machine);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(world.getTileEntity(new BlockPos(x, y, z)) != null && world.getTileEntity(new BlockPos(x, y, z)) instanceof AdvancedTile)
        {
            AdvancedTile machine = (AdvancedTile) world.getTileEntity(new BlockPos(x, y, z));
            return new AdvancedGui(player, machine);
        }
        return null;
    }
}
