package xyz.mstef.ml.tic_tac_toe;

public class NNTrainer {

	private static NNMachine nnm = NNMachine.getInstance();

	public static void trainEnding(State state) {

		System.out.println("training " + state.getTitle());

		float[] inputs = state.getFloatBoard();
		float[] outputs = state.getFloatLegalMoves(0.1f);

		for (int p : state.getPerfectMoves()) {
			outputs[p] = 1f;
		}

		nnm.learnStochasticGradientDescent(inputs, outputs);

	}

	public static void trainEndings() {

		for (State state : Factory.endings()) {
			trainEnding(state);
		}

	}

	public static void train() {

		Agent o = new MinMaxAgent();
		Agent x = new NNAgent();

		State state = new State();

		Agent agent = null;

		while (!state.isOver()) {

			int turn = state.getTurn();
			if (turn == State.O) {
				agent = o;
			} else if (turn == State.X) {
				agent = x;
			}

			Action action = agent.move(state);
			int move = action.getMove();
			State after = Environment.perform(state, action);

			if (after.isWinning()) {

				int f = 1;
				if (turn == State.X) {
					f = -1;
				}

				float[] inputs = state.getFloatBoard();
				float[] outputs = state.getFloatLegalMoves(f * state.getTurn() * 0.1f);
				outputs[move] = after.getLastReward();
				nnm.learnStochasticGradientDescent(inputs, outputs);

			}

			state = after;

		}

	}

	public static void trainEpoch() {
		for (int e = 0; e < 100; e++) {
			train();
		}
	}

	public static void trainEndingsEpoch() {
		for (int i = 0; i < 10; i++) {
			NNTrainer.trainEndings();
		}
	}

	public static void main(String[] args) {
		trainEpoch();
	}
}
