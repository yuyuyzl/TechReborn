package techreborn.compat.minetweaker;

import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import techreborn.api.recipe.machines.AssemblingMachineRecipe;
import techreborn.lib.Reference;


@ZenClass("mods.techreborn.assemblingMachine")
public class MTAssemblingMachine extends MTGeneric {

    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input1, IIngredient input2, int ticktime, int euTick) {
        ItemStack oInput1 = (ItemStack) MinetweakerCompat.toObject(input1);
        ItemStack oInput2 = (ItemStack) MinetweakerCompat.toObject(input2);

        AssemblingMachineRecipe r = new AssemblingMachineRecipe(oInput1, oInput2, MinetweakerCompat.toStack(output), ticktime, euTick);

        addRecipe(r);
    }

    @Override
    public  String getMachineName() {
        return Reference.assemblingMachineRecipe;
    }


}