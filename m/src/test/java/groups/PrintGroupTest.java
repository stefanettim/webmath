package groups;

import org.junit.jupiter.api.Test;

class PrintGroupTest {

	@Test
	void printTest() throws Exception {

		Group g = Factory.getGoodGroup();
		g.printText();

		Group c = Factory.getCyclicGroup(10);
		c.printText();

	}

}
