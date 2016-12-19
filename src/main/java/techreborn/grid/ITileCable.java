package techreborn.grid;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public interface ITileCable {
	BlockPos getPos();

	EnumFacing[] getConnections();

	ITileCable getConnected(EnumFacing facing);

	int getGrid();

	void setGrid(int gridIdentifier);

	boolean isInput();

	boolean isOutput();
}
