package xyz.mstef.ml.connect_four;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class Decode {

	public static void main(String[] args) throws IOException, URISyntaxException {

		// pass the path to the file as a parameter
		FileReader fr = new FileReader("connect_four/problems.txt");

		ArrayList<ArrayList<State>> alm = new ArrayList<ArrayList<State>>();
		ArrayList<State> als = null;
		boolean states = false;
		State s = null;
		int row = 0;
		int col = 0;
		int ch = 0;
		int pr = 0;

		int i;
		while ((i = fr.read()) != -1) {
			char c = (char) i;

			if (c == 'P') {

				if (als != null) {
					alm.add(als);
				}

				als = new ArrayList<State>();

				fr.read();

				i = fr.read();
				c = (char) i;
				ch = Integer.valueOf("" + c);
				pr = 0;
			}

			if ((c == '[')) {
				if (!states) {
					s = new State();
					s.setTurn(1);
					row = 0;
					col = 0;
					pr++;
					states = true;
				}
			}

			if ((states) && (c == '1')) { // red
				s.getBoard()[row][col] = 1;
			}

			if ((states) && (c == '2')) { // black
				s.getBoard()[row][col] = -1;
			}

			if ((states) && (c == '0')) {
				s.getBoard()[row][col] = 0;
			}

			if ((states) && ((c == '1') || (c == '2') || (c == '0'))) {
				row++;
				if (row == 6) {
					col++;
					row = 0;
				}
				if (col == 7) {
					states = false;

					URL url = new URI("https://sites.math.rutgers.edu/~zeilberg/C4/ch" + ch + "/A" + pr).toURL();
					BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

					String inputLine;
					while ((inputLine = in.readLine()) != null) {
						System.out.println(inputLine);
						String a = "" + inputLine.charAt(15);
						int answer = Integer.parseInt(a) - 1;
						s.setPerfectMove(answer);
					}
					in.close();

					s.print("ch " + ch + ", pr " + pr);

					State.save(s, ch, pr);

				}
			}

		}

		fr.close();
	}

}
