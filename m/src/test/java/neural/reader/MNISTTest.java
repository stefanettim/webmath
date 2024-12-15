package neural.reader;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MNISTTest {

	@Test
	void trainingSampleTest() throws Exception {
		Samples s = SamplesFactory.getTrainingSamples();
		assertEquals(2049, s.labelsMagic);
		assertEquals(2051, s.imagesMagic);
		System.out.println("Training samples size : " + s.size);
	}

	@Test
	void testSampleTest() throws Exception {
		Samples s = SamplesFactory.getTestSamples();
		assertEquals(2049, s.labelsMagic);
		assertEquals(2051, s.imagesMagic);
		System.out.println("Test samples size : " + s.size);
	}

	@Test
	void generateTestImagesTest() throws Exception {
		SamplesFactory.generateTest("testcase", 5);
	}

	@Test
	void generateTrainingImagesTest() throws Exception {
		SamplesFactory.generateTraining("testcase", 5);
	}
}
