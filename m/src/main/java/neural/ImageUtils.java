package neural;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

public class ImageUtils {

	public static BufferedImage convert(BufferedImage src, int bufImgType) {
		BufferedImage img = new BufferedImage(src.getWidth(), src.getHeight(), bufImgType);
		Graphics2D g2d = img.createGraphics();
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, img.getWidth(), img.getHeight());
		g2d.drawImage(src, 0, 0, null);
		g2d.dispose();
		return img;
	}

	public static BufferedImage convert(BufferedImage src) {
		return convert(src, BufferedImage.TYPE_INT_ARGB);
	}

	public static BufferedImage floatToImage(float[] pixels) throws IOException {

		if (pixels == null)
			return null;

		BufferedImage i = new BufferedImage(28, 28, BufferedImage.TYPE_INT_RGB);
		Graphics g = i.getGraphics();

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, i.getWidth(), i.getHeight());

		for (int r = 0; r < 28; r++) {
			for (int c = 0; c < 28; c++) {
				float gray = 1f - pixels[r * 28 + c];
				Color color = new Color(gray, gray, gray);
				g.setColor(color);
				g.drawLine(c, r, c, r);
			}
		}

		return i;
	}

	public static float rgbToFloat(int rgb) {
		// https://stackoverflow.com/questions/9131678/convert-a-rgb-image-to-grayscale-image-reducing-the-memory-in-java
		int r = (rgb >> 16) & 0xFF;
		int g = (rgb >> 8) & 0xFF;
		int b = (rgb & 0xFF);

		// Normalize and gamma correct:
		float rr = (float) Math.pow(r / 255.0, 2.2);
		float gg = (float) Math.pow(g / 255.0, 2.2);
		float bb = (float) Math.pow(b / 255.0, 2.2);

		// Calculate luminance:
		float lum = 0.2126f * rr + 0.7152f * gg + 0.0722f * bb;

		// Gamma compand and rescale to byte range:
		// int grayLevel = (int) (255.0 * Math.pow(lum, 1.0 / 2.2));
		// int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel;

		float grayf = (float) Math.pow(lum, 1.0 / 2.2);

		return 1 - grayf;
	}

	public static float[] imageToFloat(BufferedImage image) {
		float[] floats = new float[image.getWidth() * image.getHeight()];

		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				int rgb = image.getRGB(x, y);
				floats[x + y * image.getWidth()] = rgbToFloat(rgb);
			}
		}

		return floats;
	}

	public static void writeImage(float[] pixels, String dirname, String filename) throws IOException {
		BufferedImage i = floatToImage(pixels);
		ImageIO.write(i, "png", new File(dirname, filename + ".png"));
	}

	public static void writeImage(BufferedImage img, String dirname, String filename) throws IOException {
		ImageIO.write(img, "png", new File(dirname, filename + ".png"));
	}

	public static BufferedImage readStringImage(String data) throws IOException {
		if (data == null || "".equals(data))
			return null;

		String base64Image = data.split(",")[1];
		byte[] imageBytes = DatatypeConverter.parseBase64Binary(base64Image);
		BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));
		return img;
	}

	/**
	 * Encode image to string
	 * 
	 * @param image The image to encode
	 * @param type  jpeg, bmp, ...
	 * @return encoded string
	 */
	public static String encodeToString(BufferedImage image, String type) {

		if (image == null)
			return "";

		String imageString = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		try {
			ImageIO.write(image, type, bos);
			byte[] imageBytes = bos.toByteArray();

			Encoder encoder = Base64.getEncoder();
			imageString = "data:image/" + type + ";base64," + encoder.encodeToString(imageBytes);

			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imageString;
	}

	public static String encodeToString(BufferedImage image) {
		return encodeToString(image, "png");
	}

	public static BufferedImage subImage(BufferedImage img) throws IOException {

		int w = img.getWidth();
		int h = img.getHeight();

		int minx = w;
		int maxx = 0;
		int miny = h;
		int maxy = 0;

		for (int i = 0; i < w; i++)
			for (int j = 0; j < h; j++) {
				int rgb = img.getRGB(i, j);
				float c = rgbToFloat(rgb);

				if (c != 0) {
					if (i < minx)
						minx = i;
					if (i > maxx)
						maxx = i;
					if (j < miny)
						miny = j;
					if (j > maxy)
						maxy = j;
				}
			}

		int wx = maxx - minx;
		int wy = maxy - miny;

		if (wx < 0 || wy < 0)
			return null;

		BufferedImage sub = img.getSubimage(minx, miny, (int) wx, (int) wy);

		return sub;

	}

	public static BufferedImage squareImage(BufferedImage img) throws IOException {
		if (img == null)
			return null;

		int w = img.getWidth();
		int h = img.getHeight();

		int size = w;
		if (h > w)
			size = h;

		BufferedImage square = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);

		Graphics g = square.getGraphics();
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, size, size);
		g.drawImage(img, size / 2 - w / 2, size / 2 - h / 2, null);

		return square;

	}

	public static BufferedImage resizeImage(BufferedImage img, int width, int height) {

		if (img == null)
			return null;

		Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = resized.createGraphics();
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, width, height);
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return resized;
	}

	public static BufferedImage smallImage(BufferedImage img) throws IOException {
		BufferedImage img2020 = resizeImage(img, 20, 20);
		return img2020;

	}

	public static BufferedImage rotate(BufferedImage image, double angleDegree) {
		double rads = Math.toRadians(angleDegree);
		double sin = Math.abs(Math.sin(rads));
		double cos = Math.abs(Math.cos(rads));
		int w = (int) Math.floor(image.getWidth() * cos + image.getHeight() * sin);
		int h = (int) Math.floor(image.getHeight() * cos + image.getWidth() * sin);
		BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());
		Graphics2D graphics = rotatedImage.createGraphics();
		graphics.setPaint(Color.WHITE);
		graphics.fillRect(0, 0, rotatedImage.getWidth(), rotatedImage.getHeight());
		AffineTransform at = new AffineTransform();
		at.translate(w / 2, h / 2);
		at.rotate(rads, 0, 0);
		at.translate(-image.getWidth() / 2, -image.getHeight() / 2);
		final AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		rotateOp.filter(image, rotatedImage);
		return rotatedImage;
	}

}
