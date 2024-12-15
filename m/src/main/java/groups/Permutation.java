package groups;

import math.MyMath;

public class Permutation {

	protected int order;
	public int[] set;

	public Permutation(int o) {
		order = o;
		set = new int[order];
		for (int i = 0; i < order; i++)
			set[i] = i;
	}

	public Permutation(int[] s) {
		order = s.length;
		set = new int[order];
		for (int i = 0; i < order; i++)
			set[i] = s[i];
	}

	public Permutation(int[] s, int[] g) {
		order = s.length + g.length;
		set = new int[order];
		for (int i = 0; i < s.length; i++)
			set[i] = s[i];
		for (int i = 0; i < g.length; i++)
			set[s.length + i] = g[i];
	}

	@Override
	public boolean equals(Object o) {
		Permutation p = (Permutation) o;
		if (order != p.order)
			return false;

		for (int i = 0; i < order; i++)
			if (set[i] != p.set[i])
				return false;

		return true;
	}

	public String toString() {
		String r = "[";
		for (int i = 0; i < set.length - 1; i++)
			r += set[i] + ",";
		r += "" + set[set.length - 1] + "]";
		return r;
	}

	public Permutation remove(int position) {
		int[] res = new int[order - 1];
		int p = 0;
		for (int i = 0; i < order; i++)
			if (i != position)
				res[p++] = set[i];
		return new Permutation(res);
	}

	public Permutation sub(int size) {
		int[] res = new int[size];
		for (int i = 0; i < size; i++)
			res[i] = set[i];
		return new Permutation(res);
	}

	public Permutation permute(long position) throws Exception {
		if (order == 1)
			return this;

		Permutation result = new Permutation(order);

		long blocksize = MyMath.factorial(order - 1);
		int blocks = (int) (position / blocksize);
		long subposition = position - blocks * blocksize;

		result.set[0] = set[(int) blocks];

		Permutation subset = remove(blocks);
		Permutation subpermutation = subset.permute(subposition);

		for (int i = 0; i < order - 1; i++)
			result.set[i + 1] = subpermutation.set[i];

		return result;
	}

	public long getPermutations() throws Exception {
		return MyMath.factorial(order);
	}
}
