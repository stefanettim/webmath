package xyz.mstef.ml.connect_four;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class MinMaxSingleEndgameTest {

	@Test
	public void endgame() throws ClassNotFoundException, IOException {

		boolean goodMove = MinMaxEndGamesTest.endgame(5, 10, 9);
		assert (goodMove);
	}

}
