package teamreborn.techreborn;

import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import teamreborn.techreborn.api.power.IPowerConsumer;
import teamreborn.techreborn.api.power.IPowerHolder;
import teamreborn.techreborn.api.power.IPowerProducer;
import teamreborn.techreborn.api.power.implementation.BasePowerContainer;
import teamreborn.techreborn.capabilitys.PowerCapabilities;
import teamreborn.techreborn.init.TRBlocks;

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
    public void preinit(FMLPreInitializationEvent event)
    {
        registerCapabilitys();

        TRBlocks.init();

        NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, new GuiHandler());
    }

    private void registerCapabilitys()
    {
        CapabilityManager.INSTANCE.register(IPowerConsumer.class, new PowerCapabilities.CapabilityPowerConsumer<IPowerConsumer>(), BasePowerContainer.class);
        CapabilityManager.INSTANCE.register(IPowerProducer.class, new PowerCapabilities.CapabilityPowerProducer<IPowerProducer>(), BasePowerContainer.class);
        CapabilityManager.INSTANCE.register(IPowerHolder.class, new PowerCapabilities.CapabilityPowerHolder<IPowerHolder>(), BasePowerContainer.class);
    }
}
