package teamreborn.techreborn.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import reborncore.client.guibuilder.GuiBuilder;
import reborncore.common.logic.LogicContainer;
import reborncore.common.logic.LogicController;

import javax.annotation.Nonnull;

/**
 * Created by Prospector
 */
public class GuiWrench extends GuiContainer {

	public GuiBuilder builder = new GuiBuilder(GuiBuilder.defaultTextureSheet);
	@Nonnull
	EntityPlayer player;
	@Nonnull
	LogicController logicController;

	public GuiWrench(EntityPlayer player, LogicController logicController) {
		super(new LogicContainer(player, logicController));
		this.player = player;
		this.logicController = logicController;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		builder.drawDefaultBackground(this, guiLeft, guiTop, xSize, ySize);
		if (logicController.hasPowerContainer()) {
			builder.drawEnergyBar(this, 20, 10, 20, logicController.powerContainer.getStored(), logicController.powerContainer.getCapacity(), mouseX, mouseY, "RC");
		}
	}

	public int getXSize() {
		return 120;
	}

	public int getYsize() {
		return 60;
	}
}
