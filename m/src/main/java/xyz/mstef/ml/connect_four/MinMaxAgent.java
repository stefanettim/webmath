package xyz.mstef.ml.connect_four;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MinMaxAgent extends Agent {

	private int maxLevel;

	public MinMaxAgent() {
		this(6);
	}

	public MinMaxAgent(int maxLevel) {
		super();
		this.maxLevel = maxLevel;
	}

	/** logger */
	private static final Logger LOG = LogManager.getLogger(MinMaxAgent.class);

	public List<Action> chooseBestActions(State state) {
		return chooseBestActions(state, 0);
	}

	public List<Action> chooseBestActions(State state, int level) {

		List<Action> actions = state.getAvailableActions();

		if (level >= maxLevel) {
			return actions;
		}

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
				Action next = chooseBestFirstAction(after, level + 1);
				reward = next.getReward();
			}

			action.setReward(reward / level);

			if (LOG.isTraceEnabled()) {
				String pre = "";
				if (level < 2) {
					for (int i = 1; i < level; i++) {
						pre += " - ";
					}
					LOG.trace(pre + state.printTurn() + " " + action.getCol() + " r:" + action.getReward());
				}
			}

			float myReward = reward * state.getTurn();

			if (!first && (myReward == value)) {
				goodMoves.add(action);
			}

			if (first || (myReward > value)) {
				value = myReward;
				first = false;
				goodMoves = new ArrayList<>();
				goodMoves.add(action);
			}

		}

		return goodMoves;
	}

	@Override
	public Action move(State state) {
		return chooseBestRandomAction(state);
	}

}
