package teamreborn.techreborn.client;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import teamreborn.techreborn.TechReborn;

/**
 * Created by Gigabit101 on 08/04/2017.
 */
public class CreativeTabTR extends CreativeTabs
{
    public static CreativeTabTR INSTANCE = new CreativeTabTR();

    public CreativeTabTR()
    {
        super(TechReborn.MOD_ID);
    }

    @Override
    public ItemStack getTabIconItem()
    {
        return new ItemStack(Items.DIAMOND);
    }
}
