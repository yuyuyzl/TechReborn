package teamreborn.techreborn;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Gigabit101 on 08/04/2017.
 */
@Mod(name = TechReborn.MOD_NAME, modid = TechReborn.MOD_ID, version = TechReborn.MOD_VERSION)
public class TechReborn
{
    public static final String MOD_NAME = "TechReborn";
    public static final String MOD_ID = "techreborn";
    public static final String MOD_VERSION = "";

    @Mod.Instance
    public static TechReborn INSTANCE;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {}
}
