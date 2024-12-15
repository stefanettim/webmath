package xyz.mstef.ml.tic_tac_toe;

public class Action {

	private int move;
	private float reward = 0;

	public Action(int m) {
		super();
		this.move = m;
	}

	public int getMove() {
		return move;
	}

	public float getReward() {
		return reward;
	}

	public void setReward(float reward) {
		this.reward = reward;
	}

}
