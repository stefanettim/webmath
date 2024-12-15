package xyz.mstef.games.sudoku.sets;

public class SudokuCell implements Comparable<SudokuCell> {

	public final static String rowLabels = "ABCDEFGHI";
	public final static String colLabels = "123456789";

	public int row;
	public int col;

	public SudokuCell(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}

	public SudokuCell(int[] coordinates) {
		super();
		this.row = coordinates[0];
		this.col = coordinates[1];
	}

	@Override
	public int compareTo(SudokuCell o) {
		if (o == null) {
			return -1;
		}
		SudokuCell x = (SudokuCell) o;
		return x.toString().compareTo(toString());
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		SudokuCell x = (SudokuCell) o;
		return x.toString().equals(toString());
	}

	public String toString() {
		return "" + rowLabels.toCharArray()[row] + colLabels.toCharArray()[col];
	}
}
