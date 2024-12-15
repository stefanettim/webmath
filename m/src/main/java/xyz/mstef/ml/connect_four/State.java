package xyz.mstef.ml.connect_four;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class State implements Serializable {

	private static final long serialVersionUID = 796653661564240000L;

	final static int O = +1;
	final static int E = 0;
	final static int X = -1;

	private int[][] board = new int[][] { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 } };

	int[] hwm = new int[] { 0, 0, 0, 0, 0, 0, 0 };

	private int lastMove = -1;
	private int turn = O;
	private int winner = E;
	private boolean full = false;
	private boolean over = false;
	private boolean error = false;

	private int perfectMove = -1;

	private float lastReward;

	public State() {
		super();
	}

	public State init(int[][] s, int turn, int lastMove, int perfect) {
		for (int c = 0; c < 7; c++) {
			for (int r = 0; r < 6; r++) {
				board[r][c] = s[r][c];
				if (board[r][c] != State.E) {
					hwm[c] = r + 1;
				}
			}
		}
		this.turn = turn;
		this.lastMove = lastMove;
		this.perfectMove = perfect;
		check();

		return this;
	}

	public List<Action> getAvailableActions() {

		List<Action> empty = new ArrayList<>();

		for (int c = 0; c < 7; c++) {
			if (hwm[c] < 6) {
				empty.add(new Action(c));
			}
		}

		return empty;
	}

	public int[][] getBoard() {
		return board;
	}

	public int getTurn() {
		return turn;
	}

	private String printSquare(int r, int c) {

		int v = board[r][c];

		if ((c == lastMove) && (r == hwm[c] - 1)) {
			return printLastValue(v);
		}

		return printValue(v);
	}

	private void check() {

		int c = lastMove;
		int r = hwm[lastMove] - 1;
		int lastValue = board[r][c];

		int rr = r;
		int cc = c;
		boolean loop = true;
		int tot = 1;

		for (int[] d : new int[][] { { 1, 0 }, { 0, 1 }, { 1, 1 }, { 1, -1 } }) {
			tot = 1;

			for (int v : new int[] { 1, -1 }) {

				rr = r;
				cc = c;
				loop = true;

				while (loop) {

					rr = rr + v * d[0];
					cc = cc + v * d[1];

					if (((rr < 6) && (cc < 7) && (rr >= 0) && (cc >= 0))) {
						if (board[rr][cc] == lastValue) {
							tot++;
						} else {
							break;
						}
					} else {
						loop = false;
					}

				}

				if (tot >= 4) {
					winner = lastValue;
					break;
				}

			}

			if (isWinning()) {
				break;
			}

		}

		boolean available = false;
		for (int h = 0; h < 7; h++) {
			if (hwm[h] < 6) {
				available = true;
				break;
			}
		}

		if (!available) {
			full = true;
		}

		if (full || isWinning()) {
			turn = E;
			over = true;
		}
	}

	public boolean isFull() {
		return full;
	}

	public boolean isWinning() {
		return winner != E;
	}

	public boolean isTie() {
		return full && winner == E;
	}

	public int getWinner() {
		return winner;
	}

	public boolean isOver() {
		return over;
	}

	public String printTurn() {
		return printValue(turn);
	}

	public String printValue(int v) {
		if (v == E) {
			return " ";
		} else if (v == X) {
			return "x";
		} else if (v == O) {
			return "o";
		} else {
			return "?";
		}
	}

	public String printLastValue(int v) {
		if (v == E) {
			return " ";
		} else if (v == X) {
			return "X";
		} else if (v == O) {
			return "O";
		} else {
			return "?";
		}
	}

	public float getLastReward() {
		return lastReward;
	}

	public void setLastReward(float lastReward) {
		this.lastReward = lastReward;
	}

	public void print() {
		print("");
	}

	public int getValue(int r, int c) {
		return board[r][c];
	}

	public void move(int c) {
		board[hwm[c]][c] = turn;
		turn *= -1;
		lastMove = c;
		hwm[c]++;
		check();
	}

	public State clone() {
		State x = new State();
		for (int r = 0; r < 6; r++) {
			for (int c = 0; c < 7; c++) {
				x.board[r][c] = board[r][c];
			}
		}
		for (int c = 0; c < 7; c++) {
			x.hwm[c] = hwm[c];
		}
		x.turn = turn;
		x.over = over;
		x.full = full;
		x.error = error;
		x.winner = winner;
		x.lastReward = lastReward;
		return x;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public int getLastMove() {
		return lastMove;
	}

	public void print(String title) {
		System.out.println("======= " + title);
		System.out.println("over:" + isOver() + " turn:" + printValue(turn) + " winning:" + isWinning() + " winner:"
				+ printValue(getWinner()) + " full:" + isFull() + " tie:" + isTie());

		for (int r = 5; r >= 0; r--) {
			for (int c = 0; c < 7; c++) {
				System.out.print("|" + printSquare(r, c));
			}
			System.out.println("|");
		}

		if (perfectMove >= 0) {
			System.out.println("perfect move : " + perfectMove);
		}

		System.out.println();
	}

	public int getValue(int c) {
		int r = hwm[c];
		int v = board[r][c];
		return v;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public int getPerfectMove() {
		return perfectMove;
	}

	public void setPerfectMove(int perfectMove) {
		this.perfectMove = perfectMove;
	}

	public static String getFilename(int ch, int pr) {
		String filename = String.format("connect_four/ch%02dpr%02dState.ser", ch, pr);
		return filename;
	}

	public static void save(State state, int ch, int pr) throws IOException {

		FileOutputStream fileOut = new FileOutputStream(getFilename(ch, pr));
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(state);
		out.close();
		fileOut.close();

	}

	public static State load(int ch, int pr) throws IOException, ClassNotFoundException {
		FileInputStream fileIn = new FileInputStream(getFilename(ch, pr));
		ObjectInputStream in = new ObjectInputStream(fileIn);
		State s = (State) in.readObject();
		in.close();
		fileIn.close();

		s.rebuildHwm();

		return s;
	}

	private void rebuildHwm() {
		for (int c = 0; c < 7; c++) {
			hwm[c] = 0;
			for (int r = 0; r < 6; r++) {
				if (board[r][c] != State.E) {
					hwm[c] = r + 1;
				}
			}
		}

	}

}
