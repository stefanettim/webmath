package groups.generators;

import java.util.Collection;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

public class StatesGroup {

	private String label;
	private State startState;
	private TreeMap<String, State> states = new TreeMap<String, State>();
	private Collection<Generator> generators = new Vector<Generator>();

	private TreeSet<String> generating = new TreeSet<String>();

	private int analyzed;
	private int found;
	private int printed;

	public StatesGroup(int stateSize, String label) {
		this.label = label;
		startState = new State(stateSize);
		states.put(startState.toString(), startState);
	}

	public StatesGroup(State startState, String label) {
		this.label = label;
		this.startState = startState;
		states.put(startState.toString(), startState);
	}

	public void generate() throws Exception {
		generate(startState);
	}

	public void generate(State s) throws Exception {

		if (generating.contains(s.toString()))
			return;

		generating.add(s.toString());

		for (Generator a : generators)
			generate(s, a);

		// try to shorten list of actions
		for (State ss : states.values())
			for (Generator a : generators)
				generate(ss, a);

	}

	private void generate(State s, Generator a) throws Exception {
		State s2 = a.doAction(s);
		analyzed++;

		State e = null;

		for (State x : states.values()) {
			if (x.equals(s2)) {
				e = x;
				break;
			}
		}

		if (e == null) {
			states.put(s2.toString(), s2);
			found++;
			printed++;
			if (printed >= 1000) {
				System.out.println("analyzed : " + analyzed + " , found : " + found);
				printed = 0;
			}
			generate(s2);
		} else if ((s2.getActions().size() < e.getActions().size())
				|| (s2.printActions().length() < e.printActions().length())) {
			states.replace(e.toString(), e, s2);
		}

	}

	public void printStates() {
		System.out.println("=== StatesGroup " + label);
		for (State s : states.values()) {
			s.print();
		}

		System.out.println("size:" + states.size());
	}

	public TreeMap<String, State> getStates() {
		return states;
	}

	public Collection<Generator> getGenerators() {
		return generators;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
