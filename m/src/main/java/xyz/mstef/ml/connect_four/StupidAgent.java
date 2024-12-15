package xyz.mstef.ml.connect_four;

import java.util.List;

public class StupidAgent extends Agent {

	@Override
	public Action move(State s) {

		for (int c = 0; c < 7; c++) {
			if (s.hwm[c] < 6) {
				Action a = new Action(c);
				return a;
			}
		}

		return null;
	}

	@Override
	public List<Action> chooseBestActions(State state, int level) {
		return state.getAvailableActions();
	}
}
