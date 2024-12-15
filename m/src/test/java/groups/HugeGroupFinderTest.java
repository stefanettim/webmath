package groups;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

class HugeGroupFinderTest {

	// @Test
	void groupFinderO7Test() throws Exception {
		List<Group> lg = Engine.findGroups(8);
		System.out.println("O:7" + " g:" + lg.size());
		assertEquals(1, lg.size());
		for (Group g : lg) {
			g.printText();
		}

	}

	// @Test
	void groupFinderO8Test() throws Exception {
		List<Group> lg = Engine.findGroups(8);
		System.out.println("O:8" + " g:" + lg.size());
		assertEquals(5, lg.size());
		for (Group g : lg) {
			g.printText();
		}

	}
}
