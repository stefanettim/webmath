package xyz.mstef.ml.connect_four;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Environment {

	private static final Logger LOG = LogManager.getLogger(MinMaxAgent.class);

	Agent agentO;
	Agent agentX;

	public Environment(Agent agentO, Agent agentX) {
		super();
		this.agentO = agentO;
		this.agentX = agentX;
	}

	public static State perform(State before, Action action) {

		State state = (State) before.clone();

		int c = action.getCol();

		int s = state.getValue(c);

		if (s != State.E) {
			state.setError(true);
			LOG.error("invalid move " + c);
			return state;
		}

		state.move(c);

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

			action = agent.chooseBestRandomAction(state);

			state = perform(state, action);

			if (print) {
				state.print();
			}
		}

		return state;
	}

	public static void main(String[] args) throws InvalidMoveException, CloneNotSupportedException {

		Agent o = new TerminalAgent();
		Agent x = new MinMaxAgent(10);

		Environment e = new Environment(o, x);

		e.startGame(true);
	}

}
