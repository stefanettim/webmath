package xyz.mstef.games.sudoku.sets;

import java.util.List;

public class SudokuSet {

	public String name;
	public SudokuCell[] cells = new SudokuCell[9];

	public SudokuSet(String name, int[][] coordinates) {
		super();
		for (int i = 0; i < 9; i++) {
			this.cells[i] = new SudokuCell(coordinates[i]);
		}
		this.name = name;
	}

	public String toString() {
		return name;
	}

	public boolean contains(SudokuCell c) {
		for (int i = 0; i < 9; i++) {
			if (cells[i] == c) {
				return true;
			}
		}

		return false;
	}

	public List<SudokuCell> filterEmptyCells(SetsSudoku s) {
		return s.getEmptyCells(this);
	}

}
