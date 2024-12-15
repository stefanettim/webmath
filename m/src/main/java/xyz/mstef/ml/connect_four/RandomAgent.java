package xyz.mstef.ml.connect_four;

import java.util.List;

public class RandomAgent extends Agent {

	public Action move(State s) {

		List<Action> actions = s.getAvailableActions();

		int p = (int) (Math.random() * actions.size());

		Action a = actions.get(p);

		return a;
	}

	@Override
	public List<Action> chooseBestActions(State state, int level) {
		return state.getAvailableActions();
	}

}
