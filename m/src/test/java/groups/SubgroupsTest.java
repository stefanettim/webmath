package groups;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Subgroups")
class SubgroupsTest {

	static Group c2;
	static Group c3;
	static Group c4;
	static Group v4;
	static Group c5;
	static Group c6;
	static Group c12;
	static Group c19;
	static Group c20;
	static Group s3;
	static Group c2c2c2;
	static Group big;
	static Group q4;

	@BeforeAll
	static void setup() throws Exception {
		c2 = Factory.getCyclicGroup(2);
		c3 = Factory.getCyclicGroup(3);
		c4 = Factory.getCyclicGroup(4);
		c5 = Factory.getCyclicGroup(5);
		c6 = Factory.getCyclicGroup(6);
		c12 = Factory.getCyclicGroup(12);
		c19 = Factory.getCyclicGroup(19);
		c20 = Factory.getCyclicGroup(20);
		s3 = Factory.getS3Group();
		c2c2c2 = Factory.getC2xC2xC2Group();
		big = Factory.getCyclicGroup(25);
		v4 = Factory.getV4Group();
		q4 = Factory.getQ4Group();
	}

	void testSubgroup(Group s, Group g, boolean b) throws Exception {

		System.out.println("=================================================");

		s.printText();
		g.printText();

		Group m = Engine.subgroup(s, g);

		boolean r = (m != null);

		assertEquals(b, r);

		if (r) {
			m.printText();
			System.out.println("Subgroup " + s.name + " is isomorphic to " + m.name + "<" + g.name);
		} else
			System.out.println("Subgroup NOT found");

	}

	@Test
	void testC2SubC4() throws Exception {
		testSubgroup(c2, c4, true);
	}

	@Test
	void testC3SubC6() throws Exception {
		testSubgroup(c3, c6, true);
	}

	@Test
	void testC4NotSubC6() throws Exception {
		testSubgroup(c4, c6, false);
	}

	@Test
	void testC3SubC12() throws Exception {
		testSubgroup(c3, c12, true);
	}

	@Test
	void testC3SubS3() throws Exception {
		testSubgroup(c3, s3, true);
	}

	@Test
	void testC5SubC20() throws Exception {
		testSubgroup(c5, c20, true);
	}

	@Test
	void identityTest() throws Exception {
		testSubgroup(Factory.getIdentityGroup(), c20, true);
	}

	@Test
	void subgroupsC3Test() throws Exception {
		HashSet<Group> hs = Engine.subgroups(c3);
		int n = hs.size();
		assertEquals(2, n, "I,C3");
	}

	@Test
	void subgroupsC19Test() throws Exception {
		HashSet<Group> hs = Engine.subgroups(c19);
		int n = hs.size();
		assertEquals(2, n, "I,C19");
	}

	@Test
	void subgroupsC20Test() throws Exception {
		HashSet<Group> hs = Engine.subgroups(c20);
		int n = hs.size();
		assertEquals(6, n, "I,C20");
		for (Group g : hs)
			System.out.println(g);
	}

	@Test
	@DisplayName("Subgroups of C2xC2xC2")
	void subgroupsC2C2C2Test() throws Exception {
		HashSet<Group> hs = Engine.subgroups(c2c2c2);
		int n = hs.size();
		for (Group g : hs)
			System.out.println(g);
		assertEquals(16, n);
	}

	@Test
	@DisplayName("subgroupsV4Test")
	void subgroupsV4Test() throws Exception {
		HashSet<Group> hs = Engine.subgroups(v4);
		int n = hs.size();
		for (Group g : hs)
			System.out.println(g);
		assertEquals(5, n);
	}

	@Test
	@DisplayName("subgroupsQ4Test")
	void subgroupsQ4Test() throws Exception {
		HashSet<Group> hs = Engine.subgroups(q4);
		int n = hs.size();
		for (Group g : hs)
			System.out.println(g);
		assertEquals(6, n);
	}

	@Test
	void subgroupsBigTest() throws Exception {

		Assertions.assertThrows(Exception.class, () -> {
			Group b = big;
			Engine.subgroups(b);
		});

	}

	@Test
	void isomorphismTest() throws Exception {
		Group c2 = Factory.getCyclicGroup(2);
		Group ac2 = Factory.getAlternativeC2Group();
		boolean it = Engine.isomorphism(c2, ac2);
		assert (it);
	}

}
