package xyz.mstef.ml.tic_tac_toe;

import java.util.ArrayList;
import java.util.List;

public class State {

	final static int O = +1;
	final static int E = 0;
	final static int X = -1;

	private int[] board = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	private int lastMove = -1;
	private int turn = O;
	private int winner = E;
	private boolean full = false;
	private boolean over = false;
	private boolean error = false;

	private float lastReward;

	private int[] perfectMoves = new int[] {};
	private String title = "";

	public State() {
		super();
	}

	public State init(String title, int[] s, int t, int[] perfectMoves) {
		for (int i = 0; i < 9; i++) {
			board[i] = s[i];
		}
		this.turn = t;
		check();

		this.title = title;

		this.perfectMoves = perfectMoves;

		return this;
	}

	public List<Action> getAvailableActions() {

		List<Action> empty = new ArrayList<>();

		for (int i = 0; i < 9; i++) {
			if (getValue(i) == State.E) {
				empty.add(new Action(i));
			}
		}

		return empty;
	}

	public int[] getBoard() {
		return board;
	}

	public float[] getFloatBoard() {

		float[] input = new float[9];
		for (int f = 0; f < 9; f++) {
			input[f] = (float) getBoard()[f];
		}

		return input;
	}

	public int getTurn() {
		return turn;
	}

	private String printSquare(int n) {
		int v = board[n];

		if (n == lastMove) {
			return printLastValue(v);
		}

		return printValue(v);
	}

	private void check() {

		if ((board[0] == board[1]) && (board[1] == board[2])) {
			winner = board[0];
		} else if ((board[3] == board[4]) && (board[4] == board[5])) {
			winner = board[3];
		} else if ((board[6] == board[7]) && (board[7] == board[8])) {
			winner = board[6];
		} else if ((board[0] == board[3]) && (board[3] == board[6])) {
			winner = board[0];
		} else if ((board[1] == board[4]) && (board[4] == board[7])) {
			winner = board[1];
		} else if ((board[2] == board[5]) && (board[5] == board[8])) {
			winner = board[2];
		} else if ((board[0] == board[4]) && (board[4] == board[8])) {
			winner = board[0];
		} else if ((board[2] == board[4]) && (board[4] == board[6])) {
			winner = board[2];
		}

		full = true;
		for (int i = 0; i < 9; i++) {
			if (board[i] == State.E) {
				full = false;
			}
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

	public int getValue(int i) {
		return board[i];
	}

	public void move(int i) {
		board[i] = turn;
		turn *= -1;
		lastMove = i;
		check();
	}

	public State clone() {
		State x = new State();
		for (int i = 0; i < 9; i++) {
			x.board[i] = board[i];
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

	public void printGrid() {
		System.out.println("======= " + title);
		System.out.println("over:" + isOver() + " turn:" + printValue(turn) + " winning:" + isWinning() + " winner:"
				+ printValue(getWinner()) + " full:" + isFull() + " tie:" + isTie());
		System.out.println(printSquare(0) + "|" + printSquare(1) + "|" + printSquare(2));
		System.out.println("-----");
		System.out.println(printSquare(3) + "|" + printSquare(4) + "|" + printSquare(5));
		System.out.println("-----");
		System.out.println(printSquare(6) + "|" + printSquare(7) + "|" + printSquare(8));
		System.out.println();
	}

	public void print() {
		System.out.println("================= " + title);
		System.out.println("over:" + isOver() + " turn:" + printValue(turn) + " winning:" + isWinning() + " winner:"
				+ printValue(getWinner()) + " full:" + isFull() + " tie:" + isTie());
	//	System.out.println("+---+");
		System.out.println("|"+printSquare(0) + "" + printSquare(1) + "" + printSquare(2)+"|");
		System.out.println("|"+printSquare(3) + "" + printSquare(4) + "" + printSquare(5)+"|");
		System.out.println("|"+printSquare(6) + "" + printSquare(7) + "" + printSquare(8)+"|");
	//	System.out.println("+---+");
		System.out.println();
	} 

	public float[] getFloatLegalMoves(float w) {

		float[] moves = new float[9];

		for (int i = 0; i < 9; i++) {
			if (board[i] != State.E) {
				moves[i] = w;
			} else {
				moves[i] = 0f;
			}
		}
		return moves;
	}

	public int[] getPerfectMoves() {
		return perfectMoves;
	}

	public String getTitle() {
		return title;
	}

}
