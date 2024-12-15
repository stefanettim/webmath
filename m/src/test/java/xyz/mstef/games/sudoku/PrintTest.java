package xyz.mstef.games.sudoku;

import org.junit.jupiter.api.Test;

public class PrintTest {

	@Test
	public void printTest() {

		Sudoku b = Factory.getWikiSudokuProblem();
		b.print();

		Sudoku f = Factory.getWikiSudokuSolution();
		f.print();
	}

}
