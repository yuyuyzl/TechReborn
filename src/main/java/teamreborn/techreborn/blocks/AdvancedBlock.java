package teamreborn.techreborn.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import teamreborn.techreborn.tiles.AdvancedTile;

import javax.annotation.Nullable;

/**
 * Created by Gigabit101 on 08/04/2017.
 */
public class AdvancedBlock extends BlockContainer
{
    AdvancedTile advancedTile;

    public AdvancedBlock(AdvancedTile advancedTile)
    {
        super(Material.IRON);
        this.advancedTile = advancedTile;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        if(advancedTile != null)
        {
            return advancedTile.createNewTileEntity(worldIn, meta);
        }
        return null;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(advancedTile != null)
        {
            return advancedTile.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        if(advancedTile != null)
        {
            return advancedTile.getRenderType(state);
        }
        return super.getRenderType(state);
    }
}
