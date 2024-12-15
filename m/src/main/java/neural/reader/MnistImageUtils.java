package neural.reader;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import neural.ImageUtils;

public class MnistImageUtils {

	public static float[] readJpgImage(String filename) throws IOException {
		float[] floats = new float[28 * 28];
		BufferedImage img = ImageIO.read(new File(filename));

		BufferedImage img2828 = new BufferedImage(28, 28, BufferedImage.TYPE_INT_ARGB);
		AffineTransform at = new AffineTransform();
		at.scale(28f / (float) img.getWidth(), 28f / (float) img.getHeight());
		AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		img2828 = scaleOp.filter(img, img2828);

		RescaleOp op = new RescaleOp(2.2f, 50f, null);
		img2828 = op.filter(img2828, img2828);

		for (int r = 0; r < 28; r++) {
			for (int c = 0; c < 28; c++) {
				int p = img2828.getRGB(c, r);
				// int ca = (p>>24)&0xff;
				int cr = (p >> 16) & 0xff;
				int cg = (p >> 8) & 0xff;
				int cb = p & 0xff;

				floats[r * 28 + c] = 1f - ((float) cr + (float) cg + (float) cb) / 3f / 255f;

			}
		}

		return floats;
	}

	private static float[] mnistImage(BufferedImage small, BufferedImage centerMass) throws IOException {

		if (small == null)
			return null;

		float[] floats = new float[28 * 28];
		float[] floats_original = new float[20 * 20];

		for (int i = 0; i < 28; i++)
			for (int j = 0; j < 28; j++)
				floats[i * 28 + j] = 0f;

		float xcm = 0;
		float ycm = 0;
		float mass = 0;

		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				int rgb = small.getRGB(j, i);
				float f = ImageUtils.rgbToFloat(rgb);
				floats_original[i * 20 + j] = f;
				mass += f;
				xcm += i * f;
				ycm += j * f;
				// System.out.printf("%1.5f ",f);
			}
			// System.out.println();
		}

		if (mass > 0) {
			xcm = xcm / mass;
			ycm = ycm / mass;
		} else {
			xcm = 10;
			ycm = 10;
		}

		xcm = xcm - 10;
		ycm = ycm - 10;

		for (int i = 0; i < 20; i++)
			for (int j = 0; j < 20; j++) {
				int fx = (i + 4 - (int) xcm);
				int fy = (j + 4 - (int) ycm);
				if ((fx < 28) && (fy < 28) && (fx >= 0) && (fy >= 0))
					floats[fx * 28 + fy] = floats_original[i * 20 + j];
			}

		// just to show the center
		Graphics g = centerMass.createGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 28, 28);
		g.drawImage(small, 4, 4, null);
		g.setColor(new Color(255, 0, 0));
		g.fillOval((int) (ycm + 14), (int) (xcm + 14), 3, 3);

		return floats;
	}

	public static ImageChain mnistTransform(String data) throws IOException {
		BufferedImage originalBufferedImage = ImageUtils.readStringImage(data);
		return mnistProcess(originalBufferedImage);
	}

	public static ImageChain mnistProcess(BufferedImage originalBufferedImage) throws IOException {
		ImageChain ic = new ImageChain();
		ic.originalBufferedImage = originalBufferedImage;
		ic.convertedBufferedImage = ImageUtils.convert(originalBufferedImage);
		ic.subBufferedImage = ImageUtils.subImage(ic.convertedBufferedImage);
		ic.squareBufferedImage = ImageUtils.squareImage(ic.subBufferedImage);
		if(ic.squareBufferedImage==null) {
			ic.smallSize = 0;
			return ic;
		}
		ic.smallSize = ic.squareBufferedImage.getWidth();
		ic.smallBufferedImage = ImageUtils.smallImage(ic.squareBufferedImage);
		ic.centerMassBufferedImage = new BufferedImage(28, 28, BufferedImage.TYPE_INT_RGB);
		ic.mnist = MnistImageUtils.mnistImage(ic.smallBufferedImage, ic.centerMassBufferedImage);
		ic.mnistBufferedImage = ImageUtils.floatToImage(ic.mnist);
		return ic;
	}

	public static void writeImageChain(ImageChain ic, String dirname, String filename) throws IOException {
		ImageUtils.writeImage(ic.originalBufferedImage, dirname, filename + "_1_original");
		// writeImage(ic.convertedBufferedImage, dirname, filename+"_2_converted");
		// writeImage(ic.subBufferedImage, dirname, filename+"_3_sub");
		// writeImage(ic.squareBufferedImage, dirname, filename+"_4_square");
		// writeImage(ic.smallBufferedImage, dirname, filename+"_5_small");
		// writeImage(ic.centerMassBufferedImage, dirname, filename+"_6_center");
		// writeImage(ic.mnistBufferedImage, dirname, filename+"_7_mnist");
		ImageUtils.writeImage(ic.mnist, dirname, filename + "_8_mnist");
	}

}
