package xyz.mstef.games.sudoku;

import java.util.TreeMap;

public class TotalsEngine extends Engine {

	Sudoku s;

	public Sudoku solve(Sudoku s) throws Exception {

		this.s = s;
		availability();

		Sudoku r = solve(s, 0);

		if (r != null) {
			System.out.println("Solved");
			r.print();

			if (!r.solved()) {
				throw new Exception("Unsolved");
			}

			return r;
		}

		return null;
	}

	private Sudoku solve(Sudoku s, int lev) {

		Sudoku m = fillMustBe(s);

		TreeMap<Integer, Integer[]> sortedAv = new TreeMap<Integer, Integer[]>();

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (m.board[i][j] == -1) {
					Integer key = m.availability[i][j][0] * 100 + i * 10 + j;
					Integer[] coordinates = new Integer[] { i, j };
					sortedAv.put(key, coordinates);
				}
			}
		}

		for (Integer[] coordinates : sortedAv.values()) {
			int a = coordinates[0];
			int b = coordinates[1];
			Sudoku g = guess(m, a, b, lev);
			if (g != null) {
				return g;
			}
		}

		if (lev == 0) {
			m.print();
		}

		if (m.solved()) {
			return m;
		}

		return null;
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

	private Sudoku guess(Sudoku s, int i, int j, int lev) {
		for (int k = 1; k < 10; k++) {

			if (s.availability[i][j][k] == 1) {

				if (lev < 50) {
					System.out.println("Guessing lev:" + lev + " row:" + i + " col:" + j + " val:" + k);
				}

				s.board[i][j] = k;

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
					s.availability[sqrow + a][sqcol + b][0]--;
					// boolean impossible = checkImpossible(i,j);
					// if(impossible) {
					// return true;
					// }
				}
			}
		}

		return false;
	}

	private boolean unavailableColValue(int j, int k) {
		for (int i = 0; i < 9; i++) {
			if (s.board[i][j] == -1) {
				s.availability[i][j][k] = 0;
				s.availability[i][j][0]--;
				// boolean impossible = checkImpossible(i,j);
				// if(impossible) {
				// return true;
				// }
			}
		}
		return false;
	}

	private boolean unavailableRowValue(int i, int k) {
		for (int j = 0; j < 9; j++) {
			if (s.board[i][j] == -1) {
				s.availability[i][j][k] = 0;
				s.availability[i][j][0]--;
				// boolean impossible = checkImpossible(i,j);
				// if(impossible) {
				// return true;
				// }
			}
		}
		return false;
	}

	public boolean checkImpossible(int i, int j) {
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
					s.availability[i][j][0]++;
				}
			}
		}

	}

	private void availableColValue(int j, int k) {
		for (int i = 0; i < 9; i++) {
			if (s.board[i][j] == -1) {
				s.availability[i][j][k] = 1;
				s.availability[i][j][0]++;
			}
		}
	}

	private void availableRowValue(int i, int k) {
		for (int j = 0; j < 9; j++) {
			if (s.board[i][j] == -1) {
				s.availability[i][j][k] = 1;
				s.availability[i][j][0]++;
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
