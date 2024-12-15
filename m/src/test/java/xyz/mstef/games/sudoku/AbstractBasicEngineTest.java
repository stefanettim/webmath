package xyz.mstef.games.sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public abstract class AbstractBasicEngineTest {

	public static Engine e;

	public boolean solveSudoku(Engine e, Sudoku p) throws Exception {
		return solveSudoku(e, null, p, true);
	}

	public boolean solveSudoku(Engine e, Sudoku s, Sudoku p) throws Exception {
		return solveSudoku(e, s, p, true);
	}

	public boolean solveSudoku(Engine e, Sudoku p, boolean b) throws Exception {
		return solveSudoku(e, null, p, b);
	}

	public boolean solveSudoku(Engine e, Sudoku s, Sudoku p, boolean validate) throws Exception {

		System.out.println("============================================");
		System.out.println("Problem");
		p.print();

		Sudoku r = e.solve(p);
		Assertions.assertNotNull(r);

		if (r.availability != null) {
			r.printAvailability();
		}

		r.print();

		boolean solved = r.solved();
		if (validate) {
			assert (solved);
		}

		System.out.println("Solution Found");

		if (s != null) {
			boolean t = s.equals(r);
			if (validate) {
				Assertions.assertTrue(t);
			}
		}

		return solved;
	}

	@Test
	public void wikiOneMissingFullTest() throws Exception {
		Sudoku s = Factory.getWikiSudokuSolution();
		Sudoku p = s.clone();
		p.board[8][8] = -1;

		solveSudoku(e, s, p);
	}

	@Test
	public void wikiFourMissingTest() throws Exception {
		Sudoku s = Factory.getWikiSudokuSolution();
		Sudoku p = s.clone();
		p.board[8][8] = -1;
		p.board[8][7] = -1;
		p.board[8][6] = -1;
		p.board[8][5] = -1;

		solveSudoku(e, s, p);
	}

	@Test
	public void easyOneMissingTest() throws Exception {
		Sudoku s = Factory.getEasySudokuSolution();
		Sudoku p = s.clone();
		p.board[8][8] = -1;

		solveSudoku(e, s, p);

	}

	@Test
	public void easyFourMissingTest() throws Exception {
		Sudoku s = Factory.getEasySudokuSolution();
		Sudoku p = s.clone();
		p.board[8][8] = -1;
		p.board[8][7] = -1;
		p.board[8][6] = -1;
		p.board[8][5] = -1;

		solveSudoku(e, s, p);
	}

	@Test
	public void easyTwelveMissingTest() throws Exception {
		Sudoku s = Factory.getEasySudokuSolution();
		Sudoku p = s.clone();
		p.board[8][8] = -1;
		p.board[8][7] = -1;
		p.board[8][6] = -1;
		p.board[8][5] = -1;
		p.board[8][4] = -1;
		p.board[8][3] = -1;
		p.board[8][2] = -1;
		p.board[8][1] = -1;
		p.board[8][0] = -1;
		p.board[7][8] = -1;
		p.board[7][7] = -1;
		p.board[7][6] = -1;

		solveSudoku(e, s, p);
	}

}
