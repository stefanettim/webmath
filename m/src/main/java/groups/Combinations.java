package groups;

import math.MyMath;

public class Combinations {

	protected int order;
	protected int size;

	public Combinations(int order, int size) {
		super();
		this.order = order;
		this.size = size;
	}

	public long getSize() throws Exception {
		long perms = MyMath.factorial(order);
		long dup = MyMath.factorial(size) * MyMath.factorial(order - size);
		long tot = perms / dup;
		return tot;
	}

	public Permutation getCombination(Permutation perm, long position) throws Exception {
		if (perm.order == size) {
			return perm;
		}

		if (size == 1) {
			if (position >= perm.order) {
				System.out.println("position over order");
				position = perm.order - 1;
			}
			int v = perm.set[(int) position];
			int[] s = new int[] { v };
			return new Permutation(s);
		}

		long c0 = getSize() * size / order;
		Permutation res;

		if (position < c0) {
			Permutation rem = perm.remove(0);
			Combinations subc = new Combinations(order - 1, size - 1);
			Permutation sub = subc.getCombination(rem, position);
			int[] s0 = new int[] { perm.set[0] };
			int[] ss = sub.set;
			res = new Permutation(s0, ss);
		} else {
			Combinations subc = new Combinations(order - 1, size);
			position = position - c0;
			res = subc.getCombination(perm.remove(0), position);
		}

		return res;
	}

	public Permutation getCombination(long position) throws Exception {
		Permutation start = new Permutation(order);
		return getCombination(start, position);
	}

	public String toString() {
		String s;
		try {
			s = "Combinations (" + order + " " + size + ") tot:" + getSize() + " " + new Permutation(order);
		} catch (Exception e) {
			s = e.getMessage();
			e.printStackTrace();
		}
		return s;
	}

	public void print() throws Exception {
		System.out.println("=== " + this);
		for (int i = 0; i < getSize(); i++)
			System.out.println(i + " : " + getCombination(i));
		System.out.println("------------------------------");
	}

}
