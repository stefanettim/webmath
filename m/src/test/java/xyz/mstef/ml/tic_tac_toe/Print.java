package xyz.mstef.ml.tic_tac_toe;

import org.junit.jupiter.api.Test;

public class Print {

	@Test
	public void printInitialState() {
		Factory.initialState().print();
	}

	@Test
	public void printWinState() {
		Factory.winState().print();
	}

	@Test
	public void printTieState() {
		Factory.tieState().print();
	}

	@Test
	public void printFullWinState() {
		Factory.fullWinState().print();
	}
}
