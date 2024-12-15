package neural.examples;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import neural.Machine;
import neural.NeuralMath;

public class NeuralAndTest {

	@Test
	void trainAndTest() throws Exception {
		Machine nn = new Machine(2, new int[] { 2 }, 2);
		nn.randomizeWeights();
		nn.setLearn(1f);
		nn.printLayers();

		for (int e = 0; e < 100; e++) {
			nn.learnStochasticGradientDescent(new float[] { 0, 0 }, new float[] { 1, 0 });
			nn.learnStochasticGradientDescent(new float[] { 0, 1 }, new float[] { 1, 0 });
			nn.learnStochasticGradientDescent(new float[] { 1, 0 }, new float[] { 1, 0 });
			nn.learnStochasticGradientDescent(new float[] { 1, 1 }, new float[] { 0, 1 });
		}

		{
			float[] a = nn.feedfarward(new float[] { 0, 0 });
			int r = NeuralMath.activationsToInt(a);
			Assertions.assertEquals(0, r);
		}
		{
			float[] a = nn.feedfarward(new float[] { 0, 1 });
			int r = NeuralMath.activationsToInt(a);
			Assertions.assertEquals(0, r);
		}
		{
			float[] a = nn.feedfarward(new float[] { 1, 0 });
			int r = NeuralMath.activationsToInt(a);
			Assertions.assertEquals(0, r);
		}
		{
			nn.startMiniBatch();
			float[] a = nn.feedfarward(new float[] { 1, 1 });
			nn.printLayers();
			int r = NeuralMath.activationsToInt(a);
			Assertions.assertEquals(1, r);
			nn.endMiniBatch();
		}
	}

}
