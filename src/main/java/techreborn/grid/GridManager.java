package techreborn.grid;

import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class GridManager {
	private static volatile GridManager instance;

	public static final GridManager getInstance() {
		if (GridManager.instance == null)
			synchronized (GridManager.class) {
				if (GridManager.instance == null)
					GridManager.instance = new GridManager();
			}
		return GridManager.instance;
	}

	public HashMap<Integer, CableGrid> cableGrids;

	private GridManager() {
		this.cableGrids = new HashMap<>();
	}

	public PipeGrid addPipeGrid(int identifier, int transferCapacity) {
		return (PipeGrid) this.cableGrids.put(identifier, new PipeGrid(identifier, transferCapacity));
	}

	public CableGrid addGrid(int identifier) {
		return this.cableGrids.put(identifier, new CableGrid(identifier));
	}

	public CableGrid removeGrid(int identifier) {
		return this.cableGrids.remove(identifier);
	}

	public boolean hasGrid(int identifier) {
		return this.cableGrids.containsKey(identifier);
	}

	public CableGrid getGrid(int identifier) {
		return this.cableGrids.get(identifier);
	}

	public int getNextID() {
		int i = 0;
		while (cableGrids.containsKey(i))
			i++;
		return i;
	}

	public void tickGrids() {
		long start = System.currentTimeMillis();
		Iterator<CableGrid> cableGrid = this.cableGrids.values().iterator();

		while (cableGrid.hasNext()) {
			CableGrid grid = cableGrid.next();

			if (grid.isDirty()) {
			}
			grid.tick();
		}
		long elapsed = (System.currentTimeMillis() - start);
		System.out.println("Grids ticking took: " + elapsed + " ms. (" + (50.0 / elapsed) + "% of tick time)");
	}

	void checkMerge(CableGrid grid, ITileCable modified) {
		for (EnumFacing facing : modified.getConnections()) {
			if (modified.getConnected(facing).getGrid() != grid.getIdentifier())
				mergeGrids(grid, this.getGrid(modified.getConnected(facing).getGrid()));
		}
	}

	void mergeGrids(CableGrid destination, CableGrid source) {
		destination.addCables(source.getCables());
		destination.addInputs(source.getInputs());
		destination.addOutputs(source.getOutputs());

		source.getCables().forEach(cable -> cable.setGrid(destination.getIdentifier()));
		this.cableGrids.remove(source.getIdentifier());
	}

	void pruneOrphans(CableGrid grid, ITileCable cable) {

		if (cable.getConnections()[0] != null) {
			List<ITileCable> founds = new ArrayList<>(grid.getCables());
			founds.removeAll(explore(cable, cable.getConnected(cable.getConnections()[0])));

			if (!founds.isEmpty()) {
				this.cableGrids.remove(grid.getIdentifier());
				grid.getCables().forEach(node -> node.setGrid(-1));

				for (EnumFacing facing : cable.getConnections()) {
					ITileCable facingCable = cable.getConnected(facing);

					if (facingCable.getGrid() == -1) {

						CableGrid newGrid = this.addGrid(this.getNextID());
						newGrid.applyData(grid);

						List<ITileCable> exploreds = this.explore(null, facingCable);
						exploreds.forEach(explored -> {

							if (explored != null) {
								newGrid.addCable(explored);
								if (explored.isInput())
									newGrid.addInput(explored);
								if (explored.isOutput())
									newGrid.addOutput(explored);
								explored.setGrid(newGrid.getIdentifier());
							}
						});
					}
				}
			}
		}
	}

	private List<ITileCable> explore(ITileCable ignore, ITileCable cable) {
		List<ITileCable> openset = new ArrayList<>();

		List<ITileCable> frontier = new ArrayList<>();

		frontier.add(cable);
		while (frontier.isEmpty()) {
			Iterator<ITileCable> iterator = frontier.iterator();
			while (iterator.hasNext()) {
				ITileCable current = iterator.next();

				openset.add(current);
				for (EnumFacing facing : current.getConnections()) {
					ITileCable facingCable = current.getConnected(facing);
					if (facingCable != ignore && !openset.contains(facingCable) && !frontier.contains(facingCable))
						frontier.add(facingCable);
				}
				frontier.remove(current);
			}
		}
		return openset;
	}
}
