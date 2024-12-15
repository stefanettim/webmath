package xyz.mstef.games.sudoku.sets;

import java.util.ArrayList;
import java.util.List;

public class NakedSets {

	List<NakedSet> sets = new ArrayList<NakedSet>();

	public void search(SetsSudoku s, List<SudokuCell> sc, NakedSet nsp, int level) throws CloneNotSupportedException {

		if (level >= sc.size() || level > 3) {
			return;
		}

		if (nsp == null) {
			nsp = new NakedSet();
		}

		for (SudokuCell c : sc) {

			if (!nsp.cells.contains(c)) {

				NakedSet ns = (NakedSet) nsp.clone();
				ns.add(s, c);

				if (ns.isCompleted()) {
					sets.add(ns);
					return;
				} else {
					search(s, sc, ns, level + 1);
					;
				}
			}
		}

	}

}
