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

	void applyData(final CableGrid grid) {

	}

	public void addCable(@Nonnull final ITileCable cable) {
		this.cables.add(cable);
	}

	public void addCables(final Collection<ITileCable> cables) {
		cables.forEach(this::addCable);
	}

	public boolean removeCable(final ITileCable cable) {
		if (this.inputs.contains(cable))
			this.inputs.remove(cable);
		if (this.outputs.contains(cable))
			this.outputs.remove(cable);
		return this.cables.remove(cable);
	}

	public void removeCables(final Collection<ITileCable> cables) {
		cables.forEach(this::removeCable);
	}

	public boolean hasCable(final ITileCable cable) {
		return this.cables.contains(cable);
	}

	public void addInput(final ITileCable input) {
		this.inputs.add(input);
		this.addCable(input);
	}

	public void addInputs(final Collection<ITileCable> inputs) {
		inputs.forEach(this::addInput);
	}

	public boolean removeInput(final ITileCable input) {
		return this.inputs.remove(input);
	}

	public boolean hasInput(final ITileCable input) {
		return this.inputs.contains(input);
	}

	public void addOutput(final ITileCable output) {
		this.outputs.add(output);
		this.addCable(output);
	}

	public void addOutputs(final Collection<ITileCable> outputs) {
		outputs.forEach(this::addOutput);
	}

	public boolean removeOutput(final ITileCable output) {
		return this.outputs.remove(output);
	}

	public boolean hasOutput(final ITileCable output) {
		return this.outputs.contains(output);
	}

	public int getIdentifier() {
		return this.identifier;
	}

	public HashSet<ITileCable> getCables() {
		return this.cables;
	}

	public HashSet<ITileCable> getInputs() {
		return this.inputs;
	}

	public HashSet<ITileCable> getOutputs() {
		return this.outputs;
	}

	public void markDirty() {
		this.isDirty = true;
	}

	public boolean isDirty() {
		return this.isDirty;
	}

	@Override
	public String toString() {
		return "CableGrid [identifier=" + this.identifier + ", cables=" + this.cables + ", inputs=" + this.inputs
				+ ", outputs=" + this.outputs + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.cables == null ? 0 : this.cables.hashCode());
		result = prime * result + this.identifier;
		result = prime * result + (this.inputs == null ? 0 : this.inputs.hashCode());
		result = prime * result + (this.outputs == null ? 0 : this.outputs.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		final CableGrid other = (CableGrid) obj;
		if (this.cables == null) {
			if (other.cables != null)
				return false;
		} else if (!this.cables.equals(other.cables))
			return false;
		if (this.identifier != other.identifier)
			return false;
		if (this.inputs == null) {
			if (other.inputs != null)
				return false;
		} else if (!this.inputs.equals(other.inputs))
			return false;
		if (this.outputs == null) {
			if (other.outputs != null)
				return false;
		} else if (!this.outputs.equals(other.outputs))
			return false;
		return true;
	}
}
