package xyz.mstef.ml.connect_four;

import java.util.List;

public abstract class Agent {

	public abstract List<Action> chooseBestActions(State state, int level);

	public abstract Action move(State state);

	public List<Action> chooseBestActions(State state) {
		return chooseBestActions(state, 0);
	}

	public Action chooseBestRandomAction(State s, int level) {
		List<Action> actions = chooseBestActions(s, level);
		int p = (int) (Math.random() * actions.size());
		Action best = actions.get(p);
		return best;
	}

	public Action chooseBestRandomAction(State s) {
		Action a = chooseBestRandomAction(s, 0);
		return a;
	}

	public Action chooseBestFirstAction(State s, int level) {
		List<Action> actions = chooseBestActions(s, level);
		return actions.get(0);
	}

	public Action chooseBestFirstAction(State s) {
		Action a = chooseBestFirstAction(s, 0);
		return a;
	}

}
