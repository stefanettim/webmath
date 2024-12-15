package xyz.mstef.ml.tic_tac_toe;

import neural.Machine;

public class NNMachine extends Machine {

	private static final long serialVersionUID = -8137985214451845420L;

	protected static int[] hiddenLayers = new int[] { 90 };
	protected float learn = 0.1f;

	public static NNMachine instance = new NNMachine();

	public NNMachine() {
		super(9, hiddenLayers, 9);
		randomizeWeights();
		setLearn(1f);
		setOutputThreshold(0f);

	}

	public int process(float[] input) {

		float[] output = feedfarward(input);

		float max = 0;

		int m = 0;

		for (int i = 0; i < 9; i++) {
			if ((input[i] == State.E) && (output[i] > max)) {
				max = output[i];
				m = i;
			}
		}

		return m;
	}

	public static NNMachine getInstance() {
		return instance;
	}

}
