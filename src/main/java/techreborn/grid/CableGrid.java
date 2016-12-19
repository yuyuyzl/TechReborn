package techreborn.grid;

import javax.annotation.Nonnull;

import java.util.Collection;
import java.util.HashSet;

public class CableGrid {
	private final int identifier;

	boolean isDirty;

	private final HashSet<ITileCable> cables;

	private final HashSet<ITileCable> inputs;
	private final HashSet<ITileCable> outputs;

	public CableGrid(final int identifier) {
		this.identifier = identifier;

		this.cables = new HashSet<>();
		this.inputs = new HashSet<>();
		this.outputs = new HashSet<>();
	}

	void tick() {

	}

	void dirtyPass() {

	}

	void applyData(CableGrid grid) {

	}

	public void addCable(@Nonnull ITileCable cable) {
		if (this.cables.add(cable)) {
			this.markDirty();
			GridManager.getInstance().checkMerge(this, cable);
		}
	}

	public void addCables(Collection<ITileCable> cables) {
		cables.forEach(this::addCable);
	}

	public boolean removeCable(ITileCable cable) {
		if (this.inputs.contains(cable))
			this.inputs.remove(cable);
		if (this.outputs.contains(cable))
			this.outputs.remove(cable);
		boolean removed = this.cables.remove(cable);

		if (removed)
			GridManager.getInstance().pruneOrphans(this, cable);
		return removed;
	}

	public void removeCables(Collection<ITileCable> cables) {
		cables.forEach(this::removeCable);
	}

	public boolean hasCable(ITileCable cable) {
		return this.cables.contains(cable);
	}

	public void addInput(ITileCable input) {
		this.inputs.add(input);
		this.addCable(input);
	}

	public void addInputs(Collection<ITileCable> inputs) {
		inputs.forEach(this::addInput);
	}

	public boolean removeInput(ITileCable input) {
		return this.inputs.remove(input);
	}

	public boolean hasInput(ITileCable input) {
		return this.inputs.contains(input);
	}

	public void addOutput(ITileCable output) {
		this.outputs.add(output);
		this.addCable(output);
	}

	public void addOutputs(Collection<ITileCable> outputs) {
		outputs.forEach(this::addOutput);
	}

	public boolean removeOutput(ITileCable output) {
		return this.outputs.remove(output);
	}

	public boolean hasOutput(ITileCable output) {
		return this.outputs.contains(output);
	}

	public int getIdentifier() {
		return identifier;
	}

	public HashSet<ITileCable> getCables() {
		return cables;
	}

	public HashSet<ITileCable> getInputs() {
		return inputs;
	}

	public HashSet<ITileCable> getOutputs() {
		return outputs;
	}

	public void markDirty() {
		this.isDirty = true;
	}

	public boolean isDirty() {
		return this.isDirty;
	}

	@Override
	public String toString() {
		return "CableGrid [identifier=" + identifier + ", cables=" + cables + ", inputs=" + inputs + ", outputs="
				+ outputs + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cables == null) ? 0 : cables.hashCode());
		result = prime * result + identifier;
		result = prime * result + ((inputs == null) ? 0 : inputs.hashCode());
		result = prime * result + ((outputs == null) ? 0 : outputs.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CableGrid other = (CableGrid) obj;
		if (cables == null) {
			if (other.cables != null)
				return false;
		} else if (!cables.equals(other.cables))
			return false;
		if (identifier != other.identifier)
			return false;
		if (inputs == null) {
			if (other.inputs != null)
				return false;
		} else if (!inputs.equals(other.inputs))
			return false;
		if (outputs == null) {
			if (other.outputs != null)
				return false;
		} else if (!outputs.equals(other.outputs))
			return false;
		return true;
	}
}
