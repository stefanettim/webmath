package xyz.mstef.games.sudoku;

public class AvEngine extends Engine {

	Sudoku s;

	public Sudoku solve(Sudoku s) {

		this.s = s;
		availability();

		Sudoku r = solve(s, 0);

		if (r != null && r.solved()) {
			return r;
		}

		return null;
	}

	private Sudoku solve(Sudoku s, int lev) {

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				if (s.board[i][j] == -1) {

					for (int k = 1; k < 10; k++) {

						if (s.availability[i][j][k] == 1) {

							if (lev < 50) {
								System.out.println("lev:" + lev + " row:" + i + " col:" + j + " val:" + k);
							}

							s.board[i][j] = k;

							if ((i == 8) && (j == 8))
								return s;

							boolean impossible = false;

							impossible = unavailableRowValue(i, k);

							if (!impossible) {
								impossible = unavailableColValue(j, k);
							}

							if (!impossible) {
								impossible = unavailableSquareValue(i, j, k);
							}

							if (!impossible) {
								Sudoku o = solve(s, lev + 1);
								if (o != null) {
									return o;
								}
							}

							availableRowValue(i, k);
							availableColValue(j, k);
							availableSquareValue(i, j, k);

							s.board[i][j] = -1;
						}

					}
				}
			}
		}

		return null;
	}

	private boolean unavailableSquareValue(int i, int j, int k) {
		int sqrow = (i / 3) * 3;
		int sqcol = (j / 3) * 3;

		for (int a = 0; a < 3; a++) {
			for (int b = 0; b < 3; b++) {
				int c = s.board[sqrow + a][sqcol + b];
				if (c < 0) {
					s.availability[sqrow + a][sqcol + b][k] = 0;
					boolean impossible = checkImpossible(i, j);
					if (impossible) {
						return true;
					}
				}
			}
		}

		return false;
	}

	private boolean unavailableColValue(int j, int k) {
		for (int i = 0; i < 9; i++) {
			if (s.board[i][j] == -1) {
				s.availability[i][j][k] = 0;
				boolean impossible = checkImpossible(i, j);
				if (impossible) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean unavailableRowValue(int i, int k) {
		for (int j = 0; j < 9; j++) {
			if (s.board[i][j] == -1) {
				s.availability[i][j][k] = 0;
				boolean impossible = checkImpossible(i, j);
				if (impossible) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkImpossible(int i, int j) {
		boolean impossible = true;
		for (int l = 1; l < 10; l++) {
			if (s.availability[i][j][l] == 1) {
				impossible = false;
				break;
			}
		}

		if (impossible) {
			return true;
		}

		return false;
	}

	private void availableSquareValue(int i, int j, int k) {
		int sqrow = (i / 3) * 3;
		int sqcol = (j / 3) * 3;

		for (int a = 0; a < 3; a++) {
			for (int b = 0; b < 3; b++) {
				int c = s.board[sqrow + a][sqcol + b];
				if (c == -1) {
					s.availability[i][j][k] = 1;
				}
			}
		}

	}

	private void availableColValue(int j, int k) {
		for (int i = 0; i < 9; i++) {
			if (s.board[i][j] == -1) {
				s.availability[i][j][k] = 1;
			}
		}
	}

	private void availableRowValue(int i, int k) {
		for (int j = 0; j < 9; j++) {
			if (s.board[i][j] == -1) {
				s.availability[i][j][k] = 1;
			}
		}
	}

	private void availability() {

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				s.availability[i][j] = availability(i, j);
			}
		}
	}

	private int[] availability(int i, int j) {

		if (s.board[i][j] >= 0) {
			return new int[10];
		}

		int[] r = new int[10];

		for (int k = 1; k < 10; k++) {
			r[k] = 1;
		}

		for (int k = 0; k < 9; k++) {
			int c = s.board[k][j];
			if (c > 0) {
				r[c] = 0;
			}
		}

		for (int k = 0; k < 9; k++) {
			int c = s.board[i][k];
			if (c > 0) {
				r[c] = 0;
			}
		}

		int sqrow = (i / 3) * 3;
		int sqcol = (j / 3) * 3;

		for (int a = 0; a < 3; a++) {
			for (int b = 0; b < 3; b++) {
				int c = s.board[sqrow + a][sqcol + b];
				if (c > 0) {
					r[c] = 0;
				}
			}
		}

		return r;
	}

}
