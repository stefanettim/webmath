package xyz.mstef.games.sudoku.sets;

import java.util.ArrayList;
import java.util.List;

import xyz.mstef.games.sudoku.Sudoku;

public class SetsSudoku extends Sudoku {

	public SetsSudoku(Sudoku s) {
		super(s.board, s.availability);
	}

	public boolean isEmpty(SudokuCell c) {
		return board[c.row][c.col] == -1;
	}

	public int getValue(SudokuCell c) {
		return board[c.row][c.col];
	}

	public int getAvailability(SudokuCell c, int k) {
		return availability[c.row][c.col][k];
	}

	public void setValue(SudokuCell c, int k) throws Exception {
		board[c.row][c.col] = k;
		availability[c.row][c.col][0] = 0;
		availability[c.row][c.col][k] = 0;

		for (SudokuSet s : SudokuSets.setsMap.get(c.toString())) {
			for (SudokuCell t : s.cells) {
				if (!t.equals(c)) {
					setUnavailable(t, k);
				}
			}
		}

	}

	public int getAvailability(SudokuCell c) {
		return availability[c.row][c.col][0];
	}

	public int getOnlyValue(SudokuCell cell) {
		if (getAvailability(cell) > 1) {
			return -1;
		}
		for (int k = 1; k <= 9; k++) {
			if (availability[cell.row][cell.col][k] == 1) {
				return k;
			}
		}
		return -1;
	}

	public void availability() {
		for (SudokuCell c : SudokuCells.allCells) {
			if (isEmpty(c)) {
				availability[c.row][c.col] = availability(c);
			}
		}
	}

	private int[] availability(SudokuCell c) {

		int[] availability = new int[10];

		if (!isEmpty(c)) {
			return availability;
		}

		availability[0] = 9;

		for (int k = 1; k < 10; k++) {
			availability[k] = 1;
		}

		for (SudokuSet set : SudokuSets.setsMap.get(c.toString())) {
			for (SudokuCell sc : set.cells) {
				int v = getValue(sc);
				if (v > 0 && availability[v] == 1) {
					availability[v] = 0;
					availability[0]--;
				}
			}
		}

		return availability;
	}

	public List<SudokuCell> getAvailableCells(SudokuSet ss, int k) {

		List<SudokuCell> list = new ArrayList<SudokuCell>();
		for (SudokuCell c : ss.cells) {
			if (isEmpty(c) && getAvailability(c, k) == 1) {
				list.add(c);
			}
		}

		return list;
	}

	public void setUnavailable(SudokuCell r, int k) throws Exception {
		if (availability[r.row][r.col][k] == 1) {
			availability[r.row][r.col][k] = 0;
			availability[r.row][r.col][0]--;
		}

	}

	public boolean compareAvailability(SudokuCell c, SudokuCell t) {

		for (int k = 1; k < 10; k++) {
			int a = getAvailability(c, k);
			int b = getAvailability(t, k);
			if (a != b) {
				return false;
			}
		}

		return true;
	}

	public int getFirstAvailability(SudokuCell c) {
		for (int k = 1; k < 10; k++) {
			if (getAvailability(c, k) == 1) {
				return k;
			}
		}
		return -1;
	}

	public int getSecondAvailability(SudokuCell c) {
		boolean first = false;
		for (int k = 1; k < 10; k++) {
			if (getAvailability(c, k) == 1) {
				if (!first) {
					first = true;
				} else {
					return k;
				}
			}
		}
		return -1;
	}

	public ArrayList<Integer> getValues(SudokuCell c) {

		ArrayList<Integer> values = new ArrayList<Integer>();

		if (getAvailability(c) <= 0) {
			return values;
		}

		for (int k = 1; k < 10; k++) {
			if (getAvailability(c, k) == 1) {
				values.add(k);
			}
		}
		return values;
	}

	public List<SudokuCell> getEmptyCells(SudokuSet ss) {
		List<SudokuCell> list = new ArrayList<SudokuCell>();
		for (SudokuCell c : ss.cells) {
			if (isEmpty(c)) {
				list.add(c);
			}
		}

		return list;

	}

	public SetsSudoku clone() {

		SetsSudoku ss = new SetsSudoku(this);
		return ss;
	}

	public boolean impossible() {
		for (SudokuSet set : SudokuSets.allSets) {
			for (int k = 1; k < 10; k++) {
				boolean first=false;
			for (SudokuCell sc : set.cells) {
				if(getValue(sc)==k) {
					if(!first) {
						first=true;
					} else {
						return true;
					}
				}
			}
			}
		}
		return false;
	}

	public int getFilled() {
		int f=0;
		for(SudokuCell c:SudokuCells.allCells) {
			if(!isEmpty(c)) {
				f++;
			}
		}
		return f;
	}

}
