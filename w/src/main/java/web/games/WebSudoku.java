package web.games;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import xyz.mstef.games.sudoku.Factory;
import xyz.mstef.games.sudoku.Sudoku;
import xyz.mstef.games.sudoku.sets.SetEngine;

public class WebSudoku {

	public static void init(HttpServletRequest request, HttpServletResponse response) {

		@SuppressWarnings("unchecked")
		ArrayList<String[]> sudokus = (ArrayList<String[]>) request.getSession().getAttribute("sudokus");
		
		String sudokuAction = request.getParameter("sudokuAction");
		if("reload".equals(sudokuAction)) {
			sudokus=null;
		}

		if(sudokus==null) {

			sudokus = new ArrayList<String[]>();

		for(String label : new String[] {"custom","hardest","hardest2","top95"} ) {	
		try {
			InputStream is = Factory.class.getClassLoader().getResourceAsStream("sudoku/"+label+".txt");
			List<Sudoku> l = Factory.readInputStream(is);
			int p=1;
			for(Sudoku s : l) {
				sudokus.add(new String[] { label+"-"+p,s.toLine()});
				p++;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		}
		
		request.getSession().setAttribute("sudokus",sudokus);

		}
	}

		public static void execute(HttpServletRequest request, HttpServletResponse response) {
		
			String sudokuAction = request.getParameter("sudokuAction");

		Sudoku s = null;
		ArrayList<String> logs = null;
		
		if("clear".equals(sudokuAction)) {
			s = new Sudoku();
		} else if("easy".equals(sudokuAction)) {
			s = Factory.getEasySudokuProblem();
		} else if("wiki".equals(sudokuAction)) {
			s = Factory.getWikiSudokuProblem();
		} else if("diabolical".equals(sudokuAction)) {
			s = Factory.getDiabolicalSudokuProblem();
		} else if("platinumBlonde".equals(sudokuAction)) {
			s = new Sudoku(".......12........3..23..4....18....5.6..7.8.......9.....85.....9...4.5..47...6...");
		} else if("upload".equals(sudokuAction)) {
			String sudokuString = request.getParameter("sudokuString");
			s = new Sudoku(sudokuString);
		} else if("solve".equals(sudokuAction)) {
			
			s = new Sudoku();
			
			for(int i=0;i<9;i++) {
				for(int j=0;j<9;j++) {
					String c = request.getParameter("c"+i+""+j);
					int v = -1;
					
					if(c!=null && !"".equals(c.trim())) {
						v = Integer.parseInt(c.trim());
					}
					
					s.board[i][j]=v;
					
				}
			}	
			
			try {
				SetEngine e = new SetEngine();
				Sudoku solution = e.solve(s);
				logs=e.getLogs();

				request.setAttribute("sudokuSolution", solution);
				request.setAttribute("sudokuLogs", logs);

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		} else {
			s=new Sudoku();
		}
		
		request.setAttribute("sudoku", s);

	}
	
	

}
