package xyz.mstef.games.sudoku;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import xyz.mstef.games.sudoku.sets.SetEngine;
import xyz.mstef.games.sudoku.sets.SudokuCell;
import xyz.mstef.games.sudoku.sets.SudokuSet;
import xyz.mstef.games.sudoku.sets.SudokuSets;

public class SetEngineTest extends AbstractBasicEngineTest {

	@BeforeAll
	public static void beforeAll() {
		e = new SetEngine();
	}

	@Test
	public void printTest() {

		Sudoku s = new Sudoku();

		SudokuSet[] sets = SudokuSets.allSets;

		for (SudokuSet ss : sets) {
			for (SudokuCell c : ss.cells) {
				s.board[c.row][c.col] = 1;
			}
			s.print();
			s = Factory.getEmptySudoku();
		}

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

	@Test
	public void engineP001Test() throws Exception {
		Sudoku p = Factory.getP001Problem();
		solveSudoku(e, p);
	}

	@Test
	public void engineXWingTest() throws Exception {
		Sudoku p = Factory.getXWingSudokuProblem();
		solveSudoku(e, p);
	}

	// HiddenPair
	// 3...8.......7....51.......3......36...2..4....7...........6.13..452...........85.
	@Test
	public void hiddenPairTest() throws Exception {
		Sudoku p = new Sudoku("3...8.......7....51.......3......36...2..4....7...........6.13..452...........85.");
		solveSudoku(e, p);
	}

	// NakedTriple
	// .....2.......7...17..3...9.8..7......2.89.6...13..6....9..5.824.....891..........
	// https://www.sudokuwiki.org/sudoku.htm -> X-CYCLE, not implemented here
	// https://www.sudoku-solutions.com/ BRUTE
	// no solotion found, only BRUTE
	@Test
	public void nakedTripleTest() throws Exception {
		Sudoku p = new Sudoku(".....2.......7...17..3...9.8..7......2.89.6...13..6....9..5.824.....891..........");
		solveSudoku(e, p, false);
	}

	@Test
	public void easy50Test() throws Exception {
		problemListTest("easy50", Factory.getEasy50Problems());
	}

	@Test
	public void top95Test() throws Exception {
		problemListTest("top95", Factory.getTop95Problems());
	}

	@Test
	public void hardestTest() throws Exception {
		problemListTest("hardest", Factory.getHardestProblems());
	}

	@Test
	public void marcoTest() throws Exception {
		problemListTest("marco", Factory.readFile("marco.txt"));
	}

	public void problemListTest(String name, List<Sudoku> list) throws Exception {

		int tot = 0;
		int solved = 0;

		for (Sudoku p : list) {

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
		}

		System.out.println("List " + name + " : " + solved + " / " + tot);
		Assertions.assertEquals(tot, solved);
	}

}
