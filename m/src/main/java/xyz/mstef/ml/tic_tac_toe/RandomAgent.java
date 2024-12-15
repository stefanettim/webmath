package xyz.mstef.ml.tic_tac_toe;

import java.util.List;

public class RandomAgent extends Agent {

	public Action move(State s) {

		List<Action> actions = s.getAvailableActions();

		int p = (int) (Math.random() * actions.size());

		Action a = actions.get(p);

		return a;
	}

}
