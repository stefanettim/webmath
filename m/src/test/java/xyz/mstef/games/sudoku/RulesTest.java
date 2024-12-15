package xyz.mstef.games.sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RulesTest {

	@Test
	public void completedWikiTest() {

		Sudoku b = Factory.getWikiSudokuSolution();

		boolean c = b.solved();

		Assertions.assertTrue(c);
	}

	@Test
	public void completedPeterTest() {

		Sudoku b = Factory.getEasySudokuSolution();

		boolean c = b.solved();

		Assertions.assertTrue(c);
	}

	@Test
	public void rowCheck() {

		Sudoku b = Factory.getWikiSudokuProblem();

		{
			boolean v = b.chechRow(0, 2, 5);
			Assertions.assertFalse(v);
		}

		{
			boolean v = b.chechRow(0, 2, 1);
			Assertions.assertTrue(v);
		}
	}

	@Test
	public void colCheck() {

		Sudoku b = Factory.getWikiSudokuProblem();

		{
			boolean v = b.chechCol(2, 0, 5);
			Assertions.assertFalse(v);
		}

		{
			boolean v = b.chechCol(2, 0, 1);
			Assertions.assertTrue(v);
		}
	}

	@Test
	public void squareCheck() {

		Sudoku b = Factory.getWikiSudokuProblem();

		{
			boolean v = b.chechSquare(0, 2, 9);
			Assertions.assertFalse(v);
		}

		{
			boolean v = b.chechSquare(0, 2, 1);
			Assertions.assertTrue(v);
		}
	}

}
