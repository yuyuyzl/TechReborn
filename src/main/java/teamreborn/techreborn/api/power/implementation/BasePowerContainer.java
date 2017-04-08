package teamreborn.techreborn.api.power.implementation;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;
import teamreborn.techreborn.api.power.IPowerConsumer;
import teamreborn.techreborn.api.power.IPowerHolder;
import teamreborn.techreborn.api.power.IPowerProducer;

/**
 * Created by Gigabit101 on 08/04/2017.
 */
public class BasePowerContainer implements IPowerConsumer, IPowerProducer, IPowerHolder, INBTSerializable<NBTTagCompound>
{
    private int stored;
    private int capacity;
    private int inputRate;
    private int outputRate;

    public BasePowerContainer(int stored, int capacity, int inputRate, int outputRate)
    {
        this.stored = stored;
        this.capacity = capacity;
        this.inputRate = inputRate;
        this.outputRate = outputRate;
    }

    public BasePowerContainer(int capacity, int inputRate, int outputRate)
    {
        this(0, capacity, inputRate, outputRate);
    }

    public BasePowerContainer()
    {
        this(0, 5000, 50, 50);
    }

    @Override
    public int givePower(int amount, boolean simulated)
    {
        final int accepted = Math.min(this.getCapacity() - this.stored, Math.min(this.inputRate, amount));

        if (!simulated)
            this.stored += accepted;

        return accepted;
    }

    @Override
    public int getStoredPower()
    {
        return this.stored;
    }

    @Override
    public int getCapacity()
    {
        return this.capacity;
    }

    @Override
    public int takePower(int amount, boolean simulated)
    {
        final int removedPower = Math.min(this.stored, Math.min(this.outputRate, amount));

        if (!simulated)
            this.stored -= removedPower;

        return removedPower;
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        final NBTTagCompound dataTag = new NBTTagCompound();
        dataTag.setInteger("powerStored", this.stored);
        dataTag.setInteger("powerCapacity", this.capacity);
        dataTag.setInteger("powerInput", this.inputRate);
        dataTag.setInteger("powerOutput", this.outputRate);

        return dataTag;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {
        this.stored = nbt.getInteger("powerStored");

        if (nbt.hasKey("powerCapacity"))
            this.capacity = nbt.getInteger("powerCapacity");

        if (nbt.hasKey("powerInput"))
            this.inputRate = nbt.getInteger("powerInput");

        if (nbt.hasKey("powerOutput"))
            this.outputRate = nbt.getInteger("powerOutput");

        if (this.stored > this.getCapacity())
            this.stored = this.getCapacity();
    }

    public int getInputRate()
    {
        return inputRate;
    }

    public int getOutputRate()
    {
        return outputRate;
    }

    public int getStored()
    {
        return stored;
    }
}
