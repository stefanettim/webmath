package groups.generators;

public class CycleAction extends Generator {

	private int offset;
	private int cycle;

	public CycleAction(int offset, int cycle) {
		super();
		this.offset = offset;
		this.cycle = cycle;

		label = "C" + cycle + "," + offset;
	}

	@Override
	public State doAction(State s) throws CloneNotSupportedException {

		State n = (State) s.clone();

		char[] b = s.getState();
		char[] r = n.getState();

		r[offset] = b[offset + cycle - 1];
		for (int i = offset; i < offset + cycle - 1; i++)
			r[i + 1] = b[i];

		n.getActions().add(this);

		return n;
	}

}
