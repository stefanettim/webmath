package neural.examples;

import neural.Machine;
import neural.NeuralMath;

public class NandMachine {

	public static void main(String[] args) {

		Machine m = new Machine(2, new int[] { 2 }, 1);
		m.randomizeWeights();
		m.setLearn(3f);
		m.setOutputThreshold(0.1f);
		m.printLayers();
		System.out.println("-----------------------------------------------------------------");

		for (int i = 0; i < 100; i++) {
			System.out.println("epoch " + i);
			float cost = 0;
			cost += m.learnStochasticGradientDescent(new float[] { 0, 0 }, new float[] { 1 });
			cost += m.learnStochasticGradientDescent(new float[] { 1, 0 }, new float[] { 1 });
			cost += m.learnStochasticGradientDescent(new float[] { 0, 1 }, new float[] { 1 });
			cost += m.learnStochasticGradientDescent(new float[] { 1, 1 }, new float[] { 0 });
			m.printLayers();
			System.out.println("-------------------------");
			if (cost < 0.4)
				break;
		}

		System.out.println("0 0 : " + NeuralMath.stringVector(m.feedfarward(new float[] { 0, 0 })));
		System.out.println("1 0 : " + NeuralMath.stringVector(m.feedfarward(new float[] { 1, 0 })));
		System.out.println("0 1 : " + NeuralMath.stringVector(m.feedfarward(new float[] { 0, 1 })));
		System.out.println("1 1 : " + NeuralMath.stringVector(m.feedfarward(new float[] { 1, 1 })));

	}

}
