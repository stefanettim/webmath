package groups.generators;

public abstract class Generator implements Cloneable {

	protected String label;

	public abstract State doAction(State s) throws Exception;

	public String getLabel() {
		return label;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
