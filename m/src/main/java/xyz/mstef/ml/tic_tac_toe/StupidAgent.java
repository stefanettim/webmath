package xyz.mstef.ml.tic_tac_toe;

public class StupidAgent extends Agent {

	public Action move(State s) {

		for (int i = 0; i < 9; i++) {
			if (s.getValue(i) == State.E) {
				Action a = new Action(i);
				return a;
			}
		}

		return null;
	}

}
