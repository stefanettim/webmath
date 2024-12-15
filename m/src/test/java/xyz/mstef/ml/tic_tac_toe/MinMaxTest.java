package xyz.mstef.ml.tic_tac_toe;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class MinMaxTest {

	@Nested
	@Tag("play")
	@DisplayName("Games")
	public class InnerGames {

		@RepeatedTest(value = 5)
		public void minMaxRandomMatch() {
			Agent o = new MinMaxAgent();
			Agent x = new RandomAgent();

			Environment e = new Environment(o, x);

			State finalState = e.startGame();
			assertNotEquals(State.X, finalState.getWinner());

		}

		@RepeatedTest(value = 5)
		public void draw() {
			Agent o = new MinMaxAgent();
			Agent x = new MinMaxAgent();
			Environment e = new Environment(o, x);

			State finalState = e.startGame();
			assert (finalState.isTie());

		}
	}

	private Agent a = new MinMaxAgent();

	@Test
	@Tag("endings")
	public void endings() {
		EndingsUtils.endingsTest(a);
	}

}
