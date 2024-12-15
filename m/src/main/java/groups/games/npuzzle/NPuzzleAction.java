package groups.games.npuzzle;

import groups.generators.Generator;
import groups.generators.State;

public class NPuzzleAction extends Generator {

	private char direction;

	public NPuzzleAction(char direction, String label) {
		super();
		this.direction = direction;
		this.label = label;
	}

	@Override
	public State doAction(State s) throws Exception {

		NPuzzleState ns = (NPuzzleState) s.clone();

		if (direction == 'U')
			ns.moveUp();
		else if (direction == 'L')
			ns.moveLeft();
		else
			throw new Exception("Unknown action : " + direction);

		ns.getActions().add(this);

		return ns;

	}

}
