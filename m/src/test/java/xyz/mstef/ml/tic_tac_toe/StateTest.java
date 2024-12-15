package xyz.mstef.ml.tic_tac_toe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StateTest {

	@Test
	public void initialState() {
		assertEquals(State.E, Factory.initialState().getWinner());
	}

	@Test
	public void winState() {
		assertEquals(State.X, Factory.winState().getWinner());
	}

	@Test
	public void tieState() {
		assertEquals(State.E, Factory.tieState().getWinner());
	}

	@Test
	public void fullWinState() {
		State s = Factory.fullWinState();
		assert (s.isFull());
		assertEquals(State.O, s.getWinner());
	}

}
