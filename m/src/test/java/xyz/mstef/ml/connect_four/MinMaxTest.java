package xyz.mstef.ml.connect_four;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MinMaxTest {

	private Agent a = new MinMaxAgent();
	private Agent a8 = new MinMaxAgent(8);
	private Agent a9 = new MinMaxAgent(9);

	@Test
	public void oneMoveWin() {
		State s = Factory.oneMoveWinState();
		Action ac = a.move(s);
		State after = Environment.perform(s, ac);
		after.print("oneMoveWin");
		Assertions.assertEquals(3, ac.getCol());
	}

	@Test
	public void fiveMoveWin() {
		State s = Factory.fiveMoveWinState();
		Action ac = a9.move(s);
		State after = Environment.perform(s, ac);
		after.print("fiveMoveWin");
		Assertions.assertEquals(3, ac.getCol());
	}

	@Test
	public void fourMoveWin() {
		State s = Factory.fourMoveWinState();
		Action ac = a8.move(s);
		State after = Environment.perform(s, ac);
		after.print("fourMoveWin");
		Assertions.assertEquals(3, ac.getCol());
	}

	@Test
	public void twoMoveWin() {
		State s = Factory.twoMoveWinState();
		Action ac = a.move(s);
		State after = Environment.perform(s, ac);
		after.print("twoMoveWin");
		Assertions.assertEquals(3, ac.getCol());
	}

	@Test
	public void save() {
		State s = Factory.oneMoveSaveState();
		Action ac = a.move(s);
		State after = Environment.perform(s, ac);
		after.print("oneMoveSave");
		Assertions.assertEquals(3, ac.getCol());
	}

	@Test
	public void smartO() {
		State s = Factory.smartOState();
		Action ac = a.move(s);
		State after = Environment.perform(s, ac);
		after.print("smartO");
		Assertions.assertEquals(2, ac.getCol());
	}

	@Test
	public void smartX() {
		State s = Factory.smartXState();
		Action ac = a.move(s);
		State after = Environment.perform(s, ac);
		after.print("smartX");
		Assertions.assertEquals(2, ac.getCol());
	}

}
