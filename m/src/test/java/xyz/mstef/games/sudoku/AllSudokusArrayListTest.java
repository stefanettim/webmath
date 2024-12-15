package xyz.mstef.games.sudoku;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AllSudokusArrayListTest  {

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

			Map<String, List<Integer>> values = 
					AllSudokusArrayList.parse(p.toLine());
			
			Map<String, List<Integer>> res = AllSudokusArrayList.search(values);
			
			boolean solveSudoku = res!=null;
			if (solveSudoku) {
				solved++;
			} else {
				System.out.println("UNSOLVED");
				System.out.println(p.toLine());
				p.print();
				AllSudokusArrayList.print(values);
			}
			tot++;
		}

		System.out.println("List " + name + " : " + solved + " / " + tot);
		Assertions.assertEquals(tot, solved);
	}

}
