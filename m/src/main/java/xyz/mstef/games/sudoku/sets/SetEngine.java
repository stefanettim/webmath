package xyz.mstef.games.sudoku.sets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import xyz.mstef.games.sudoku.Engine;
import xyz.mstef.games.sudoku.Factory;
import xyz.mstef.games.sudoku.Sudoku;

public class SetEngine extends Engine {

	private ArrayList<String> logs = new ArrayList<String>();
	private boolean logging = true;

	private void log(String log) {
		if (logging) {
			logs.add(log);
		}
	}

	public Sudoku solve(Sudoku s) throws Exception {
		SetsSudoku ss = new SetsSudoku(s);
		ss.availability();

		if(ss.impossible()) {
			logs.add("IMPOSSIBLE");
			return s;
		}

		if(ss.getFilled()<17) {
			logs.add("Insert at least 17 values");
			return s;
		}

		SetsSudoku m = fill(ss);
		
		if(!m.solved()) {
			logs.add("#### Unsolved!! ###");
		} else {
			logs.add("Solved");
		}
		
		return m;
	}

	private SetsSudoku fill(SetsSudoku s) throws Exception {


		// OnlyValue
		int ava = -1;
		for (SudokuCell cell : SudokuCells.allCells) {

			if (s.isEmpty(cell)) {

				ava = s.getAvailability(cell);

				// IMPOSSIBLE
				if (ava <= 0) {
					log("!! Impossible " + cell);
					return s;
				}

				// only value
				if (ava == 1) {
					int v = s.getOnlyValue(cell);
					log(cell + "=" + v + " Only Value on cell");
					s.setValue(cell, v);
					return fill(s);
				}

			}
		}

		// HiddenSingle sets only values
		for (SudokuSet ss : SudokuSets.allSets) {
			for (int k = 1; k < 10; k++) {

				SudokuCell onlyCell = null;
				boolean onlyCellFound = false;

				for (SudokuCell test : ss.cells) {
					
					if (s.isEmpty(test) && s.getAvailability(test, k) == 1) {

						if (onlyCell == null) {
							onlyCell = test;
							onlyCellFound = true;
						} else {
							onlyCellFound = false;
							break;
						}
					}

				}

				if (onlyCellFound) {
					log(onlyCell + "=" + k + " Hidden Single for set " + ss);
					s.setValue(onlyCell, k);
					return fill(s);
				}
			}
		}

		// Subset intersections
		for (SudokuSet ss : SudokuSets.allSets) {

			for (int k = 1; k < 10; k++) {

				List<SudokuCell> cells = s.getAvailableCells(ss, k);

				if (!cells.isEmpty()) {

					for (SudokuSet so : SudokuSets.allSets) {

						if (!so.equals(ss)) {

							List<SudokuCell> subcells = s.getAvailableCells(so, k);

							// log("checking for "+k+" of "+so+"("+subcells.size()+") in
							// "+ss+"("+cells.size()+")");

							/*
							 * if(k==9 && so.toString()=="row1" && ss.toString()=="sqr2") { log("found"); }
							 */

							boolean subset = cells.containsAll(subcells);

							if (!subcells.isEmpty() && subset) {

								boolean removed = cells.removeAll(subcells);

								String removedString = "";

								if (removed && !cells.isEmpty()) {
									removedString = "Subset, available cells for " + k + " on " + so + "("
											+ subcells.size() + ") are a subset of " + ss + " cells, removing";

									for (SudokuCell r : cells) {
										removedString += " " + r;
										s.setUnavailable(r, k);
									}

									removedString += " for " + k;
									log(removedString);
									return fill(s);
								}

							}

						}

					}
				}

			}

		}

		// NakedPair
		for (SudokuSet ss : SudokuSets.allSets) {

			for (SudokuCell c : ss.cells) {

				if (s.getAvailability(c) == 2) {

					for (SudokuCell t : ss.cells) {

						if (!t.equals(c) && s.getAvailability(t) == 2) {

							if (s.compareAvailability(c, t)) {

								int a = -1;
								int b = -1;

								for (int k = 1; k < 10; k++) {
									if (s.getAvailability(c, k) == 1) {
										if (a < 0) {
											a = k;
										} else {
											b = k;
										}
									}
								}

								boolean found = false;
								String edited = "";

								for (SudokuCell x : ss.cells) {

									if (x != c && x != t) {

										boolean edit = false;
										if (s.getAvailability(x, a) == 1) {
											s.setUnavailable(x, a);
											edit = true;
										}
										if (s.getAvailability(x, b) == 1) {
											s.setUnavailable(x, b);
											edit = true;
										}
										if (edit) {
											found = true;
											edited += " " + x;
										}

									}

								}

								if (found) {
									log("NakedPair " + c + " " + t + " on " + ss + " removing " + a + " and " + b
											+ " from " + edited);
									return fill(s);
								}

							}

						}
					}

				}

			}

		}

		// XWing Rows
		for (SudokuSet ss : SudokuSets.rows) {

			for (int k = 1; k < 10; k++) {

				int a = -1;
				int b = -1;
				int r = -1;
				boolean pair = false;

				for (SudokuCell c : ss.cells) {

					r = c.row;

					if (s.isEmpty(c) && s.getAvailability(c, k) == 1) {
						if (a < 0) {
							a = c.col;
						} else if (b < 0) {
							b = c.col;
							pair = true;
						} else {
							pair = false;
						}
					}

				}

				if (pair) {
					for (SudokuSet tt : SudokuSets.rows) {
						if (tt != ss) {

							int at = -1;
							int bt = -1;
							int rt = -1;
							boolean pairt = false;

							for (SudokuCell t : tt.cells) {

								rt = t.row;

								if (s.isEmpty(t) && s.getAvailability(t, k) == 1) {
									if (at < 0) {
										at = t.col;
									} else if (bt < 0) {
										bt = t.col;
										pairt = true;
									} else {
										pairt = false;
									}
								}

							}

							if (pairt && a == at && b == bt) {

								String removedCells = "";
								boolean removed = false;

								for (int i = 0; i < 9; i++) {
									if (i != r && i != rt) {

										SudokuCell ra = new SudokuCell(i, a);
										if (s.isEmpty(ra) && s.getAvailability(ra, k) == 1) {
											s.setUnavailable(ra, k);
											removedCells += " " + ra;
											removed = true;
										}

										SudokuCell rb = new SudokuCell(i, b);
										if (s.isEmpty(rb) && s.getAvailability(rb, k) == 1) {
											s.setUnavailable(rb, k);
											removedCells += " " + rb;
											removed = true;
										}

									}
								}

								if (removed) {
									log("XWing Rows " + ss + " and " + tt + " for " + k + ", columns " + a + " and " + b
											+ ", removing " + removedCells + " for " + k);
									return fill(s);
								}
							}

						}

					}

				}
			}

		}

		// XWing Cols
		for (SudokuSet ss : SudokuSets.cols) {

			for (int k = 1; k < 10; k++) {

				int a = -1;
				int b = -1;
				int col = -1;
				boolean pair = false;

				for (SudokuCell c : ss.cells) {

					col = c.col;

					if (s.isEmpty(c) && s.getAvailability(c, k) == 1) {
						if (a < 0) {
							a = c.row;
						} else if (b < 0) {
							b = c.row;
							pair = true;
						} else {
							pair = false;
						}
					}

				}

				if (pair) {
					for (SudokuSet tt : SudokuSets.cols) {
						if (tt != ss) {

							int at = -1;
							int bt = -1;
							int rc = -1;
							boolean pairt = false;

							for (SudokuCell t : tt.cells) {

								rc = t.col;

								if (s.isEmpty(t) && s.getAvailability(t, k) == 1) {
									if (at < 0) {
										at = t.row;
									} else if (bt < 0) {
										bt = t.row;
										pairt = true;
									} else {
										pairt = false;
									}
								}

							}

							if (pairt && a == at && b == bt) {

								String removedCells = "";
								boolean removed = false;

								for (int j = 0; j < 9; j++) {
									if (j != col && j != rc) {

										SudokuCell ra = new SudokuCell(a, j);
										if (s.isEmpty(ra) && s.getAvailability(ra, k) == 1) {
											s.setUnavailable(ra, k);
											removedCells += " " + ra;
											removed = true;
										}

										SudokuCell rb = new SudokuCell(b, j);
										if (s.isEmpty(rb) && s.getAvailability(rb, k) == 1) {
											s.setUnavailable(rb, k);
											removedCells += " " + rb;
											removed = true;
										}

									}
								}

								if (removed) {
									// s.printAvailability();
									log("XWing Cols " + ss + " and " + tt + " for " + k + ", rows " + a + " and " + b
											+ ", removing " + removedCells + " for " + k);
									return fill(s);
								}
							}

						}

					}

				}
			}

		}

		// XY-Wing
		// http://www.settimanasudoku.it/xy-xy-wing-o-ali-doppie/
		for (SudokuCell c : SudokuCells.allCells) {
			if (s.isEmpty(c) && s.getAvailability(c) == 2) {

				int v1 = s.getFirstAvailability(c);
				int v2 = s.getSecondAvailability(c);

				for (SudokuSet ss : SudokuSets.setsMap.get(c.toString())) {
					for (SudokuCell x : ss.cells) {
						if (!x.equals(c) && s.isEmpty(x) && s.getAvailability(x) == 2) {

							/*
							 * if("H4".equals(c.toString())&&"H3".equals(x.toString())) {
							 * System.out.println("check "+c+"("+v1+","+v2+") "+x); }
							 */

							int x1 = s.getFirstAvailability(x);
							int x2 = s.getSecondAvailability(x);
							int a1 = -1;
							int a2 = -1;

							if (x1 == v1 && x2 != v2) {
								a1 = x1;
								a2 = x2;
							}

							if (x2 == v1 && x1 != v2) {
								a1 = x2;
								a2 = x1;
							}

							if (x1 == v2 && x2 != v1) {
								a1 = x1;
								a2 = x2;
							}

							if (x2 == v2 && x1 != v1) {
								a1 = x2;
								a2 = x1;
							}

							if (a1 > 0 && a2 > 0) {
								SudokuCell a = x;

								/*
								 * if("H4".equals(c.toString())) {
								 * System.out.println("prep "+c+"("+v1+","+v2+") "+a+"("+a1+","+a2+") "); }
								 */

								for (SudokuSet sb : SudokuSets.setsMap.get(c.toString())) {
									for (SudokuCell y : sb.cells) {
										if (!y.equals(c) && !y.equals(a) && s.isEmpty(y) && s.getAvailability(y) == 2) {

											int xb1 = s.getFirstAvailability(y);
											int xb2 = s.getSecondAvailability(y);
											int b1 = -1;
											int b2 = -1;

											if (xb1 == v1 && xb2 != v2) {
												b1 = xb1;
												b2 = xb2;
											}

											if (xb2 == v1 && xb1 != v2) {
												b1 = xb2;
												b2 = xb1;
											}

											if (xb1 == v2 && xb2 != v1) {
												b1 = xb1;
												b2 = xb2;
											}

											if (xb2 == v2 && xb1 != v1) {
												b1 = xb2;
												b2 = xb1;
											}

											if (b1 > 0 && b2 > 0 && b1 != a1 && b2 == a2 && a.col != y.col
													&& a.row != y.row) {
												SudokuCell b = y;

												String editedString = "";
												boolean edited = false;

												SudokuCell i1 = new SudokuCell(a.row, b.col);
												if (s.isEmpty(i1) && s.getAvailability(i1, a2) == 1) {
													s.setUnavailable(i1, a2);
													edited = true;
													editedString += i1;
												}

												SudokuCell i2 = new SudokuCell(b.row, a.col);
												if (s.isEmpty(i2) && s.getAvailability(i2, a2) == 1) {
													s.setUnavailable(i2, a2);
													edited = true;
													editedString += i2;
												}

												if (edited) {
													log("XY-Wing " + c + "(" + v1 + "," + v2 + ") " + a + "(" + a1 + ","
															+ a2 + ") " + " " + b + "(" + b1 + "," + b2
															+ "), excluding " + editedString + " for " + a2);
													return fill(s);
												}

											}
										}
									}
								}
							}
						}
					}
				}

			}

		}

		// HiddenPair
		for (SudokuSet ss : SudokuSets.allSets) {

			for (SudokuCell a : ss.cells) {

				if (s.isEmpty(a)) {

					ArrayList<Integer> la = s.getValues(a);

					for (Integer av : la) {

						for (Integer bv : la) {

							if (bv > av) {

								for (SudokuCell b : ss.cells) {

									if (s.isEmpty(b) && !b.equals(a) && s.getAvailability(b, av) == 1
											&& s.getAvailability(b, bv) == 1) {

										boolean elsewhere = false;
										for (SudokuCell x : ss.cells) {
											if (s.isEmpty(x) && !a.equals(x) && !b.equals(x)) {
												if (s.getAvailability(x, av) == 1) {
													elsewhere = true;
												}
												if (s.getAvailability(x, bv) == 1) {
													elsewhere = true;
												}
											}
										}

										if (!elsewhere) {

											String removedString = "";
											boolean removed = false;

											for (SudokuCell x : new SudokuCell[] { a, b }) {
												for (int k = 1; k < 10; k++) {
													if (k != av && k != bv && s.getAvailability(x, k) == 1) {
														s.setUnavailable(x, k);
														removedString += x + "(" + k + ") ";
														removed = true;
													}
												}
											}

											if (removed) {

												log("HiddenPair " + a + "(" + av + "," + bv + ") " + b + "(" + av + ","
														+ bv + ") " + ", excluding " + removedString);
												return fill(s);
											}

										}

									}
								}

							}

						}
					}

				}
			}

		}

		// NakedSet
		for (SudokuSet ss : SudokuSets.allSets) {

			List<SudokuCell> sc = ss.filterEmptyCells(s);

			NakedSets nakedSets = new NakedSets();
			nakedSets.search(s, sc, null, 0);

			for (NakedSet ns : nakedSets.sets) {

				boolean edit = false;
				String editedString = "";

				for (SudokuCell c : sc) {

					if (!ns.cells.contains(c)) {

						for (Integer i : ns.values) {

							if (s.getAvailability(c, i) == 1) {
								s.setUnavailable(c, i);
								editedString += c + "(" + i + ") ";
								edit = true;
							}
						}

					}

				}

				if (edit) {
					log("NakedSet candidate for set " + ss + ", " + ns.toString() + ", removed " + editedString);
					return fill(s);
				}

			}

		}

		// Brute
		if (!s.solved()) {

			log("BRUTE FORCE");
			logging = false;
			SetsSudoku bs1 = brute(s);
			if (bs1.solved()) {
				return bs1;
			}

		}

		return s;
	}

