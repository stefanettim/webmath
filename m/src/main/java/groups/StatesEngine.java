package groups;

import java.util.TreeMap;

import groups.generators.Generator;
import groups.generators.State;
import groups.generators.StatesGroup;

public class StatesEngine {

	public static Group states2actions(StatesGroup statesGroup) throws Exception {
		int order = statesGroup.getStates().size();

		if (order == 1)
			return Factory.getIdentityGroup();

		Group group = new Group(order, statesGroup.getLabel());

		TreeMap<Integer, State> actionStateMap = new TreeMap<Integer, State>();
		TreeMap<String, Integer> stateActionMap = new TreeMap<String, Integer>();

		int k = 0;
		for (State state : statesGroup.getStates().values()) {
			actionStateMap.put(k, state);
			group.table[0][k] = k;
			group.table[k][0] = k;
			stateActionMap.put(state.toString(), k);
			group.actions.add(k, new Action(state.printActions()));
			k++;
		}

		for (int i = 1; i < order; i++) {
			for (int j = 1; j < order; j++) {
				State si = actionStateMap.get(i);
				State sj = actionStateMap.get(j);

				State p = si;
				for (Generator a : sj.getActions()) {
					p = a.doAction(p);
				}

				int pi = stateActionMap.get(p.toString());

				group.table[i][j] = pi;
			}

		}

		return group;

	}

}
