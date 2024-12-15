package xyz.mstef.ml.tic_tac_toe;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MinMaxAgent extends Agent {

	/** logger */
	private static final Logger LOG = LogManager.getLogger(MinMaxAgent.class);

	public Action move(State s) {
		Action a = chooseBest(s, 1);
		return a;
	}

	private Action chooseBest(State state, int level) {

		List<Action> actions = state.getAvailableActions();

		if (level == 1) {
			LOG.trace("av " + actions.size());
		}

		List<Action> goodMoves = new ArrayList<>();

		float value = 0;
		boolean first = true;

		for (Action action : actions) {

			float reward = 0;

			State after = Environment.perform(state, action);

			if (after.isOver()) {
				reward = after.getLastReward();
			} else {
				Action next = chooseBest(after, level + 1);
				reward = next.getReward();
			}

			action.setReward(reward / level);

			String pre = "";
			if (level < 3) {
				for (int i = 1; i < level; i++) {
					pre += " - ";
				}
				LOG.trace(pre + state.printTurn() + " " + action.getMove() + " r:" + action.getReward());
			}

			float myReward = reward * state.getTurn();

			if (!first && (myReward == value)) {
				goodMoves.add(action);
			}

			if (first || (myReward > value)) {
				value = myReward;
				first = false;
				goodMoves = new ArrayList<>();
			}

			if (myReward >= value) {
				goodMoves.add(action);
			}

		}

		// int p = (int) ( Math.random() * goodMoves.size() );
		int p = 0;

		Action best = goodMoves.get(p);

		return best;
	}

}
