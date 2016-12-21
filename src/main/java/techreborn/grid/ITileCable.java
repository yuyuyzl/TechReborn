package techreborn.grid;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface ITileCable {
	BlockPos getPos();

	EnumFacing[] getConnections();

	ITileCable getConnected(EnumFacing facing);

	int getGrid();

	void setGrid(int gridIdentifier);

	boolean isInput();

	boolean isOutput();

	void connect(EnumFacing facing, ITileCable to);

	void disconnect(EnumFacing facing);

	World getWorld();
}
