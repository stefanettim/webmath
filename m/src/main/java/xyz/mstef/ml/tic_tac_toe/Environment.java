package xyz.mstef.ml.tic_tac_toe;

public class Environment {

	Agent agentO;
	Agent agentX;

	public Environment(Agent agentO, Agent agentX) {
		super();
		this.agentO = agentO;
		this.agentX = agentX;
	} 

	public static State perform(State before, Action action) {

		State state = (State) before.clone();

		int m = action.getMove();
 
		int s = state.getValue(m);

		if (s != State.E) {
			state.setError(true);
			return state;
		}

		state.move(m);

		int reward = 0;
		if (state.isWinning()) {
			if (state.getWinner() == State.X) {
				reward = -1;
			} else if (state.getWinner() == State.O) {
				reward = 1;
			}
		}

		state.setLastReward(reward);

		return state;
	}

	public State startGame() {
		return startGame(false);
	}

	public State startGame(boolean print) {

		State state = new State();

		while (!state.isOver()) {

			int turn = state.getTurn();

			Action action = null;

			Agent agent = null;

			if (turn == State.O) {
				agent = agentO;
			} else if (turn == State.X) {
				agent = agentX;
			}

			action = agent.move(state);

			state = perform(state, action);

			if (print) {
				state.print();
			}
		}

		return state;
	}

	public static void main(String[] args) throws InvalidMoveException, CloneNotSupportedException {

		Agent o = new TerminalAgent();
		Agent x = new MinMaxAgent();

		Environment e = new Environment(o, x);

		e.startGame(true);
	}

}
