package neural.examples;

import neural.Machine;
import neural.NeuralMath;

public class BatchedNandMachine {

	public static void main(String[] args) {

		Machine m = new Machine(2, new int[] {}, 1);
		m.randomizeWeights();
		m.setLearn(3f);
		m.setOutputThreshold(0.1f);
		m.printLayers();
		System.out.println("-----------------------------------------------------------------");

		int epochs = 0;

		for (int i = 0; i < 100; i++) {
			System.out.println("====== epoch " + i);

			m.startMiniBatch();
			m.evaluate(new float[] { 0, 0 }, new float[] { 1 });
			m.evaluate(new float[] { 1, 0 }, new float[] { 1 });
			m.endMiniBatch();
			m.learnMiniBatch();
			System.out.println("--- minibatch 1");
			m.printLayers();

			m.startMiniBatch();
			m.evaluate(new float[] { 0, 1 }, new float[] { 1 });
			m.evaluate(new float[] { 1, 1 }, new float[] { 0 });
			m.endMiniBatch();
			m.learnMiniBatch();
			System.out.println("--- minibatch 2");
			m.printLayers();

			epochs++;
			if (m.getMiniBatchCost() < 0.1)
				break;
		}
		System.out.println("-----------------------------------------------------------------");
		System.out.println("epochs:" + epochs);

		System.out.println("0 0 : " + NeuralMath.stringVector(m.feedfarward(new float[] { 0, 0 })));
		System.out.println("1 0 : " + NeuralMath.stringVector(m.feedfarward(new float[] { 1, 0 })));
		System.out.println("0 1 : " + NeuralMath.stringVector(m.feedfarward(new float[] { 0, 1 })));
		System.out.println("1 1 : " + NeuralMath.stringVector(m.feedfarward(new float[] { 1, 1 })));

	}

}
