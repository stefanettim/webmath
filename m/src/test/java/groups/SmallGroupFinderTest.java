package groups;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

class SmallGroupFinderTest {

	@Test
	void groupFinderO6Test() throws Exception {

		Group c6 = Factory.getCyclicGroup(6);
		Group s3 = Factory.getS3Group();

		List<Group> lg = Engine.findGroups(6);
		assertEquals(2, lg.size());

		boolean c6f = false;
		boolean s3f = false;

		for (Group g : lg) {
			if (Engine.isomorphism(g, c6))
				c6f = true;

			if (Engine.isomorphism(g, s3))
				s3f = true;
		}

		assert (c6f);
		assert (s3f);
	}

}
