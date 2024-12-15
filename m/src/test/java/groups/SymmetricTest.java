package groups;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

class SymmetricTest {

	@Test
	void symmetric3Test() throws Exception {
		Group symmetric3 = Factory.getSymmetricGroup(3);
		Group S3 = Factory.getS3Group();

		symmetric3.printText();

		boolean i = Engine.isomorphism(S3, symmetric3);

		assert (i);

	}

	@Test
	void symmetric4Test() throws Exception {
		Group symmetric4 = Factory.getSymmetricGroup(4);
		symmetric4.printText();

		boolean i = Engine.validate(symmetric4);
		assert (i);

		System.out.println("S4 size : " + symmetric4.order);

	}

	@Test
	void alternating3Test() throws Exception {
		Group g = Factory.getAlternatingGroup(3);
		g.printText();
		boolean i = Engine.validate(g);
		assert (i);
		Group C3 = Factory.getCyclicGroup(3);
		i = Engine.isomorphism(C3, g);
		assert (i);
	}

	@Test
	void alternating4Test() throws Exception {
		Group g = Factory.getAlternatingGroup(4);
		g.printText();
		boolean i = Engine.validate(g);
		assert (i);

		HashSet<Group> hs = Engine.subgroups(g);
		// int n=hs.size();
		for (Group s : hs)
			System.out.println(s);

	}
}
