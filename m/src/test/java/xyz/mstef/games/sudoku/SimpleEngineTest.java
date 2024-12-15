package xyz.mstef.games.sudoku;

import org.junit.jupiter.api.BeforeAll;

public class SimpleEngineTest extends AbstractBasicEngineTest {

	@BeforeAll
	public static void beforeAll() {
		e = new SimpleEngine();
	}

}
