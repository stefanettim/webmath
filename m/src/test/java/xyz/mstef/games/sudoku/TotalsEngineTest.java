package xyz.mstef.games.sudoku;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TotalsEngineTest extends AbstractBasicEngineTest {

	@BeforeAll
	public static void beforeAll() {
		e = new TotalsEngine();
	}

	@Test
	public void engineVeryEasyTest() throws Exception {

		Sudoku s = Factory.getVeryEasySudokuSolution();
		Sudoku p = Factory.getVeryEasySudokuProblem();
		solveSudoku(e, s, p);
	}

	@Test
	public void engineEasyTest() throws Exception {

		Sudoku s = Factory.getEasySudokuSolution();
		Sudoku p = Factory.getEasySudokuProblem();
		solveSudoku(e, s, p);
	}

	@Test
	public void engineWikiTest() throws Exception {

		Sudoku s = Factory.getWikiSudokuSolution();
		Sudoku p = Factory.getWikiSudokuProblem();
		solveSudoku(e, s, p);
	}

	@Test
	public void engineDiabolicalTest() throws Exception {

		Sudoku s = Factory.getDiabolicalSudokuSolution();
		Sudoku p = Factory.getDiabolicalSudokuProblem();
		solveSudoku(e, s, p);
	}

	// @Test
	public void engineEmptyTest() throws Exception {

		Sudoku s = Factory.getEmptySudoku();
		Sudoku p = e.solve(s);
		p.print();
	}

	// @Test
	public void engineXWingTest() throws Exception {

		Sudoku s = Factory.getXWingSudokuProblem();
		Sudoku p = e.solve(s);
		p.print();
	}

	@Test
	public void engineP001Test() throws Exception {

		Sudoku s = Factory.getP001Problem();
		Sudoku p = e.solve(s);
		p.print();
	}
}
