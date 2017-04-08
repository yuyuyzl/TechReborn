package teamreborn.techreborn.tiles.generators;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import teamreborn.techreborn.api.power.PowerUtils;
import teamreborn.techreborn.capabilitys.PowerCapabilities;
import teamreborn.techreborn.tiles.AdvancedTile;

import java.util.List;

/**
 * Created by Gigabit101 on 08/04/2017.
 */
public class TileCreative extends AdvancedTile implements ITickable
{
    @Override
    public String getName()
    {
        return "creative";
    }

    @Override
    public List<Slot> getSlots()
    {
        return null;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileCreative();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        return false;
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
    public void update()
    {
        PowerUtils.distributePowerToAllFaces(world, pos, 50, false);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        if(capability == PowerCapabilities.CAPABILITY_PRODUCER)
        {
            return PowerCapabilities.CAPABILITY_PRODUCER.cast(getPowerContainer());
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        if(capability == PowerCapabilities.CAPABILITY_PRODUCER)
        {
            return true;
        }
        return super.hasCapability(capability, facing);
    }
}
