package xyz.mstef.ml.connect_four;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StateTest {

	@Test
	public void initialState() {
		Factory.initialState().print();
		Assertions.assertEquals(State.E, Factory.initialState().getWinner());
	}

	@Test
	public void winState() {
		Factory.winState().print();
		Assertions.assertEquals(State.X, Factory.winState().getWinner());
	}

	@Test
	public void tieState() {
		Factory.tieState().print();
		Assertions.assertEquals(State.E, Factory.tieState().getWinner());
		assert (Factory.tieState().isTie());
	}

	@Test
	public void fullWinState() {
		State s = Factory.fullWinState();
		s.print();
		assert (s.isFull());
		Assertions.assertEquals(State.O, s.getWinner());
	}
}
