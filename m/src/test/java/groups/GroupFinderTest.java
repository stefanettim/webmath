package groups;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class GroupFinderTest {

	@Test
	void groupFinderC3Test() throws Exception {

		List<Group> lg = Engine.findGroups(3);

		for (Group g : lg) {
			g.printText();
		}

		assertEquals(1, lg.size());

		Group f0 = lg.get(0);
		Group c3 = Factory.getCyclicGroup(3);
		boolean i = Engine.isomorphism(f0, c3);

		assertTrue(i);
	}

	@Test
	void isomorphismTest() throws Exception {

		Group c4 = Factory.getCyclicGroup(4);
		Group v4 = Factory.getV4Group();
		boolean i = Engine.isomorphism(c4, v4);
		assertFalse(i);
	}

	@Test
	void isomorphismMixedC4Test() throws Exception {

		Group c4 = Factory.getCyclicGroup(4);
		Group ac4 = Factory.getAlternativeC4Group();
		boolean i = Engine.isomorphism(c4, ac4);
		assert (i);
	}

	@Test
	void groupFinderO4Test() throws Exception {

		Group c4 = Factory.getCyclicGroup(4);
		Group v4 = Factory.getV4Group();
		List<Group> kl = new ArrayList<Group>();
		kl.add(v4);
		kl.add(c4);

		List<Group> lg = Engine.findGroups(4);
		assertEquals(2, lg.size());

		boolean c4f = false;
		boolean v4f = false;

		for (Group g : lg) {
			if (Engine.isomorphism(g, c4))
				c4f = true;

			if (Engine.isomorphism(g, v4))
				v4f = true;
		}

		assert (c4f);
		assert (v4f);

	}

	@Test
	void groupFinderTest() throws Exception {
		for (int o = 1; o < 7; o++) {
			System.out.println("===================================================================");
			List<Group> lg = Engine.findGroups(o);
			System.out.println("O:" + o + " g:" + lg.size());
			for (Group g : lg) {
				g.printText();
			}

		}
	}

	@Test
	void group6Finder() throws Exception {
		List<Group> lg = Engine.findGroups(6);
		System.out.println("group6 : " + lg.size());
	}

}
