package xyz.mstef.games.sudoku;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class SudokuTest {

	@Test
	public void cloneTest() {

		Sudoku b = Factory.getWikiSudokuSolution();

		b.print();

		Sudoku m = b.clone();

		m.print();

	}

	@Test
	public void stringTest() {
		Sudoku m = new Sudoku("4.....8.5.3..........7......2.....6.....8.4......1.......6.3.7.5..2.....1.4......");
		m.print();
	}

	@Test
	public void top95Test() throws IOException {

		for (Sudoku s : Factory.getTop95Problems()) {
			s.print();
		}

	}

}
