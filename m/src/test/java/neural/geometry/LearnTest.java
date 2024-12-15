package neural.geometry;

import java.io.File;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import neural.ImageUtils;
import utils.MProperties;

public class LearnTest {

	private final static int maxIterations = 1000;
	private GeometryMachine gm = new GeometryMachine(4);

	@Test
	void learnTest() throws Exception {
		gm.learn(maxIterations, "testcaseGeometry");

		GeometrySample s = GeometrySample.generateSample(4);
		String path = MProperties.load().getProperty("tmp");
		ImageUtils.writeImage(s.getImage(), path+File.separator+"nn", "machineTest");
		int r = gm.feedFarward(s.getImage());
		Assertions.assertEquals(s.getExpected(), r);

	}

}
