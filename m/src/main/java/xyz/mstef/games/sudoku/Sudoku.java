package xyz.mstef.games.sudoku;

public class Sudoku implements Cloneable {

	public int[][] board = new int[9][9];
	public int availability[][][] = new int[9][9][10];

	public Sudoku(int[][] board) {
		super();

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				this.board[i][j] = board[i][j];
			}
		}

	}

	public Sudoku(int[][] board, int[][][] availability) {
		super();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				this.board[i][j] = board[i][j];
				for (int k = 0; k < 10; k++) {
					this.availability[i][j][k] = availability[i][j][k];
				}
			}
		}

	}

	public Sudoku(String line) {
		super();

		int p = 0;
		int i = 0;
		int j = 0;

		for (char c : line.toCharArray()) {

			i = p / 9;
			j = p % 9;

			if (c == '.' || c == '0') {
				board[i][j] = -1;
			} else {
				int k = Integer.parseInt("" + c);
				board[i][j] = k;
			}

			p++;
		}

	}

	public Sudoku() {
		super();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				board[i][j] = -1;
				availability[i][j] = new int[10];
				availability[i][j][0] = 9;
			}
		}
	}

	public Sudoku clone() {

		Sudoku x = new Sudoku();

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				x.board[i][j] = board[i][j];
				for (int k = 0; k < 10; k++) {
					x.availability[i][j][k] = availability[i][j][k];
				}
			}
		}

		return x;

	}

	public boolean chechRow(int row, int col, int value) {

		for (int j = 0; j < 9; j++) {

			if (j == col) {
				break;
			}

			if (board[row][j] == value) {
				return false;
			}
		}

		return true;

	}

	public boolean chechCol(int row, int col, int value) {

		for (int i = 0; i < 9; i++) {

			if (i == row) {
				break;
			}

			if (board[i][col] == value) {
				return false;
			}
		}

		return true;

	}

	public boolean chechSquare(int row, int col, int value) {

		int sqrow = (row / 3) * 3;
		int sqcol = (col / 3) * 3;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {

				if ((sqrow + i == row) && (sqcol + j == col)) {
					break;
				}

				if (board[sqrow + i][sqcol + j] == value) {
					return false;
				}
			}
		}
		return true;

	}

	public void print() {

		System.out.println("Sudoku " + toLine());

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				int v = board[i][j];

				if (v > 0) {
					System.out.print(v + " ");
				} else {
					System.out.print("  ");
				}

				if (j == 2 || j == 5) {
					System.out.print("|");
				}

			}
			System.out.println("");
			if (i == 2 || i == 5) {
				System.out.println("------+------+-----");
			}
		}

	}

	public boolean solved() {

		for (int i = 0; i < 9; i++) {

			for (int j = 0; j < 9; j++) {

				int v = board[i][j];

				if (v == -1) {
					return false;
				}

				boolean r = chechRow(i, j, v);
				if (!r) {
					return false;
				}

				boolean c = chechCol(i, j, v);
				if (!c) {
					return false;
				}

				boolean q = chechSquare(i, j, v);
				if (!q) {
					return false;
				}
			}
		}

		return true;
	}

	public boolean equals(Object o) {

		if (o == null) {
			return false;
		}

		if (!(o instanceof Sudoku)) {
			return false;
		}

		Sudoku x = (Sudoku) o;

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (!(board[i][j] == x.board[i][j])) {
					return false;
				}
			}
		}

		return true;
	}

	public String cell(Long i, Long j) {
		if (board[i.intValue()][j.intValue()] == -1) {
			return "";
		}

		return "" + board[i.intValue()][j.intValue()];
	}

	public String getStringAvailability(int i, int j) {
		String s = "";
		for (int k = 0; k < 10; k++) {
			if (availability[i][j][k] == 1) {
				s += k;
			}
		}
		return s;
	}

	public void printAvailability() {

		System.out.println("Sudoku " + toLine());

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				int v = board[i][j];

				if (v > 0) {
					System.out.printf("%9s", v);
				} else {
					System.out.printf("%9s", getStringAvailability(i, j));
				}

				if (j == 2 || j == 5) {
					System.out.print("|");
				}

			}
			System.out.println("");
			if (i == 2 || i == 5) {
				System.out
						.println("---------------------------+---------------------------+---------------------------");
			}
		}

	}

	public String toLine() {

		String line = "";
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] > 0) {
					line += board[i][j];
				} else {
					line += ".";
				}
			}
		}

		return line;
	}

	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}



	
}
