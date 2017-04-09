package teamreborn.techreborn;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

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
