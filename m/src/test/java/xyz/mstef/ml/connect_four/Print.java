package xyz.mstef.ml.connect_four;

import org.junit.jupiter.api.Test;

public class Print {

	@Test
	public void printInitialState() {
		Factory.initialState().print("initialState");
	}

	@Test
	public void printWinState() {
		Factory.winState().print("winState");
	}

	@Test
	public void printTieState() {
		Factory.tieState().print("tieState");
	}

	@Test
	public void printFullWinState() {
		Factory.fullWinState().print("fullWinState");
	}
}
