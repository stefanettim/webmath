package neural.examples;

import java.util.Arrays;

public class SimpleNeuralNand {

	private int L0 = 2;
	private int L1 = 2;
	private int L2 = 1;

	private float[][] w1 = new float[L0][L1];
	private float[][] w2 = new float[L1][L2];
	private float[] b1 = new float[L1];
	private float[] b2 = new float[L2];

	public SimpleNeuralNand() {
		for (int j = 0; j < 2; j++) {
			for (int i = 0; i < 2; i++)
				w1[i][j] = (float) Math.random();

			b1[j] = (float) Math.random();
		}
	}

	private float sigmoid(float z) {
		return (float) (1 / (1 + Math.exp(z)));
	}

	public float[] step(float[] inputs, float[][] weights, float[] bias) {
		int ol = weights[0].length;
		float[] activations = new float[ol];

		for (int j = 0; j < ol; j++) {
			float z = 0;
			float[] w = weights[j];
			for (int i = 0; i < ol; i++) {
				z += w[i] * inputs[i] + bias[i];
			}
			activations[j] = sigmoid(z);
		}

		return activations;
	}

	public float[] go(float[] inputs) {
		float[] a1 = step(inputs, w1, b1);
		float[] a2 = step(a1, w2, b2);

		return a2;
	}

	public float train(float[] inputs, float[] outputs) {
		float[] a = go(inputs);

		float error = 0;
		float cost = 0;
		for (int i = 0; i < outputs.length; i++) {
			error = a[i] - outputs[i];
			cost += error * error;
		}
		cost = 1 / 4f * cost;

		return cost;

	}

	public static void main(String[] args) {

		SimpleNeuralNand nn = new SimpleNeuralNand();
		float[] a = nn.go(new float[] { 0, 1 });
		System.out.println(Arrays.toString(a));

		float c = nn.train(new float[] { 0f, 1f }, new float[] { 1f });
		System.out.println("Cost : " + c);
	}

}
