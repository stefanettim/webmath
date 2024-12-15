package xyz.mstef.ml.tic_tac_toe;

public class NNAgent extends Agent {
	
	public Action move(State s) {

		float[] input = new float[9];
		float[] output = new float[9];

		for (int f = 0; f < 9; f++) {

			input[f] = (float) s.getBoard()[f];

			if (s.getBoard()[f] == State.E) {
				output[f] = 1f;
			} else {
				output[f] = -1f;
			}

		}

		int m = NNMachine.getInstance().process(input);

		NNMachine.getInstance().printActivations();

		return new Action(m);

	}

}
