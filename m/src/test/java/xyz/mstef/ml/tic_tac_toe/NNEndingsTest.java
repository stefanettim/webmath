package xyz.mstef.ml.tic_tac_toe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class NNEndingsTest {

	private Agent a = new NNAgent();

	@BeforeAll
	public static void train() {

		System.out.println("training");

		// NNTrainer.trainEpoch();
		NNTrainer.trainEndingsEpoch();

	}

	@Test
	public void endings() {
		EndingsUtils.endingsTest(a);
	}

	@Test
	public void oneMoveWin() {
		EndingsUtils.endingTest(Factory.oneMoveWinState(), a);
	}

	@Test
	public void twoMoveWin() {
		State s = Factory.twoMoveWinState();
		Action ac = a.move(s);
		State after = Environment.perform(s, ac);
		after.print();
		assertNotEquals(2, ac.getMove());
		assertNotEquals(5, ac.getMove());
		assertNotEquals(7, ac.getMove());
		assertNotEquals(8, ac.getMove());
	}

	@Test
	public void oneMoveSave() {
		State s = Factory.oneMoveSaveState();
		Action ac = a.move(s);
		State after = Environment.perform(s, ac);
		after.print();
		assertEquals(5, ac.getMove());
	}

	@Test
	public void smartO() {
		State s = Factory.smartOState();
		Action ac = a.move(s);
		State after = Environment.perform(s, ac);
		after.print();
		assertEquals(4, ac.getMove());
	}

	@Test
	public void smartX() {
		State s = Factory.smartXState();
		Action ac = a.move(s);
		State after = Environment.perform(s, ac);
		after.print();
		assertEquals(4, ac.getMove());
	}

}
