package neural.geometry;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

import neural.ImageUtils;
import neural.Machine;
import neural.NeuralMath;

public class GeometryMachine extends Machine {

	private static final long serialVersionUID = -8813288382533331720L;

	protected static int size = 30;
	protected static double minRadiusFactor = 0.25f;

	protected static int[] hiddenLayers = new int[] { 320 };
	protected int maxSides = 4;
	protected float learn = 0.1f;

	/*
	 * step 20000000 result 100% protected static int size = 30; protected static
	 * double minRadiusFactor = 0.25f; protected static int[] hiddenLayers = new
	 * int[]{320}; protected int maxSides = 4; protected float learn=0.1f;
	 */

	/*
	 * step 10.000.000 result 100% protected static int size = 30; protected static
	 * double minRadiusFactor = 0.25f; protected static int[] hiddenLayers = new
	 * int[]{240}; protected int maxSides = 4; protected float learn=0.1f;
	 */

	public GeometryMachine(int maxSides) {
		super(size * size, hiddenLayers, maxSides - 2);
		this.maxSides = maxSides;
		randomizeWeights();
	}

	public void learn(int step, String prefix) throws IOException {

		int c = 0;

		for (int i = 0; i < step; i++) {

			GeometrySample s = GeometrySample.generateSample(maxSides);
			float[] output = NeuralMath.digitToArray(s.getExpected() - 3, maxSides - 2);
			BufferedImage bi = s.getImage();
			float[] input = ImageUtils.imageToFloat(bi);
			learnStochasticGradientDescent(input, output);

			c++;
			if (c == 10000) {
				float t = test(100);
				c = 0;

				if (prefix != null) {
					save(prefix);
				}

				System.out.printf("progress %,8d success %3.2f \n", i + 1, t);

			}

		}

	}

	public float test(int step) {

		int correct = 0;

		for (int i = 0; i < step; i++) {
			GeometrySample s = GeometrySample.generateSample(maxSides);
			float[] input = ImageUtils.imageToFloat(s.getImage());
			float[] output = feedfarward(input);
			int res = NeuralMath.activationsToInt(output);
			if (res + 3 == s.getExpected()) {
				correct++;
			}
		}

		float p = 100f * (float) correct / 100f;

		return p;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

		GeometryMachine gm = new GeometryMachine(4);
		gm.learn(20000000, "lastGeometry");

		System.out.println("==============================================");
		float r = gm.test(1000);
		System.out.printf("lastGeometry : %3.2f \n", r);

	}

	public int feedFarward(BufferedImage image) {
		float[] input = ImageUtils.imageToFloat(image);
		float[] output = feedfarward(input);
		int r = NeuralMath.activationsToInt(output) + 3;
		return r;
	}

}
