package neural.reader;

import org.junit.jupiter.api.Test;

import neural.Machine;

public class DumpErrorsTest {

	@Test
	void loadTest() throws Exception {
		ReadMachine rm = (ReadMachine) Machine.load("testcaseRead");
		rm.printActivations();
	}

	@Test
	void dumpTrainingErrorsTest() throws Exception {
		ReadMachine rm = (ReadMachine) Machine.load("testcaseRead");

		Samples s = SamplesFactory.getTrainingSamples();

		float p = rm.test(10, s, true);
		System.out.printf("first 200 training, GOOD:%03.2f\n", p);

	}

	@Test
	void dumpTestErrorsTest() throws Exception {
		ReadMachine rm = (ReadMachine) Machine.load("testcaseRead");

		Samples s = SamplesFactory.getTestSamples();

		float p = rm.test(10, s, true);
		System.out.printf("first 200 test, GOOD:%03.2f\n", p);

	}
}
