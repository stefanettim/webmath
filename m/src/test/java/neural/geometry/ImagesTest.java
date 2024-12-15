package neural.geometry;

import java.awt.image.BufferedImage;
import java.io.File;

import org.junit.jupiter.api.Test;

import neural.ImageUtils;
import utils.MProperties;

public class ImagesTest {

	@Test
	void generateTest() throws Exception {
		for (int i = 0; i < 20; i++) {
			GeometrySample s = GeometrySample.generateSample(3 + i % 3);
			BufferedImage img = s.getImage();
			String path = MProperties.load().getProperty("tmp");
			ImageUtils.writeImage(img, path+File.separator+"nn", "generateTest" + i);
		}
	}

}
