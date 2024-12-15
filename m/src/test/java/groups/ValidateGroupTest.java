package groups;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ValidateGroupTest {

	static Group c3;

	@BeforeAll
	static void setup() throws Exception {
		c3 = Factory.getCyclicGroup(3);
	}

	boolean validateGroup(Group b) {
		return validateGroup(b, true, true, true, true);
	}

	boolean validateGroup(Group group, boolean eid, boolean einv, boolean ec, boolean ea) {

		boolean identity = Engine.validateIdentityElement(group);
		assertEquals(eid, identity, "badGroup " + group.name + " identity");

		boolean inverse = Engine.validateInverseElement(group);
		assertEquals(einv, inverse, "badGroup " + group.name + " inverse");

		boolean closed = Engine.validateClosure(group);
		assertEquals(ec, closed, "badGroup " + group.name + " closure");

		boolean associative = false;
		if (closed) {
			associative = Engine.validateAssociativity(group);
		}
		assertEquals(ea, associative, "badGroup " + group.name + " associative");

		return true;
	}

	@Test
	void validateMixedC4GroupTest() {
		Group b = Factory.getAlternativeC4Group();
		b.printText();
		validateGroup(b);
	}

	@Test
	void validateBadIndentityGroupTest() {
		Group b = Factory.getBadIdentityGroup();
		validateGroup(b, false, false, false, false);
	}

	@Test
	void validateBadClosureGroupTest() {
		Group b = Factory.getBadClosureGroup();
		validateGroup(b, true, false, false, false);
	}

	@Test
	void validateGoodGroupTest() {
		Group b = Factory.getGoodGroup();
		validateGroup(b);
	}

	@Test
	void validateZ8GroupTest() throws Exception {
		Group b = Factory.getCyclicGroup(8);
		validateGroup(b);
	}

	@Test
	void validateTest() throws Exception {
		assert (Engine.validateClosure(c3));
		Group m = Engine.closedSubset(c3, new Permutation(new int[] { 0, 2 }));
		assertNull(m);
	}

	@Test
	void validateQ4Test() {
		Group b = Factory.getQ4Group();
		validateGroup(b);
		b.printText();
	}
}
