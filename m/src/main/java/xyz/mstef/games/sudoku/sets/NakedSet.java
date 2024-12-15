package xyz.mstef.games.sudoku.sets;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NakedSet {

	List<SudokuCell> cells = new ArrayList<SudokuCell>();
	Set<Integer> values = new HashSet<Integer>();

	public String toString() {
		String sc = "";
		for (SudokuCell c : cells) {
			sc += " " + c;
		}

		String sv = "";
		for (Integer i : values) {
			sv += " " + i;
		}

		return "cells {" + sc + " } values {" + sv + " }";
	}

	public boolean isCompleted() {
		return cells.size() > 0 && cells.size() == values.size();
	}

	public boolean add(SetsSudoku s, SudokuCell c) {

		cells.add(c);

		for (Integer k : s.getValues(c)) {
			values.add(k);
		}

		return false;
	}

	public Object clone() {
		NakedSet ns = new NakedSet();
		ns.values.addAll(values);
		ns.cells.addAll(cells);
		return ns;
	}

}
