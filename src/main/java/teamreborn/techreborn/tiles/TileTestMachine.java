package teamreborn.techreborn.tiles;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import reborncore.api.rcpower.implementation.BasePowerContainer;
import reborncore.common.capabilitys.PowerCapabilities;
import reborncore.common.logic.LogicControllerRegistry;
import reborncore.common.registration.RebornRegistry;
import teamreborn.techreborn.TechReborn;

import java.util.List;

/**
 * Created by Gigabit101 on 09/04/2017.
 */
@RebornRegistry(modID = TechReborn.MOD_ID)
public class TileTestMachine extends TileBaseMachine {
	@LogicControllerRegistry(name = "techreborn:test_machine")
	public static TileTestMachine machine;

	@Override
	public int getInvSize() {
		return 0;
	}

	@Override
	public List<Slot> getSlots() {
		return null;
	}

	@Override
	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY, GuiContainer gui, int guiLeft, int guiTop) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY, gui, guiLeft, guiTop);
		final BasePowerContainer container = (BasePowerContainer) world.getTileEntity(pos).getCapability(PowerCapabilities.CAPABILITY_HOLDER, null);
		getBuilder().drawEnergyBar(gui, 20, 20, 50, container.getStored(), container.getCapacity(), mouseX - guiLeft, mouseY - guiTop, "RC");
	}
}
