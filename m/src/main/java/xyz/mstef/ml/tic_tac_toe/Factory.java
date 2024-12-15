package xyz.mstef.ml.tic_tac_toe;

public class Factory {

	public static State initialState() {
		return new State();
	}

	public static State winState() {
		return new State().init("winState", new int[] { -1, 0, 1, 1, -1, 0, 1, 0, -1 }, 1, new int[] {});
	}

	public static State tieState() {
		return new State().init("tieState", new int[] { -1, 1, 1, 1, 1, -1, -1, -1, 1 }, 1, new int[] {});
	}

	public static State fullWinState() {
		return new State().init("fullWinState", new int[] { 1, 1, 1, 1, 1, -1, -1, -1, 1 }, 1, new int[] {});
	}

	public static State oneMoveWinState() {
		return new State().init("oneMoveWinState", new int[] { 1, 1, 0, -1, -1, 0, 0, 0, 0 }, -1, new int[] { 5 });
	}

	public static State twoMoveWinState() {
		return new State().init("twoMoveWinState", new int[] { 1, -1, 0, 0, 0, 0, 0, 0, 0 }, 1, new int[] { 3, 4 });
	}

	public static State oneMoveSaveState() {
		return new State().init("oneMoveSaveState", new int[] { -1, 0, 0, 1, 1, 0, -1, 0, 0 }, -1, new int[] { 5 });
	}

	public static State smartOState() {
		return new State().init("smartOState", new int[] { -1, 1, -1, 0, 0, 0, 0, 0, 0 }, 1, new int[] { 4 });
	}

	public static State smartXState() {
		return new State().init("smartXState", new int[] { 1, -1, 1, 0, 0, 0, 0, 0, 0 }, -1, new int[] { 4 });
	}

	public static State[] endings() {
		return new State[] { twoMoveWinState(), oneMoveWinState(), smartOState(), smartXState(), oneMoveSaveState() };
	}

}
