package xyz.mstef.games.sudoku;

public class SimpleEngine extends Engine {

	public Sudoku solve(Sudoku s) {
		return solve(s, 0);
	}

	public Sudoku solve(Sudoku s, int lev) {

		for (int i = 0; i < 9; i++) {

			for (int j = 0; j < 9; j++) {

				if (s.board[i][j] == -1) {

					for (int v = 1; v <= 9; v++) {

						if (lev < 60) {
							for (int l = 0; l < lev; l++) {
								System.out.print("-");
							}
							System.out.println("lev:" + lev + " row:" + i + " col:" + j + " val:" + v);
						}

						boolean r = s.chechRow(i, j, v);
						if (!r) {
							continue;
						}
						boolean c = s.chechCol(i, j, v);
						if (!c) {
							continue;
						}
						boolean q = s.chechSquare(i, j, v);
						if (!q) {
							continue;
						}

						s.board[i][j] = v;

						if ((i == 8) && (j == 8))
							break;

						Sudoku o = solve(s, lev + 1);
						if (o != null) {
							return o;
						}

						s.board[i][j] = -1;
					}
				}
			}
		}

		if (s.solved()) {
			return s;
		}

		return null;
	}

}