	private SetsSudoku brute(SetsSudoku s) throws Exception {

		SudokuCell guess = null;

		int ava = 10;
		for (SudokuCell cell : SudokuCells.allCells) {
			if (s.isEmpty(cell)) {
				if (s.getAvailability(cell) < ava) {
					guess = cell;
					ava = s.getAvailability(cell);
					if (ava == 2) {
						break;
					}
				}
			}
		}

		if (guess != null) {

			for (int k : s.getValues(guess)) {

				log("-- Brute Force " + guess + " : " + k);

				SetsSudoku sc = s.clone();
				sc.setValue(guess, k);

				int avax = -1;
				boolean impossible = false;
				for (SudokuCell cell : SudokuCells.allCells) {

					if (s.isEmpty(cell)) {

						avax = s.getAvailability(cell);

						// IMPOSSIBLE
						if (avax <= 0) {
							impossible = true;
						}

					}
				}

				if (!impossible) {
					for (SudokuSet ss : SudokuSets.allSets) {

						SudokuCell onlyCell = null;
						boolean onlyCellFound = false;

						for (SudokuCell test : ss.cells) {

							if (s.isEmpty(test) && sc.getAvailability(test, k) == 1) {

								if (onlyCell == null) {
									onlyCell = test;
									onlyCellFound = true;
								} else {
									onlyCellFound = false;
									break;
								}
							}

						}

						if (onlyCellFound) {
							sc.setValue(onlyCell, k);
						}

					}

					SetsSudoku m = fill(sc);
					if (m.solved()) {
						return m;
					}

				}
			}
		}
		return s;
	}

	public ArrayList<String> getLogs() {
		return logs;
	}

	public void setLogs(ArrayList<String> logs) {
		this.logs = logs;
	}

	public static void main(String[] args) throws IOException, Exception {
		int tot = 0;
		int solved = 0;

		Engine e = new SetEngine();

		for (Sudoku p : Factory.getTop95Problems()) {

			Sudoku r = e.solve(p);
			boolean solveSudoku = r.solved();
			if (solveSudoku) {
				solved++;
			} else {
				System.out.println("UNSOLVED");
				System.out.println(p.toLine());
				p.print();
				r.print();
				r.printAvailability();
			}
			tot++;
			System.out.println("===============================================================");
		}

		System.out.println("List : " + solved + " / " + tot);

	}
}
