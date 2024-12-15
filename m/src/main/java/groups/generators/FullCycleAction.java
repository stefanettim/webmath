package groups.generators;

public class FullCycleAction extends Generator {

	{
		label = "r";
	}

	@Override
	public State doAction(State s) throws CloneNotSupportedException {
		State n = (State) s.clone();
		int l = s.getSize();
		char[] r = new char[l];
		char[] b = s.getState();
		r[0] = b[l - 1];

		for (int i = 0; i < l - 1; i++)
			r[i + 1] = b[i];

		n.setState(r);
		n.getActions().add(this);
		return n;
	}

}
