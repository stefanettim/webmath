package neural;

import org.junit.jupiter.api.Test;

public class NeuralMathTest {

	@Test
	void shuffleTest() throws Exception {
		int size = 20;
		Integer[] a = new Integer[size];

		for (int i = 0; i < size; i++)
			a[i] = i;
		System.out.println(NeuralMath.stringVector(a));

		Integer[] s = NeuralMath.shuffle(a);
		System.out.println(NeuralMath.stringVector(s));
	}

	@Test
	void shuffle10Test() throws Exception {
		System.out.println(NeuralMath.stringVector(NeuralMath.shuffle(10)));
	}
}
