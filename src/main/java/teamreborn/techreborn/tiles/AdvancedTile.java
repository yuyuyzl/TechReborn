package teamreborn.techreborn.tiles;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import scala.tools.nsc.interpreter.Power;
import teamreborn.techreborn.TechReborn;
import teamreborn.techreborn.api.power.implementation.BasePowerContainer;
import teamreborn.techreborn.capabilitys.PowerCapabilities;
import teamreborn.techreborn.client.TRGuiBuilder;
import teamreborn.techreborn.client.guis.AdvancedGui;
import teamreborn.techreborn.reborncore.VanillaPacketDispatcher;

import java.util.List;

/**
 * Created by Gigabit101 on 08/04/2017.
 */
public abstract class AdvancedTile extends TileEntity
{
    public abstract String getName();

    public TRGuiBuilder builder = new TRGuiBuilder();

    @SideOnly(Side.CLIENT)
    public TRGuiBuilder getBuilder()
    {
        return builder;
    }

    public int getXSize()
    {
        return 176;
    }

    public int getYsize()
    {
        return 176;
    }

    @SideOnly(Side.CLIENT)
    public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY, int guiLeft, int guiTop, int xSize, int ySize, AdvancedGui gui)
    {
        getBuilder().drawDefaultBackground(gui, guiLeft, guiTop, xSize, ySize);
        getBuilder().drawPlayerSlots(gui, guiLeft + xSize / 2, guiTop + 86, true);
        if (getSlots() != null)
        {
            for (Slot s : getSlots())
            {
                getBuilder().drawSlot(gui, guiLeft + s.xPos - 1, guiTop + s.yPos - 1);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void drawGuiContainerForegroundLayer(int mouseX, int mouseY, GuiContainer gui, int guiLeft, int guiTop) {}

    //Container
    public abstract List<Slot> getSlots();

    public int inventoryOffsetX()
    {
        return 8;
    }

    public int inventoryOffsetY()
    {
        return 87;
    }

    //Block
    public abstract TileEntity createNewTileEntity(World world, int meta);

    public abstract boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ);

    public void openGui(EntityPlayer player, AdvancedTile machine)
    {
        if (!player.isSneaking())
        {
            player.openGui(TechReborn.INSTANCE, 0, machine.world, machine.pos.getX(), machine.pos.getY(), machine.pos.getZ());
        }
    }

    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    //Inv
    public ItemStackHandler inv = new ItemStackHandler(getInvSize());

    public abstract int getInvSize();

    public boolean hasInv()
    {
        if (getInvSize() != 0)
        {
            return true;
        }
        return false;
    }

    public ItemStackHandler getInv()
    {
        return this.inv;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        if(hasInv() && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY  || needsPower() && capability == PowerCapabilities.CAPABILITY_HOLDER)
        {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        if(hasInv() && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inv);
        }
        if(needsPower() && capability == PowerCapabilities.CAPABILITY_HOLDER)
        {
            return PowerCapabilities.CAPABILITY_HOLDER.cast(powerContainer);
        }
        return super.getCapability(capability, facing);
    }

    public abstract boolean needsPower();

    //Power
    public BasePowerContainer powerContainer = new BasePowerContainer(getMaxCapacity(), getMaxInput(), getMaxOutput());

    public BasePowerContainer getPowerContainer()
    {
        return powerContainer;
    }

    public int getMaxCapacity()
    {
        return 0;
    }

    public int getMaxInput()
    {
        return 0;
    }

    public int getMaxOutput()
    {
        return 0;
    }

    //NBT
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        compound = super.writeToNBT(compound);
        compound.merge(getInv().serializeNBT());
        compound.merge(getPowerContainer().serializeNBT());
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        getInv().deserializeNBT(compound);
        getPowerContainer().deserializeNBT(compound);
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        return new SPacketUpdateTileEntity(this.pos, 0, this.getUpdateTag());
    }

    @Override
    public NBTTagCompound getUpdateTag()
    {
        return this.writeToNBT(new NBTTagCompound());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet)
    {
        super.onDataPacket(net, packet);
        this.readFromNBT(packet.getNbtCompound());
    }

    public void sync()
    {
        VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
    }
}
