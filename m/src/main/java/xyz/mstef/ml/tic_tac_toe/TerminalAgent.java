package xyz.mstef.ml.tic_tac_toe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TerminalAgent extends Agent {

	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public Action move(State s) {

		String ms = "0";
		try {
			System.out.print("your move : ");
			ms = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int m = Integer.parseInt(ms);

		Action a = new Action(m);

		return a;
	}

}
