package groups.games.npuzzle;

import groups.generators.State;

public class NPuzzleState extends State implements Cloneable {

	private int msize = -1;
	private int nsize = -1;
	private int mblank = -1;
	private int nblank = -1;
	private int blank = -1;

	private int[][] table;

	public NPuzzleState(int m, int n) {
		super(m * n);
		this.msize = m;
		this.nsize = n;
		this.blank = m * n;
		table = new int[m][n];
	}

	public void reset() {
		int p = 1;
		for (int j = 0; j < nsize; j++)
			for (int i = 0; i < msize; i++)
				table[i][j] = p++;

		mblank = msize - 1;
		nblank = nsize - 1;
	}

	public void moveUp() throws CloneNotSupportedException {
		if (nblank > 0) {
			table[mblank][nblank] = table[mblank][nblank - 1];
			table[mblank][nblank - 1] = blank;
			nblank = nblank - 1;
		} else if (nblank == 0) {
			for (int j = 1; j < nsize; j++)
				table[mblank][j - 1] = table[mblank][j];
			table[mblank][nsize - 1] = blank;
			nblank = nsize - 1;
		}

	}

	public void moveLeft() throws CloneNotSupportedException {
		if (mblank > 0) {
			table[mblank][nblank] = table[mblank - 1][nblank];
			table[mblank - 1][nblank] = blank;
			mblank = mblank - 1;
		} else if (mblank == 0) {
			for (int j = 1; j < msize; j++)
				table[j - 1][nblank] = table[j][nblank];
			table[msize - 1][nblank] = blank;
			mblank = msize - 1;
		}

	}

	public void moveDown() throws CloneNotSupportedException {
		if (nblank < nsize - 1) {
			table[mblank][nblank] = table[mblank][nblank + 1];
			table[mblank][nblank + 1] = blank;
			nblank = nblank + 1;
		} else if (nblank == nsize - 1) {
			for (int j = nsize - 1; j > 0; j--)
				table[mblank][j] = table[mblank][j - 1];
			table[mblank][0] = blank;
			nblank = 0;
		}

	}

	private void printLine(int x) {
		for (int i = 0; i < x; i++)
			System.out.print("-");
		System.out.println();
	}

	public void print() {
		int max = ("" + (blank)).length();
		for (int j = 0; j < nsize; j++) {
			// printLine(msize*(max+1)+1);
			for (int i = 0; i < msize; i++) {
				String v = "" + table[i][j];
				if (table[i][j] == blank)
					v = "#";
				System.out.printf("|%" + max + "s", v);
			}
			System.out.println("|");
		}
		printLine(msize * (max + 1) + 1);

	}

	public String toString() {
		String s = "";
		for (int j = 0; j < nsize; j++)
			for (int i = 0; i < msize; i++)
				s += " " + table[i][j];

		return s;
	}

	public int getMblank() {
		return mblank;
	}

	public int getNblank() {
		return nblank;
	}

	public int[][] getTable() {
		return table;
	}

	public int getMsize() {
		return msize;
	}

	public int getNsize() {
		return nsize;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		NPuzzleState c = (NPuzzleState) super.clone();
		c.table = new int[msize][nsize];
		for (int j = 0; j < nsize; j++)
			for (int i = 0; i < msize; i++)
				c.table[i][j] = table[i][j];
		return c;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof NPuzzleState))
			return false;

		NPuzzleState s = (NPuzzleState) o;

		for (int j = 0; j < nsize; j++)
			for (int i = 0; i < msize; i++)
				if (s.table[i][j] != table[i][j])
					return false;

		return true;
	}

}
