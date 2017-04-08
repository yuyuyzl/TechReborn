package teamreborn.techreborn.client.guis;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.translation.I18n;
import teamreborn.techreborn.inventorys.AdvancedContainer;
import teamreborn.techreborn.tiles.AdvancedTile;

/**
 * Created by Gigabit101 on 08/04/2017.
 */
public class AdvancedGui extends GuiContainer
{
    AdvancedTile advancedTile;
    EntityPlayer player;

    public AdvancedGui(EntityPlayer player, AdvancedTile advancedTile)
    {
        super(new AdvancedContainer(player, advancedTile));
        this.advancedTile = advancedTile;
        this.player = player;
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        advancedTile.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY, guiLeft, guiTop, xSize, ySize, this);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        advancedTile.drawGuiContainerForegroundLayer(mouseX, mouseY, this, guiLeft, guiTop);
    }

    public void drawTitle() {
        drawCentredString(I18n.translateToLocal(advancedTile.getBlockType().getUnlocalizedName() + ".name"), 6, 4210752, Layer.FOREGROUND);
    }

    public void drawCentredString(String string, int y, int colour, Layer layer) {
        drawString(string, (xSize / 2 - mc.fontRendererObj.getStringWidth(string) / 2), y, colour, layer);
    }

    public void drawCentredString(String string, int y, int colour, int modifier, Layer layer) {
        drawString(string, (xSize / 2 - (mc.fontRendererObj.getStringWidth(string)) / 2) + modifier, y, colour, layer);
    }

    public void drawString(String string, int x, int y, int colour, Layer layer) {
        int factorX = 0;
        int factorY = 0;
        if (layer == Layer.BACKGROUND) {
            factorX = guiLeft;
            factorY = guiTop;
        }
        mc.fontRendererObj.drawString(string, x + factorX, y + factorY, colour);
        GlStateManager.color(1, 1, 1, 1);
    }

    public void addPowerButton(int x, int y, int id, Layer layer) {
        if (id == 0)
            buttonList.clear();
        int factorX = 0;
        int factorY = 0;
        if (layer == Layer.BACKGROUND) {
            factorX = guiLeft;
            factorY = guiTop;
        }
//        buttonList.add(new GuiButtonPowerBar(id, x + factorX, y + factorY, this, layer));
    }

    public enum Layer
    {
        BACKGROUND, Layer, FOREGROUND
    }
}
