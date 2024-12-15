package xyz.mstef.games.sudoku;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AllSudokusTest  {

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

	// https://github.com/mharrys/sudoku/blob/master/tests/data/collections/hardest
	@Test
	public void hardest2Test() throws Exception {
		problemListTest("hardest2", Factory.readFile("hardest2.txt"));
	}

	@Test
	public void marcoTest() throws Exception {
		problemListTest("marco", Factory.readFile("marco.txt"));
	}

	//@Test
	public void all17Test() throws Exception {
		problemListTest("all_17", Factory.readFile("all_17.txt"));
	}

	public void problemListTest(String name, List<Sudoku> list) throws Exception {

		int tot = 0;
		int solved = 0;

		for (Sudoku p : list) {

			Map<String, String> values = 
					AllSudokus.parse(p.toLine());
			
			Map<String, String> res = AllSudokus.search(values);
			
			boolean solveSudoku = res!=null;
			if (solveSudoku) {
				solved++;
			} else {
				System.out.println("UNSOLVED");
				System.out.println(p.toLine());
				p.print();
				AllSudokus.print(values);
			}
			tot++;
			
			if(tot%1000==0) {
				System.out.println("Progress : "+tot);
			}
		}

		System.out.println("List " + name + " : " + solved + " / " + tot);
		Assertions.assertEquals(tot, solved);
	}

}
