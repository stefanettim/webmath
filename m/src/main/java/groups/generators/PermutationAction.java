package groups.generators;

public class PermutationAction extends Generator {

	private int[] set;

	public PermutationAction(String label, int[] set) {
		super();
		this.label = label;
		this.set = set;
	}

	@Override
	public State doAction(State s) throws CloneNotSupportedException {

		State m = (State) s.clone();

		for (int i = 0; i < s.getSize(); i++)
			m.state[i] = s.state[set[i]];

		return m;
	}

}
