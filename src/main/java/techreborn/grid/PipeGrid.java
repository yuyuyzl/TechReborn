package techreborn.grid;

public class PipeGrid extends CableGrid {

	private int transferCapacity;

	public PipeGrid(int identifier, int transferCapacity) {
		super(identifier);

		this.transferCapacity = transferCapacity;
	}

	public int getTransferCapacity() {
		return transferCapacity;
	}

	@Override
	void tick() {

	}

	@Override
	void dirtyPass() {

	}

	@Override
	void applyData(CableGrid grid) {
		if (grid instanceof PipeGrid)
			transferCapacity = ((PipeGrid) grid).getTransferCapacity();
	}
}
