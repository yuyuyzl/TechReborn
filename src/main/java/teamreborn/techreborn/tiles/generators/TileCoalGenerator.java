package teamreborn.techreborn.tiles.generators;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.SlotItemHandler;
import teamreborn.techreborn.api.power.implementation.BasePowerContainer;
import teamreborn.techreborn.capabilitys.PowerCapabilities;
import teamreborn.techreborn.tiles.AdvancedTile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gigabit101 on 08/04/2017.
 */
public class TileCoalGenerator extends AdvancedTile implements ITickable
{
    @Override
    public void update()
    {
        sync();
    }

    @Override
    public String getName()
    {
        return "coal_generator";
    }

    @Override
    public List<Slot> getSlots()
    {
        List<Slot> slots = new ArrayList<>();
        slots.add(new SlotItemHandler(inv, 0, 80, 60));
        return slots;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileCoalGenerator();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        openGui(playerIn, (TileCoalGenerator) worldIn.getTileEntity(pos));
        return true;
    }

    @Override
    public void drawGuiContainerForegroundLayer(int mouseX, int mouseY, GuiContainer gui, int guiLeft, int guiTop)
    {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY, gui, guiLeft, guiTop);
        final BasePowerContainer container = (BasePowerContainer) world.getTileEntity(pos).getCapability(PowerCapabilities.CAPABILITY_CONSUMER, null);
        getBuilder().drawEnergyBar(gui, 10, 10, 50, container.getStored(), container.getCapacity(), mouseX - guiLeft, mouseY - guiTop, "TR");
    }

    @Override
    public int getInvSize()
    {
        return 1;
    }

    @Override
    public boolean needsPower()
    {
        return true;
    }

    @Override
    public int getMaxCapacity()
    {
        return 25000;
    }

    @Override
    public int getMaxInput()
    {
        return 20;
    }

    @Override
    public int getMaxOutput()
    {
        return 20;
    }
}
