package teamreborn.techreborn;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import teamreborn.techreborn.gui.WrenchGuiHandler;

/**
 * Created by Gigabit101 on 08/04/2017.
 */
@Mod(name = TechReborn.MOD_NAME, modid = TechReborn.MOD_ID, version = TechReborn.MOD_VERSION)
public class TechReborn {
	public static final String MOD_NAME = "Tech Reborn Test";
	public static final String MOD_ID = "techreborn";
	public static final String MOD_VERSION = "";
	@Mod.Instance
	public static TechReborn INSTANCE;
	public static Item wrench;

	@Mod.EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		wrench = new Item().setUnlocalizedName(MOD_ID + ":wrench").setRegistryName(MOD_ID, "wrench").setCreativeTab(CreativeTabTR.INSTANCE);
		GameRegistry.register(wrench);
		NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, new WrenchGuiHandler());
	}
}
