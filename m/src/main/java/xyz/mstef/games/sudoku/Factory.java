package xyz.mstef.games.sudoku;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Factory {
	
	// https://attractivechaos.github.io/plb/kudoku.html

	// https://it.wikipedia.org/wiki/Sudoku
	public static Sudoku getWikiSudokuProblem() {

		Sudoku b = new Sudoku();

		b.board[0][0] = 5;
		b.board[0][1] = 3;
		b.board[0][4] = 7;
		b.board[1][0] = 6;
		b.board[1][3] = 1;
		b.board[1][4] = 9;
		b.board[1][5] = 5;
		b.board[2][1] = 9;
		b.board[2][2] = 8;
		b.board[2][7] = 6;
		b.board[3][0] = 8;
		b.board[3][4] = 6;
		b.board[3][8] = 3;
		b.board[4][0] = 4;
		b.board[4][3] = 8;
		b.board[4][5] = 3;
		b.board[4][8] = 1;
		b.board[5][0] = 7;
		b.board[5][4] = 2;
		b.board[5][8] = 6;
		b.board[6][1] = 6;
		b.board[6][6] = 2;
		b.board[6][7] = 8;
		b.board[7][3] = 4;
		b.board[7][4] = 1;
		b.board[7][5] = 9;
		b.board[7][8] = 5;
		b.board[8][4] = 8;
		b.board[8][7] = 7;
		b.board[8][8] = 9;

		return b;

	}

	public static Sudoku getWikiSudokuSolution() {

		Sudoku b = new Sudoku();

		b.board[0][0] = 5;
		b.board[0][1] = 3;
		b.board[0][2] = 4;
		b.board[0][3] = 6;
		b.board[0][4] = 7;
		b.board[0][5] = 8;
		b.board[0][6] = 9;
		b.board[0][7] = 1;
		b.board[0][8] = 2;

		b.board[1][0] = 6;
		b.board[1][1] = 7;
		b.board[1][2] = 2;
		b.board[1][3] = 1;
		b.board[1][4] = 9;
		b.board[1][5] = 5;
		b.board[1][6] = 3;
		b.board[1][7] = 4;
		b.board[1][8] = 8;

		b.board[2][0] = 1;
		b.board[2][1] = 9;
		b.board[2][2] = 8;
		b.board[2][3] = 3;
		b.board[2][4] = 4;
		b.board[2][5] = 2;
		b.board[2][6] = 5;
		b.board[2][7] = 6;
		b.board[2][8] = 7;

		b.board[3][0] = 8;
		b.board[3][1] = 5;
		b.board[3][2] = 9;
		b.board[3][3] = 7;
		b.board[3][4] = 6;
		b.board[3][5] = 1;
		b.board[3][6] = 4;
		b.board[3][7] = 2;
		b.board[3][8] = 3;

		b.board[4][0] = 4;
		b.board[4][1] = 2;
		b.board[4][2] = 6;
		b.board[4][3] = 8;
		b.board[4][4] = 5;
		b.board[4][5] = 3;
		b.board[4][6] = 7;
		b.board[4][7] = 9;
		b.board[4][8] = 1;

		b.board[5][0] = 7;
		b.board[5][1] = 1;
		b.board[5][2] = 3;
		b.board[5][3] = 9;
		b.board[5][4] = 2;
		b.board[5][5] = 4;
		b.board[5][6] = 8;
		b.board[5][7] = 5;
		b.board[5][8] = 6;

		b.board[6][0] = 9;
		b.board[6][1] = 6;
		b.board[6][2] = 1;
		b.board[6][3] = 5;
		b.board[6][4] = 3;
		b.board[6][5] = 7;
		b.board[6][6] = 2;
		b.board[6][7] = 8;
		b.board[6][8] = 4;

		b.board[7][0] = 2;
		b.board[7][1] = 8;
		b.board[7][2] = 7;
		b.board[7][3] = 4;
		b.board[7][4] = 1;
		b.board[7][5] = 9;
		b.board[7][6] = 6;
		b.board[7][7] = 3;
		b.board[7][8] = 5;

		b.board[8][0] = 3;
		b.board[8][1] = 4;
		b.board[8][2] = 5;
		b.board[8][3] = 2;
		b.board[8][4] = 8;
		b.board[8][5] = 6;
		b.board[8][6] = 1;
		b.board[8][7] = 7;
		b.board[8][8] = 9;

		return b;
	}

	public static Sudoku getEasySudokuSolution() {

		Sudoku b = new Sudoku();

		b.board[0][0] = 1;
		b.board[0][1] = 4;
		b.board[0][2] = 9;
		b.board[0][3] = 2;
		b.board[0][4] = 3;
		b.board[0][5] = 5;
		b.board[0][6] = 6;
		b.board[0][7] = 7;
		b.board[0][8] = 8;

		b.board[1][0] = 5;
		b.board[1][1] = 7;
		b.board[1][2] = 3;
		b.board[1][3] = 8;
		b.board[1][4] = 4;
		b.board[1][5] = 6;
		b.board[1][6] = 2;
		b.board[1][7] = 1;
		b.board[1][8] = 9;

		b.board[2][0] = 2;
		b.board[2][1] = 6;
		b.board[2][2] = 8;
		b.board[2][3] = 7;
		b.board[2][4] = 9;
		b.board[2][5] = 1;
		b.board[2][6] = 3;
		b.board[2][7] = 5;
		b.board[2][8] = 4;

		b.board[3][0] = 6;
		b.board[3][1] = 9;
		b.board[3][2] = 2;
		b.board[3][3] = 3;
		b.board[3][4] = 1;
		b.board[3][5] = 4;
		b.board[3][6] = 7;
		b.board[3][7] = 8;
		b.board[3][8] = 5;

		b.board[4][0] = 3;
		b.board[4][1] = 5;
		b.board[4][2] = 7;
		b.board[4][3] = 9;
		b.board[4][4] = 8;
		b.board[4][5] = 2;
		b.board[4][6] = 1;
		b.board[4][7] = 4;
		b.board[4][8] = 6;

		b.board[5][0] = 4;
		b.board[5][1] = 8;
		b.board[5][2] = 1;
		b.board[5][3] = 5;
		b.board[5][4] = 6;
		b.board[5][5] = 7;
		b.board[5][6] = 9;
		b.board[5][7] = 3;
		b.board[5][8] = 2;

		b.board[6][0] = 8;
		b.board[6][1] = 2;
		b.board[6][2] = 6;
		b.board[6][3] = 1;
		b.board[6][4] = 5;
		b.board[6][5] = 3;
		b.board[6][6] = 4;
		b.board[6][7] = 9;
		b.board[6][8] = 7;

		b.board[7][0] = 7;
		b.board[7][1] = 3;
		b.board[7][2] = 5;
		b.board[7][3] = 4;
		b.board[7][4] = 2;
		b.board[7][5] = 9;
		b.board[7][6] = 8;
		b.board[7][7] = 6;
		b.board[7][8] = 1;

		b.board[8][0] = 9;
		b.board[8][1] = 1;
		b.board[8][2] = 4;
		b.board[8][3] = 6;
		b.board[8][4] = 7;
		b.board[8][5] = 8;
		b.board[8][6] = 5;
		b.board[8][7] = 2;
		b.board[8][8] = 3;

		return b;
	}

	public static Sudoku getEasySudokuProblem() {

		Sudoku b = new Sudoku();

		b.board[0][0] = 1;
		b.board[0][5] = 5;
		b.board[0][6] = 6;
		b.board[0][8] = 8;

		b.board[1][4] = 4;
		b.board[1][6] = 2;

		b.board[2][0] = 2;
		b.board[2][1] = 6;
		b.board[2][4] = 9;
		b.board[2][5] = 1;

		b.board[3][0] = 6;
		b.board[3][2] = 2;
		b.board[3][3] = 3;
		b.board[3][5] = 4;

		b.board[4][1] = 5;
		b.board[4][2] = 7;
		b.board[4][4] = 8;
		b.board[4][6] = 1;
		b.board[4][7] = 4;

		b.board[5][3] = 5;
		b.board[5][5] = 7;
		b.board[5][6] = 9;
		b.board[5][8] = 2;

		b.board[6][3] = 1;
		b.board[6][4] = 5;
		b.board[6][7] = 9;
		b.board[6][8] = 7;

		b.board[7][2] = 5;
		b.board[7][4] = 2;

		b.board[8][0] = 9;
		b.board[8][2] = 4;
		b.board[8][3] = 6;
		b.board[8][8] = 3;

		return b;

	}

	// https://www.sudokuessentials.com/sudoku_for_kids.html
	public static Sudoku getVeryEasySudokuProblem() {

		Sudoku b = new Sudoku();

		b.board = new int[][] { { -1, 2, -1, 4, 5, 6, 7, 8, 9 }, { 4, 5, 7, -1, 8, -1, 2, 3, 6 },
				{ 6, 8, 9, 2, 3, 7, -1, 4, -1 }, { -1, -1, 5, 3, 6, 2, 9, 7, 4 }, { 2, 7, 4, -1, 9, -1, 6, 5, 3 },
				{ 3, 9, 6, 5, 7, 4, 8, -1, -1 }, { -1, 4, -1, 6, 1, 8, 3, 9, 7 }, { 7, 6, 1, -1, 4, -1, 5, 2, 8 },
				{ 9, 3, 8, 7, 2, 5, -1, 6, -1 } };

		return b;
	}

	public static Sudoku getVeryEasySudokuSolution() {

		Sudoku b = new Sudoku();

		b.board = new int[][] { { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 4, 5, 7, 1, 8, 9, 2, 3, 6 },
				{ 6, 8, 9, 2, 3, 7, 1, 4, 5 }, { 8, 1, 5, 3, 6, 2, 9, 7, 4 }, { 2, 7, 4, 8, 9, 1, 6, 5, 3 },
				{ 3, 9, 6, 5, 7, 4, 8, 1, 2 }, { 5, 4, 2, 6, 1, 8, 3, 9, 7 }, { 7, 6, 1, 9, 4, 3, 5, 2, 8 },
				{ 9, 3, 8, 7, 2, 5, 4, 6, 1 } };

		return b;
	}

	// https://www.sudokuoftheday.com/free/free-diabolical-sudoku/
	public static Sudoku getDiabolicalSudokuProblem() {

		Sudoku b = new Sudoku();

		b.board = new int[][] { { -1, -1, 1, -1, -1, 8, -1, 7, 3 }, { -1, -1, 5, 6, -1, -1, -1, -1, 1 },
				{ 7, -1, -1, -1, -1, 1, -1, -1, -1 }, { -1, 9, -1, 8, 1, -1, -1, -1, -1 },
				{ 5, 3, -1, -1, -1, -1, -1, 4, 6 }, { -1, -1, -1, -1, 6, 5, -1, 3, -1 },
				{ -1, -1, -1, 1, -1, -1, -1, -1, 4 }, { 8, -1, -1, -1, -1, 9, 3, -1, -1 },
				{ 9, 4, -1, 5, -1, -1, 7, -1, -1 } };

		return b;
	}

	public static Sudoku getDiabolicalSudokuSolution() {

		Sudoku b = new Sudoku();

		b.board = new int[][] { { 4, 2, 1, 9, 5, 8, 6, 7, 3 }, { 3, 8, 5, 6, 7, 4, 2, 9, 1 },
				{ 7, 6, 9, 3, 2, 1, 4, 5, 8 }, { 6, 9, 4, 8, 1, 3, 5, 2, 7 }, { 5, 3, 8, 7, 9, 2, 1, 4, 6 },
				{ 1, 7, 2, 4, 6, 5, 8, 3, 9 }, { 2, 5, 6, 1, 3, 7, 9, 8, 4 }, { 8, 1, 7, 2, 4, 9, 3, 6, 5 },
				{ 9, 4, 3, 5, 8, 6, 7, 1, 2 } };

		return b;
	}

	public static Sudoku getEmptySudoku() {

		Sudoku b = new Sudoku();

		b.board = new int[][] { { -1, -1, -1, -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1 } };

		return b;
	}

	// https://www.sudoku-solutions.com/
	// Sample Puzzle No #6766
	public static Sudoku getXWingSudokuProblem() {

		Sudoku b = new Sudoku();

		b.board = new int[][] { { 8, -1, 6, -1, -1, -1, 5, -1, 2 }, { -1, 9, 5, -1, 3, -1, 8, 1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1 }, { 3, -1, -1, 5, -1, 4, -1, -1, 7 },
				{ -1, -1, -1, 7, -1, 8, -1, -1, -1 }, { 5, -1, -1, 3, -1, 9, -1, -1, 1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1 }, { -1, 3, 9, -1, 4, -1, 1, 6, -1 },
				{ 6, -1, 8, -1, -1, -1, 4, -1, 3 } };

		return b;
	}

	public static Sudoku getP001Problem() {

		Sudoku b = new Sudoku();

		b.board = new int[][] { { -1, -1, -1, -1, -1, -1, 7, -1, 9 }, { -1, 9, 4, -1, -1, 8, 6, -1, 5 },
				{ -1, 8, -1, -1, -1, -1, -1, -1, 1 }, { 6, 1, -1, -1, -1, -1, 4, 5, -1 },
				{ -1, -1, 2, -1, 5, -1, 9, -1, -1 }, { 7, -1, -1, -1, 9, 2, -1, -1, -1 },
				{ -1, 5, -1, 2, -1, 3, -1, -1, -1 }, { -1, -1, -1, 5, -1, -1, 1, -1, -1 },
				{ 9, -1, -1, 6, 8, -1, -1, -1, -1 } };

		return b;
	}

	// http://norvig.com/sudoku.html
	public static List<Sudoku> getTop95Problems() throws IOException {
		return readFile("top95.txt");
	}

	public static List<Sudoku> getHardestProblems() throws IOException {
		return readFile("hardest.txt");
	}

	public static List<Sudoku> getEasy50Problems() throws IOException {
		return readFile("easy50.txt", "========");
	}

	public static List<Sudoku> readFile(String filename) throws IOException {
		FileInputStream fr = new FileInputStream("sudoku/" + filename);
		return readInputStream(fr);
	}

	public static List<Sudoku> readInputStream(InputStream is) throws IOException {

		List<Sudoku> sudokus = new ArrayList<Sudoku>();

		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		String line;
		while ((line = br.readLine()) != null) {
			if(!line.startsWith("#")) {
			Sudoku s = new Sudoku(line);
			sudokus.add(s);
			}
		}

		br.close();

		return sudokus;
	}

	public static List<Sudoku> readFile(String filename, String separator) throws IOException {

		List<Sudoku> sudokus = new ArrayList<Sudoku>();

		BufferedReader br = new BufferedReader(new FileReader("sudoku/" + filename));

		String sudokuLine = "";
		String line;
		while ((line = br.readLine()) != null) {
			if (line.equals(separator)) {
				Sudoku s = new Sudoku(sudokuLine);
				sudokus.add(s);
				sudokuLine = "";
			} else {
				sudokuLine += line;
			}
		}

		br.close();

		return sudokus;
	}

}
