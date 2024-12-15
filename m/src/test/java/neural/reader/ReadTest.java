package neural.reader;

import org.junit.jupiter.api.Test;

import neural.ImageUtils;
import neural.Machine;

public class ReadTest {

	@Test
	void testClassMachineTest() throws Exception {
		ReadMachine rm = new ReadMachine();
		rm = (ReadMachine) rm.loadClassResource("Read");
		rm.testFullTest();
	}

	// @Test
	void testFullTest() throws Exception {
		ReadMachine rm = new ReadMachine();
		rm = (ReadMachine) Machine.load("bestRead");
		rm.testFullTest();
	}

	// @Test
	void trainingFullTest() throws Exception {
		ReadMachine rm = new ReadMachine();
		rm = (ReadMachine) Machine.load("testcaseRead");
		rm.trainingFullTest();
	}

	@Test
	void marcoFullTest() throws Exception {
		ReadMachine rm = new ReadMachine();
		rm = (ReadMachine) Machine.load("bestRead");

		Samples s = SamplesFactory.getMarcoSamples();
		float p = rm.test(0, s, true);

		System.out.printf("MARCO GOOD:%03.2f\n", p);

		for (int i = 0; i < 10; i++) {
			ImageUtils.writeImage(s.images[i], "neural/marco", "converted" + i);
		}
	}

}
