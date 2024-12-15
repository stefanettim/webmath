package groups.generators;

import java.util.LinkedList;
import java.util.List;

public class State implements Cloneable {

	char[] state;
	private List<Generator> actions = new LinkedList<Generator>();

	private void init(char[] state) {
		setState(state);
	}

	public State(char[] state) {
		super();
		init(state);
	}

	public State(int i) {
		super();
		char[] x = new char[i];
		for (int j = 0; j < i; j++)
			x[j] = ("" + (j + 1)).charAt(0);
		init(x);
	}

	public char[] getState() {
		return state;
	}

	public int getSize() {
		return state.length;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		State c = (State) super.clone();
		c.state = state.clone();

		LinkedList<Generator> clone = new LinkedList<Generator>();

		for (Generator g : getActions())
			clone.add((Generator) g.clone());

		c.setActions(clone);
		return c;
	}

	public List<Generator> getActions() {
		return actions;
	}

	public String printActions() {

		if (actions.isEmpty())
			return "e";

		String space = "";
		int power = 0;
		String print = "";
		String last = "";
		for (Generator a : actions) {
			if (a.getLabel().equals(last) || "".equals(last))
				power++;
			else {
				if (power > 1)
					print += last + power + space;
				else
					print += last + space;
				power = 0;
			}
			last = a.getLabel();
		}

		if (power > 1)
			print += last + power + space;
		else
			print += last + space;

		return print;
	}

	public void print() {

		System.out.print(toString());
		System.out.print(" - " + printActions());
		System.out.println();
	}

	public String toString() {
		String stringState = "[" + state[0];
		for (int i = 1; i < state.length; i++)
			stringState += "" + state[i];
		stringState += "]";
		return stringState;
	}

	public void setActions(List<Generator> actions) {
		this.actions = actions;
	}

	public void setState(char[] newState) {

		state = new char[newState.length];
		for (int j = 0; j < newState.length; j++)
			state[j] = newState[j];

	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof State))
			return false;

		State s = (State) o;

		for (int i = 0; i < state.length; i++)
			if (state[i] != s.state[i])
				return false;

		return true;
	}

}
