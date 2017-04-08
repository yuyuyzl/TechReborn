package teamreborn.techreborn.inventorys;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import reborncore.common.container.RebornContainer;
import teamreborn.techreborn.tiles.AdvancedTile;

/**
 * Created by Gigabit101 on 08/04/2017.
 */
public class AdvancedContainer extends RebornContainer
{
    AdvancedTile advancedTile;
    EntityPlayer player;

    public AdvancedContainer(EntityPlayer player, AdvancedTile advancedTile)
    {
        this.advancedTile = advancedTile;
        this.player = player;
        if(advancedTile.getSlots() != null)
        {
            for(Slot s : advancedTile.getSlots())
            {
                addSlotToContainer(s);
            }
        }
        drawPlayersInv(player, advancedTile.inventoryOffsetX(), advancedTile.inventoryOffsetY());
        drawPlayersHotBar(player, advancedTile.inventoryOffsetX(), advancedTile.inventoryOffsetY() + 58);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return true;
    }
}
