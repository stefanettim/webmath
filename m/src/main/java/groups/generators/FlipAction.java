package groups.generators;

public class FlipAction extends Generator {

	private int offset;

	public FlipAction(int offset, String label) {
		super();
		this.offset = offset;
		this.label = label;
	}

	@Override
	public State doAction(State s) throws CloneNotSupportedException {

		char[] b = s.getState();

		State n = (State) s.clone();
		char[] r = n.getState();

		r[offset] = b[offset + 1];
		r[offset + 1] = b[offset];

		n.setState(r);
		n.getActions().add(this);

		return n;
	}

}
