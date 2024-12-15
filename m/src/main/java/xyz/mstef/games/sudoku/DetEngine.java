package xyz.mstef.games.sudoku;

public class DetEngine extends Engine {

	Sudoku s;

	public Sudoku solve(Sudoku s) throws Exception {

		this.s = s;
		availability();
		Sudoku m = fillMustBe(s);
		return m;
	}

	private Sudoku fillMustBe(Sudoku s) {

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (s.board[i][j] == -1) {
					for (int k = 1; k < 10; k++) {

						boolean possibleElsewhere = false;

						for (int x = 0; x < 9; x++) {
							if (x != j
									&& (s.board[i][x] == k || (s.board[i][x] == -1 && s.availability[i][x][k] == 1))) {
								possibleElsewhere = true;
							}
						}

						if (!possibleElsewhere) {
							System.out.println("---- mustBeCol row:" + i + " col:" + j + " val:" + k);
							Sudoku m = s.clone();
							m.board[i][j] = k;
							m.availability[i][j][k] = 0;
							m.availability[i][j][0]--;
							return fillMustBe(m);
						}

						possibleElsewhere = false;
						for (int x = 0; x < 9; x++) {
							if (x != i
									&& (s.board[x][j] == k || (s.board[x][j] == -1 && s.availability[x][j][k] == 1))) {
								possibleElsewhere = true;
							}
						}

						if (!possibleElsewhere) {
							System.out.println("---- mustBeRow row:" + i + " col:" + j + " val:" + k);
							Sudoku m = s.clone();
							m.board[i][j] = k;
							m.availability[i][j][k] = 0;
							m.availability[i][j][0]--;
							return fillMustBe(m);
						}

						possibleElsewhere = false;
						int sqrow = (i / 3) * 3;
						int sqcol = (j / 3) * 3;

						for (int a = 0; a < 3; a++) {
							for (int b = 0; b < 3; b++) {

								int m = sqrow + a;
								int n = sqcol + b;

								if (!(m == i && n == j) && (s.board[m][n] == k
										|| (s.board[m][n] == -1 && s.availability[m][n][k] == 1))) {
									possibleElsewhere = true;
								}

							}
						}

						if (!possibleElsewhere) {
							System.out.println("---- mustBeSquare row:" + i + " col:" + j + " val:" + k);
							Sudoku m = s.clone();
							m.board[i][j] = k;
							m.availability[i][j][k] = 0;
							m.availability[i][j][0]--;
							return fillMustBe(m);
						}

					}
				}
			}
		}

		return s;
	}

	private void availability() {

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				s.availability[i][j] = availability(i, j);
			}
		}
	}

	private int[] availability(int i, int j) {

		int[] r = new int[10];

		if (s.board[i][j] >= 0) {
			return r;
		}

		r[0] = 9;

		for (int k = 1; k < 10; k++) {
			r[k] = 1;
		}

		for (int k = 0; k < 9; k++) {
			int c = s.board[k][j];
			if (c > 0 && r[c] == 1) {
				r[c] = 0;
				r[0]--;
			}
		}

		for (int k = 0; k < 9; k++) {
			int c = s.board[i][k];
			if (c > 0 && r[c] == 1) {
				r[c] = 0;
				r[0]--;
			}
		}

		int sqrow = (i / 3) * 3;
		int sqcol = (j / 3) * 3;

		for (int a = 0; a < 3; a++) {
			for (int b = 0; b < 3; b++) {
				int c = s.board[sqrow + a][sqcol + b];
				if (c > 0 && r[c] == 1) {
					r[c] = 0;
					r[0]--;
				}
			}
		}

		return r;
	}

}
