package neural.examples;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class NeuralNandTest {

	@Test
	void go() throws Exception {
		SimpleNeuralNand nn = new SimpleNeuralNand();
		float[] a = nn.go(new float[] { 0, 1 });
		System.out.println(Arrays.toString(a));
	}

	@Test
	void train() throws Exception {
		SimpleNeuralNand nn = new SimpleNeuralNand();
		float[] a = nn.go(new float[] { 0, 1 });
		System.out.println(Arrays.toString(a));
	}
}
