package xyz.mstef.ml.connect_four;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

public class MinMaxEndGamesTest {

	final static int __MAX_LEVEL = 3; // 5 is slow

	static boolean endgame(int ch, int pr, int maxLevel) throws ClassNotFoundException, IOException {

		Agent a = new MinMaxAgent(maxLevel);

		State state = State.load(ch, pr);

		state.print("problem " + State.getFilename(ch, pr));

		Action g = a.chooseBestFirstAction(state);

		State after = Environment.perform(state, g);

		after.print("solution " + State.getFilename(ch, pr) + " goodMove " + g.getCol());

		boolean goodMove = (g.getCol() == state.getPerfectMove());

		return goodMove;
	}

	@Test
	public void allEndgames() throws ClassNotFoundException, IOException {

		for (int ch = 1; ch <= __MAX_LEVEL; ch++) {

			int pr = 1;

			boolean loop = true;

			while (loop) {

				String filename = State.getFilename(ch, pr);
				if (new File(filename).exists()) {

					System.out
							.println("===============================================================================");
					boolean goodMove = endgame(ch, pr, ch * 2 - 1);
					assert (goodMove);

				} else {
					loop = false;
				}

				pr++;

			}

		}

	}

}
