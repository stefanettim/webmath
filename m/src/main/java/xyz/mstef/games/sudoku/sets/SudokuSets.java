package xyz.mstef.games.sudoku.sets;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class SudokuSets {

	public static SudokuSet row0 = new SudokuSet("row1",
			new int[][] { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 3 }, { 0, 4 }, { 0, 5 }, { 0, 6 }, { 0, 7 }, { 0, 8 } });
	public static SudokuSet row1 = new SudokuSet("row2",
			new int[][] { { 1, 0 }, { 1, 1 }, { 1, 2 }, { 1, 3 }, { 1, 4 }, { 1, 5 }, { 1, 6 }, { 1, 7 }, { 1, 8 } });
	public static SudokuSet row2 = new SudokuSet("row3",
			new int[][] { { 2, 0 }, { 2, 1 }, { 2, 2 }, { 2, 3 }, { 2, 4 }, { 2, 5 }, { 2, 6 }, { 2, 7 }, { 2, 8 } });
	public static SudokuSet row3 = new SudokuSet("row4",
			new int[][] { { 3, 0 }, { 3, 1 }, { 3, 2 }, { 3, 3 }, { 3, 4 }, { 3, 5 }, { 3, 6 }, { 3, 7 }, { 3, 8 } });
	public static SudokuSet row4 = new SudokuSet("row5",
			new int[][] { { 4, 0 }, { 4, 1 }, { 4, 2 }, { 4, 3 }, { 4, 4 }, { 4, 5 }, { 4, 6 }, { 4, 7 }, { 4, 8 } });
	public static SudokuSet row5 = new SudokuSet("row6",
			new int[][] { { 5, 0 }, { 5, 1 }, { 5, 2 }, { 5, 3 }, { 5, 4 }, { 5, 5 }, { 5, 6 }, { 5, 7 }, { 5, 8 } });
	public static SudokuSet row6 = new SudokuSet("row7",
			new int[][] { { 6, 0 }, { 6, 1 }, { 6, 2 }, { 6, 3 }, { 6, 4 }, { 6, 5 }, { 6, 6 }, { 6, 7 }, { 6, 8 } });
	public static SudokuSet row7 = new SudokuSet("row8",
			new int[][] { { 7, 0 }, { 7, 1 }, { 7, 2 }, { 7, 3 }, { 7, 4 }, { 7, 5 }, { 7, 6 }, { 7, 7 }, { 7, 8 } });
	public static SudokuSet row8 = new SudokuSet("row9",
			new int[][] { { 8, 0 }, { 8, 1 }, { 8, 2 }, { 8, 3 }, { 8, 4 }, { 8, 5 }, { 8, 6 }, { 8, 7 }, { 8, 8 } });

	public static SudokuSet[] rows;

	public static SudokuSet col0 = new SudokuSet("col1",
			new int[][] { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 3, 0 }, { 4, 0 }, { 5, 0 }, { 6, 0 }, { 7, 0 }, { 8, 0 } });
	public static SudokuSet col1 = new SudokuSet("col2",
			new int[][] { { 0, 1 }, { 1, 1 }, { 2, 1 }, { 3, 1 }, { 4, 1 }, { 5, 1 }, { 6, 1 }, { 7, 1 }, { 8, 1 } });
	public static SudokuSet col2 = new SudokuSet("col3",
			new int[][] { { 0, 2 }, { 1, 2 }, { 2, 2 }, { 3, 2 }, { 4, 2 }, { 5, 2 }, { 6, 2 }, { 7, 2 }, { 8, 2 } });
	public static SudokuSet col3 = new SudokuSet("col4",
			new int[][] { { 0, 3 }, { 1, 3 }, { 2, 3 }, { 3, 3 }, { 4, 3 }, { 5, 3 }, { 6, 3 }, { 7, 3 }, { 8, 3 } });
	public static SudokuSet col4 = new SudokuSet("col5",
			new int[][] { { 0, 4 }, { 1, 4 }, { 2, 4 }, { 3, 4 }, { 4, 4 }, { 5, 4 }, { 6, 4 }, { 7, 4 }, { 8, 4 } });
	public static SudokuSet col5 = new SudokuSet("col6",
			new int[][] { { 0, 5 }, { 1, 5 }, { 2, 5 }, { 3, 5 }, { 4, 5 }, { 5, 5 }, { 6, 5 }, { 7, 5 }, { 8, 5 } });
	public static SudokuSet col6 = new SudokuSet("col7",
			new int[][] { { 0, 6 }, { 1, 6 }, { 2, 6 }, { 3, 6 }, { 4, 6 }, { 5, 6 }, { 6, 6 }, { 7, 6 }, { 8, 6 } });
	public static SudokuSet col7 = new SudokuSet("col8",
			new int[][] { { 0, 7 }, { 1, 7 }, { 2, 7 }, { 3, 7 }, { 4, 7 }, { 5, 7 }, { 6, 7 }, { 7, 7 }, { 8, 7 } });
	public static SudokuSet col8 = new SudokuSet("col9",
			new int[][] { { 0, 8 }, { 1, 8 }, { 2, 8 }, { 3, 8 }, { 4, 8 }, { 5, 8 }, { 6, 8 }, { 7, 8 }, { 8, 8 } });

	public static SudokuSet[] cols;

	public static SudokuSet sqr0 = new SudokuSet("sqr1",
			new int[][] { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 0 }, { 1, 1 }, { 1, 2 }, { 2, 0 }, { 2, 1 }, { 2, 2 } });
	public static SudokuSet sqr1 = new SudokuSet("sqr2",
			new int[][] { { 0, 3 }, { 0, 4 }, { 0, 5 }, { 1, 3 }, { 1, 4 }, { 1, 5 }, { 2, 3 }, { 2, 4 }, { 2, 5 } });
	public static SudokuSet sqr2 = new SudokuSet("sqr3",
			new int[][] { { 0, 6 }, { 0, 7 }, { 0, 8 }, { 1, 6 }, { 1, 7 }, { 1, 8 }, { 2, 6 }, { 2, 7 }, { 2, 8 } });
	public static SudokuSet sqr3 = new SudokuSet("sqr4",
			new int[][] { { 3, 0 }, { 3, 1 }, { 3, 2 }, { 4, 0 }, { 4, 1 }, { 4, 2 }, { 5, 0 }, { 5, 1 }, { 5, 2 } });
	public static SudokuSet sqr4 = new SudokuSet("sqr5",
			new int[][] { { 3, 3 }, { 3, 4 }, { 3, 5 }, { 4, 3 }, { 4, 4 }, { 4, 5 }, { 5, 3 }, { 5, 4 }, { 5, 5 } });
	public static SudokuSet sqr5 = new SudokuSet("sqr6",
			new int[][] { { 3, 6 }, { 3, 7 }, { 3, 8 }, { 4, 6 }, { 4, 7 }, { 4, 8 }, { 5, 6 }, { 5, 7 }, { 5, 8 } });
	public static SudokuSet sqr6 = new SudokuSet("sqr7",
			new int[][] { { 6, 0 }, { 6, 1 }, { 6, 2 }, { 7, 0 }, { 7, 1 }, { 7, 2 }, { 8, 0 }, { 8, 1 }, { 8, 2 } });
	public static SudokuSet sqr7 = new SudokuSet("sqr8",
			new int[][] { { 6, 3 }, { 6, 4 }, { 6, 5 }, { 7, 3 }, { 7, 4 }, { 7, 5 }, { 8, 3 }, { 8, 4 }, { 8, 5 } });
	public static SudokuSet sqr8 = new SudokuSet("sqr9",
			new int[][] { { 6, 6 }, { 6, 7 }, { 6, 8 }, { 7, 6 }, { 7, 7 }, { 7, 8 }, { 8, 6 }, { 8, 7 }, { 8, 8 } });

	public static SudokuSet[] sqrs;

	public static SudokuSet[] allSets;

	public static TreeMap<String, Set<SudokuSet>> setsMap;

	static {
		rows = new SudokuSet[] { row0, row1, row2, row3, row4, row5, row6, row7, row8 };
		cols = new SudokuSet[] { col0, col1, col2, col3, col4, col5, col6, col7, col8 };
		sqrs = new SudokuSet[] { sqr0, sqr1, sqr2, sqr3, sqr4, sqr5, sqr6, sqr7, sqr8 };
		allSets = new SudokuSet[] { row0, row1, row2, row3, row4, row5, row6, row7, row8, col0, col1, col2, col3, col4,
				col5, col6, col7, col8, sqr0, sqr1, sqr2, sqr3, sqr4, sqr5, sqr6, sqr7, sqr8 };

		setsMap = new TreeMap<String, Set<SudokuSet>>();
		for (SudokuSet s : allSets) {
			for (SudokuCell c : s.cells) {
				String key = c.toString();
				Set<SudokuSet> cellSets = setsMap.get(key);
				if (cellSets == null) {
					cellSets = new HashSet<>();
					setsMap.put(key, cellSets);
				}
				cellSets.add(s);
			}
		}
	}

}
