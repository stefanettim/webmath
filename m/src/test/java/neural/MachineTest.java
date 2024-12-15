package neural;

import org.junit.jupiter.api.Test;

public class MachineTest {

	@Test
	void prepare() throws Exception {
		Machine m = new Machine(2, new int[] { 2, 3, 4 }, 1);
		m.printLayers();
	}

	@Test
	void prepareSimple() throws Exception {
		Machine m = new Machine(2, new int[] {}, 1);
		m.printLayers();
	}

	@Test
	void randomize() throws Exception {
		Machine m = new Machine(2, new int[] { 3 }, 1);
		m.randomizeWeights();
		m.printLayers();
	}

	@Test
	void feedfarward() throws Exception {
		Machine m = new Machine(2, new int[] { 3 }, 1);
		m.randomizeWeights();
		float[] input = new float[] { 1, 1 };
		float[] output = m.feedfarward(input);

		NeuralMath.printVector(output);
	}

	@Test
	void feedfarwardBookPag55() throws Exception {
		Machine m = new Machine(2, new int[] {}, 2);
		m.layers[1].weights = new float[][] { { 0.9f, 0.2f }, { 0.3f, 0.8f } };
		m.layers[1].bias = new float[] { 0f, 0f };
		float[] input = new float[] { 1f, 0.5f };
		float[] output = m.feedfarward(input);

		NeuralMath.printVector(output);
	}

	@Test
	void feedfarwardBookPag64() throws Exception {
		Machine m = new Machine(3, new int[] { 3 }, 3);
		m.layers[1].weights = new float[][] { { 0.9f, 0.2f, 0.1f }, { 0.3f, 0.8f, 0.5f }, { 0.4f, 0.2f, 0.6f } };
		m.layers[1].bias = new float[] { 0f, 0f, 0f };
		// float[prev][next]
		// input{i1,i2} {{w11,w12,w13},{w21,w22,w23}} => {z1,z2,z3}
		m.layers[2].weights = new float[][] { { 0.3f, 0.6f, 0.8f }, { 0.7f, 0.5f, 0.1f }, { 0.5f, 0.2f, 0.9f } };
		m.layers[2].bias = new float[] { 0f, 0f, 0f };
		float[] input = new float[] { 0.9f, 0.1f, 0.8f };
		float[] output = m.feedfarward(input);

		NeuralMath.printVector(output);
	}

}
