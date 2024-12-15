package neural.geometry;

import org.junit.jupiter.api.Test;

public class SavedTest {

	private void test(GeometryMachine gm, int limit, String label) {
		for (int i = 0; i < 10; i++) {
			float r = gm.test(100);
			System.out.printf("savedTest %20s : %3.2f \n", label, r);
			boolean check = (r > limit);
			assert (check);
		}
	}

	@Test
	void savedResourceTest() throws Exception {
		GeometryMachine gm = (GeometryMachine) new GeometryMachine(4).loadClassResource("Geometry");
		test(gm, 90, "resource");
	}

	@Test
	void savedTestCaseTest() throws Exception {
		GeometryMachine gm = (GeometryMachine) GeometryMachine.load("testcaseGeometry");
		test(gm, 75, "testcase");
	}

}
