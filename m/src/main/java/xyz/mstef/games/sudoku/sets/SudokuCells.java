package xyz.mstef.games.sudoku.sets;

public class SudokuCells {

	public static SudokuCell[] allCells = new SudokuCell[81];

	static {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				allCells[i + 9 * j] = new SudokuCell(i, j);
			}
		}
	}

}
