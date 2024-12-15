package groups;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class StatesGroupTest {

	@Test
	void statesV4Test() throws Exception {
		Group b = Factory.getV4Group();
		boolean v = Engine.validate(b);
		assertTrue(v, "V4 validate");
		b.printText();
	}

	@Test
	void statesS3Test() throws Exception {
		Group b = Factory.getS3Group();
		boolean v = Engine.validate(b);
		assertTrue(v, "S3 validate");
		b.printText();
	}

	@Test
	void statesC2C2C2Test() throws Exception {
		Group b = Factory.getC2xC2xC2Group();
		boolean v = Engine.validate(b);
		assertTrue(v, "C2xC2xC2 validate");
		b.printText();
	}
}
