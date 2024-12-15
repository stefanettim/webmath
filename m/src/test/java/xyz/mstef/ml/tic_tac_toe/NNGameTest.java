package xyz.mstef.ml.tic_tac_toe;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class NNGameTest {

	private Agent a = new NNAgent();

	@BeforeAll
	public static void train() {

		System.out.println("training");

		NNTrainer.trainEndingsEpoch();
		NNTrainer.trainEpoch();

	}

	@Test
	public void draw() {

		Environment e = new Environment(a, a);

		State finalState = null;

		finalState = e.startGame(true);
		finalState.print();
		assert (finalState.isTie());

	}

	@Test
	public void randomMatch() {
		
		Agent o = new NNAgent();
		Agent x = new RandomAgent();

		Environment e = new Environment(o, x);

		State finalState = e.startGame();
		finalState.print();
		assertNotEquals(State.X, finalState.getWinner());

	}

	@Test 
	public void minMaxMatch() {
		
		Agent o = new NNAgent();
		Agent x = new MinMaxAgent();

		Environment e = new Environment(o, x);

		State finalState = e.startGame();
		finalState.print();
		assertNotEquals(State.X, finalState.getWinner());

	}

}
