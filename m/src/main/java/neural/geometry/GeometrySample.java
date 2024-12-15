package neural.geometry;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GeometrySample {

	private BufferedImage image = null;
	private int expected = 0;
	private int guessed = 0;
	private int id = 0;

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public int getExpected() {
		return expected;
	}

	public void setExpected(int expected) {
		this.expected = expected;
	}

	public int getGuessed() {
		return guessed;
	}

	public void setGuessed(int guessed) {
		this.guessed = guessed;
	}

	public boolean isCorrect() {
		return expected == guessed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static GeometrySample generateSample(int maxSides) {

		GeometrySample s = new GeometrySample();

		int sides = (int) (3d + (maxSides - 2d) * Math.random());
		BufferedImage bi = generateImage(sides);

		s.setExpected(sides);
		s.setImage(bi);

		return s;

	}

	public static BufferedImage generateImage(int sides) {

		int size = GeometryMachine.size;
		double minRadiusFactor = GeometryMachine.minRadiusFactor;

		BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = img.createGraphics();
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, size, size);
		g2d.setColor(Color.BLACK);

		// 1x1 coordinates
		double radius = minRadiusFactor + (0.5d - minRadiusFactor) * Math.random();
		double cx = radius + (1d - 2d * radius) * Math.random();
		double cy = radius + (1d - 2d * radius) * Math.random();

		double phi0 = 2d * Math.PI * Math.random();
		double theta = 2d * Math.PI / (double) sides;
		double xp = 0;
		double yp = 0;

		for (int i = 0; i <= sides; i++) {
			double phi = phi0 + theta * (double) i;
			double x = cx + radius * Math.sin(phi);
			double y = cy + radius * Math.cos(phi);

			if (i > 0) {
				// size x size
				g2d.drawLine((int) (xp * (double) size), (int) (yp * (double) size), (int) (x * (double) size),
						(int) (y * (double) size));
			}

			xp = x;
			yp = y;

		}

		return img;
	}
}
