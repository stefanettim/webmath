package xyz.mstef.ml.connect_four;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GamesTest {

	@Test
	public void game() {
		Agent o = new MinMaxAgent(6);
		Agent x = new MinMaxAgent(6);
		Environment e = new Environment(o, x);

		for (int i = 0; i < 3; i++) {
			State finalState = e.startGame(false);
			finalState.print("game" + i);
		}

	}

	@Test
	public void minMaxRandomMatch() {
		Agent o = new MinMaxAgent();
		Agent x = new RandomAgent();

		Environment e = new Environment(o, x);

		for (int i = 0; i < 3; i++) {
			State finalState = e.startGame(false);
			finalState.print("random" + i);
			Assertions.assertNotEquals(State.X, finalState.getWinner());
		}

	}

}
