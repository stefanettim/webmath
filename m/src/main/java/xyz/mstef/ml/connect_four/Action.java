package xyz.mstef.ml.connect_four;

public class Action {

	private int col;
	private float reward = 0;

	public Action(int c) {
		super();
		this.col = c;
	}

	public float getReward() {
		return reward;
	}

	public void setReward(float reward) {
		this.reward = reward;
	}

	public int getCol() {
		return col;
	}

}
