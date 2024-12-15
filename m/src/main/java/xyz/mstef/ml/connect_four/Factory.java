package xyz.mstef.ml.connect_four;

public class Factory {

	public static State initialState() {
		return new State();
	}

	public static State winState() {
		return new State().init(new int[][] { { -1, -1, -1, -1, 0, 0, 0 }, { 1, 1, 1, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 } }, 1,
				3, -1);
	}

	public static State tieState() {
		return new State().init(
				new int[][] { { -1, -1, 1, 1, -1, -1, 1 }, { 1, 1, -1, -1, 1, -1, -1 }, { -1, -1, 1, 1, -1, -1, 1 },
						{ 1, 1, -1, -1, 1, -1, -1 }, { -1, -1, 1, 1, -1, -1, 1 }, { 1, 1, -1, -1, 1, -1, -1 } },
				1, 6, -1);
	}

	public static State fullWinState() {
		return new State().init(
				new int[][] { { -1, -1, 1, 1, -1, -1, 1 }, { 1, 1, -1, -1, 1, -1, -1 }, { -1, -1, 1, 1, -1, -1, 1 },
						{ 1, 1, -1, -1, 1, -1, -1 }, { -1, -1, 1, 1, -1, -1, 1 }, { 1, 1, 1, 1, 1, -1, -1 } },
				1, 2, -1);
	}

	public static State oneMoveWinState() {
		return new State().init(new int[][] { { 1, 1, 1, 0, 0, 0, 0 }, { -1, -1, -1, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, },
				1, 2, -1);
	}

	public static State fiveMoveWinState() {
		return new State().init(new int[][] { { 1, -1, 1, -1, 1, -1, 1 }, { -1, -1, 1, 1, -1, -1, 1 },
				{ 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, },
				-1, 2, -1);
	}

	public static State fourMoveWinState() {
		return new State()
				.init(new int[][] { { 1, -1, 1, 1, 1, -1, 1 }, { 1, 1, 0, -1, -1, 1, -1 }, { -1, -1, 0, 0, 0, -1, 0 },
						{ -1, 0, 0, 0, 0, 1, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, }, -1, 2, -1);
	}

	public static State twoMoveWinState() {
		return new State().init(new int[][] { { 0, 1, 1, 0, 0, 0, 0 }, { 0, -1, -1, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 } }, 1,
				2, -1);
	}

	public static State oneMoveSaveState() {
		return new State().init(new int[][] { { 1, 1, 1, 0, 0, 0, 1 }, { -1, -1, -1, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 } },
				-1, 6, -1);
	}

	public static State smartXState() {
		return new State().init(new int[][] { { 0, 1, -1, 1, -1, 0, 0 }, { 0, 1, -1, -1, 0, 0, 0 },
				{ 0, -1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, },
				-1, 2, -1);
	}

	public static State smartOState() {
		return new State().init(new int[][] { { 1, -1, 1, -1, 0, 0, 0 }, { -1, 1, -1, -1, 0, 0, 0 },
				{ 1, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, },
				1, 2, -1);
	}

}
