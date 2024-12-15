package xyz.mstef.ml.tic_tac_toe;

import java.util.Arrays;

public class EndingsUtils {

	public static void endingTest(State s, Agent a) {
		System.out.println("++++ endingTest " + s.getTitle());
		Action ac = a.move(s);
		State after = Environment.perform(s, ac);
		after.print();

		int p = Arrays.binarySearch(s.getPerfectMoves(), ac.getMove());

		assert (p >= 0);
	}

	public static void endingsTest(Agent a) {

		for (State s : Factory.endings()) {
			endingTest(s, a);
		}

	}

}
