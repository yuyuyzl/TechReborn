package teamreborn.techreborn.tiles.machines;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import teamreborn.techreborn.api.power.implementation.BasePowerContainer;
import teamreborn.techreborn.capabilitys.PowerCapabilities;
import teamreborn.techreborn.tiles.AdvancedTile;

import java.util.List;

/**
 * Created by Gigabit101 on 08/04/2017.
 */
public class TileElectricFurnace extends AdvancedTile
{
    @Override
    public String getName()
    {
        return "electric_furnace";
    }

    @Override
    public List<Slot> getSlots()
    {
        return null;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileElectricFurnace();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        openGui(playerIn, (TileElectricFurnace)worldIn.getTileEntity(pos));
        return true;
    }

    @Override
    public int getInvSize()
    {
        return 0;
    }

    @Override
    public boolean needsPower()
    {
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
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        if(capability == PowerCapabilities.CAPABILITY_CONSUMER)
        {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        if(capability == PowerCapabilities.CAPABILITY_CONSUMER)
        {
            return PowerCapabilities.CAPABILITY_CONSUMER.cast(getPowerContainer());
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public int getMaxOutput()
    {
        return 20;
    }

    @Override
    public int getMaxInput()
    {
        return 20;
    }

    @Override
    public int getMaxCapacity()
    {
        return 10000;
    }
}
