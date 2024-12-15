package neural.reader;

import org.junit.jupiter.api.Test;

import neural.Machine;

public class LearnAndSerializeTest {

	private final static int LIMIT = 3000;

	@Test
	void fullTest() throws Exception {
		ReadMachine rm = new ReadMachine();
		rm.learn(LIMIT, "testcaseRead");
		rm = new ReadMachine();
		rm = (ReadMachine) Machine.load("testcaseRead");
	}

}
