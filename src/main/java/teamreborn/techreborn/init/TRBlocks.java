package teamreborn.techreborn.init;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;
import reborncore.RebornRegistry;
import teamreborn.techreborn.TechReborn;
import teamreborn.techreborn.blocks.AdvancedBlock;
import teamreborn.techreborn.tiles.AdvancedTile;
import teamreborn.techreborn.tiles.generators.TileCoalGenerator;
import teamreborn.techreborn.tiles.generators.TileCreative;
import teamreborn.techreborn.tiles.machines.TileElectricFurnace;

/**
 * Created by Gigabit101 on 08/04/2017.
 */
public class TRBlocks
{
    public static Block COAL_GENERATOR;
    public static Block CREATIVE_GENERATOR;
    public static Block ELECTRIC_FURNACE;

    public static void init()
    {
        COAL_GENERATOR = new AdvancedBlock(new TileCoalGenerator()).setUnlocalizedName(TechReborn.MOD_ID + ".coal_generator");
        registerAdvanced(COAL_GENERATOR, new TileCoalGenerator());

        CREATIVE_GENERATOR = new AdvancedBlock(new TileCreative()).setUnlocalizedName(TechReborn.MOD_ID + ".creative_generator");
        registerAdvanced(CREATIVE_GENERATOR, new TileCreative());

        ELECTRIC_FURNACE = new AdvancedBlock(new TileElectricFurnace()).setUnlocalizedName(TechReborn.MOD_ID + ".electric_furnace");
        registerAdvanced(ELECTRIC_FURNACE, new TileElectricFurnace());
    }

    static void registerAdvanced(Block block, AdvancedTile advancedTileEntity)
    {
        RebornRegistry.registerBlock(block, advancedTileEntity.getName());
        GameRegistry.registerTileEntity(advancedTileEntity.getClass(), advancedTileEntity.getName());
    }
}
