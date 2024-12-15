package xyz.mstef.ml.connect_four;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class TerminalAgent extends Agent {

	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	@Override
	public Action move(State s) {

		String rc = "0";
		try {
			System.out.print("col : ");
			rc = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int c = Integer.parseInt(rc);

		Action a = new Action(c);

		return a;
	}

	@Override
	public List<Action> chooseBestActions(State state, int level) {
		return state.getAvailableActions();
	}
}
