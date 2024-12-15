package xyz.mstef.games.sudoku;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AvEngineTest extends AbstractBasicEngineTest {

	@BeforeAll
	public static void beforeAll() {
		e = new AvEngine();
	}

	@Test
	public void engineVeryEasyTest() throws Exception {

		Sudoku s = Factory.getVeryEasySudokuSolution();
		Sudoku p = Factory.getVeryEasySudokuProblem();
		solveSudoku(e, s, p);
	}

}
