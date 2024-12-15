package neural.reader;

import java.awt.image.BufferedImage;
import java.io.File;

import org.junit.jupiter.api.Test;

import neural.ImageUtils;
import neural.Machine;
import utils.MProperties;

public class ImagesTest {

	@Test
	void rotateTest() throws Exception {
		ReadMachine rm = (ReadMachine) Machine.load("bestRead");

		Samples s = SamplesFactory.getTestSamples();

		for (int n = 0; n < 20; n++) {
			float[] image = s.getImageBytes(n);

			ReadResult rs = rm.process(image);

			System.out.println("[" + n + "] ReadResult Original : " + rs.getResult());

			BufferedImage img = ImageUtils.floatToImage(image);

			String path = MProperties.load().getProperty("tmp");
			ImageUtils.writeImage(img, path+File.separator+"nn", "rotateTest_" + n + "_000");

			float maxRotation = 10;
			float angle = 2 * (0.5f - (float) Math.random()) * maxRotation;

//		System.out.println("angle : "+angle);

			BufferedImage rotated = ImageUtils.rotate(img, angle);

			ImageChain ic = MnistImageUtils.mnistProcess(rotated);

			MnistImageUtils.writeImageChain(ic, path+File.separator+"nn", "rotateTest_" + n);

			ReadResult rr = rm.process(ic.mnist);

			System.out.println("[" + n + "] ReadResult Rotated : " + rr.getResult());
		}
	}

}
