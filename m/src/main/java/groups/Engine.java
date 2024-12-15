package groups;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import math.MyMath;

public class Engine {

	public static boolean validate(Group g) {
		boolean i = validateIdentityElement(g);
		if (!i)
			return false;

		boolean in = validateInverseElement(g);
		if (!in)
			return false;

		boolean c = validateClosure(g);
		if (!c)
			return false;

		boolean a = validateAssociativity(g);
		if (!a)
			return false;

		return true;
	}

	public static boolean validateIdentityElement(Group g) {
		for (int i = 0; i < g.order; i++)
			if ((g.table[i][0] != i) || (g.table[0][i] != i))
				return false;
		return true;
	}

	public static boolean validateInverseElement(Group g) {
		for (int i = 0; i < g.order; i++) {
			boolean inverse = false;
			for (int j = 0; j < g.order; j++) {
				if ((g.table[i][j] == 0) && (g.table[j][i] == 0))
					inverse = true;

			}
			if (!inverse)
				return false;
		}
		return true;
	}

	public static boolean validateAssociativity(Group g) {
		for (int i = 0; i < g.order; i++)
			for (int j = 0; j < g.order; j++)
				for (int k = 0; k < g.order; k++) {
					int ij = g.table[i][j];
					int ij_k = g.table[ij][k];

					int jk = g.table[j][k];
					int i_jk = g.table[i][jk];

					if (ij_k != i_jk)
						return false;

					// System.out.println("("+i+j+")"+k+"="+ij+k+"="+ij_k+"
					// "+i+"("+j+k+")="+i+jk+"="+i_jk);
				}

		return true;
	}

	public static boolean validateClosure(Group g) {

		if (g.order == 1)
			return true;

		for (int i = 1; i < g.order; i++) {
			for (int j = 0; j < g.order; j++) {
				boolean closed = false;
				for (int t = 0; t < g.order; t++) {
					if (g.table[0][t] == g.table[i][j]) {
						closed = true;
						break;
					}
				}
				if (!closed) {
					return false;
				}
			}
		}
		return true;
	}

	public static Group closedSubset(Group g, Permutation p) throws CloneNotSupportedException {
		int o = p.order;
		Group m = new Group(o, "Sub");
		for (int i = 0; i < o; i++) {
			int pi = p.set[i];
			for (int j = 0; j < o; j++) {
				int pj = p.set[j];
				int pk = g.table[pi][pj];
				if (i > 0) {
					boolean closed = false;
					for (int t = 0; t < o; t++) {
						if (pk == m.table[0][t]) {
							closed = true;
							break;
						}
					}
					if (!closed)
						return null;
				}
				m.table[i][j] = pk;
			}

			m.actions.add(i, (Action) g.actions.get(pi).clone());
		}
		m.name = "Sub" + m.actionsString();
		return m;
	}

	public static HashSet<Group> subgroups(Group g) throws Exception {
		HashSet<Group> hs = new HashSet<>();
		hs.add(Factory.getIdentityGroup());
		hs.add(g);

		for (int o = 2; o < g.order - 1; o++) {

			if (g.order % o == 0) {

				Combinations ps = new Combinations(g.order, o);
				long f = ps.getSize();
				for (int n = 0; n < f * o / g.order; n++) {
					Permutation p = ps.getCombination(n);
					Group m = closedSubset(g, p);
					if (m != null)
						hs.add(m);
				}
			}

		}

		return hs;
	}

	public static HashSet<Group> groups(int order) throws Exception {
		HashSet<Group> hs = new HashSet<>();
		return hs;
	}

	public static Group subgroup(Group s, Group g) throws Exception {

		if (s.order > g.order)
			return null;

		Combinations ps = new Combinations(g.order, s.order);
		long f = ps.getSize();
		for (long n = 0; n < f; n++) {
			Permutation p = ps.getCombination(n);
			Group m = closedSubset(g, p);
			if (m != null)
				if (isomorphism(s, m))
					return m;
		}

		return null;
	}

	public static List<Group> findGroups(int order) throws Exception {
		List<Group> known = new ArrayList<Group>();
		if (order == 1) {
			known.add(Factory.getIdentityGroup());
			return known;
		}

		Group g = new Group(order);
		for (int i = 0; i < order; i++) {
			g.table[0][i] = i;
			g.table[i][0] = i;
			g.actions.add(i, new Action("" + i));
		}

		completeRow(g, 1, known);

		return known;

	}

	private static void completeRow(Group g, int row, List<Group> known) throws Exception {

		if (row == g.order) {
			// System.out.println("validate"+g);
			boolean a = Engine.validateAssociativity(g);

			if (a) {
				// System.out.println("validate OK");
				boolean found = false;

				for (Group k : known) {
					if (Engine.isomorphism(g, k))
						found = true;
				}

				if (!found) {
					Group x = (Group) g.clone();
					known.add(x);
					// System.out.println("Found o:"+g.order+" "+known.size());
					// x.printText();
				}
			} else {
				// System.out.println("KO");
			}

			return;
		}

		int[] sub = new int[g.order - 1];
		int p = 0;
		for (int j = 0; j < g.order; j++) {
			if (j != row)
				sub[p++] = j;
		}

		Permutation perm = new Permutation(sub);
		long tot = perm.getPermutations();

		long step = 0;
//		long done=0;
		for (Permutation permsub : Permutations.permute(perm)) {
//			done++;
			step++;
			if ((row == 1) && (step > tot / 1000)) {
				// System.out.println("progress "+(1000*done/tot)+" 0/00");
				step = 0;
			}

			// Permutation permsub = perm.permute(pn);
			boolean colValid = true;
			for (int i = 1; i < g.order; i++) {
				int v = permsub.set[i - 1];
				for (int k = 0; k < row; k++) {
					if (g.table[k][i] == v) {
						colValid = false;
						break;
					}
				}

				if (!colValid)
					break;

				g.table[row][i] = v;
			}

			if (colValid)
				completeRow(g, row + 1, known);

		}
	}

	public static boolean isomorphism(Group a, Group b) throws Exception {
		if (a.order != b.order)
			return false;

		a.rebuildTable();
		b.rebuildTable();

		long t = MyMath.factorial(a.order);

		Permutation ps = new Permutation(a.order);

		for (long n = 0; n < t; n++) {

			Permutation p = ps.permute(n);
			boolean iso = true;
			for (int i = 0; i < a.order; i++) {
				boolean row = true;
				for (int j = 0; j < a.order; j++) {
					int ija = a.table[i][j];

					int mi = p.set[i];
					int mj = p.set[j];

					int mv = b.table[mi][mj];

					int v = 0;
					for (int k = 0; k < a.order; k++)
						if (mv == p.set[k])
							v = k;

					if (ija != v) {
						row = false;
						break;
					}
				}
				if (!row) {
					iso = false;
					break;
				}
			}

			if (iso)
				return true;
		}

		return false;

	}

	public static Group squared(Group g) {
		int o = 0;
		int[] newActions = new int[g.order];

		for (int i = 0; i < g.order; i++) {
			int r = g.table[i][i];
			boolean newValue = true;
			for (int k = 0; k < o; k++)
				if (newActions[k] == r)
					newValue = false;
			if (newValue)
				newActions[o++] = r;
		}

		Group s = new Group(o);
		s.name = g.name + " squared";

		for (int i = 0; i < o; i++)
			s.actions.set(i, g.actions.get(newActions[i]));

		for (int i = 0; i < o; i++)
			for (int j = 0; j < o; j++) {
				int original = g.table[newActions[i]][newActions[j]];
				int newvalue = 0;
				for (int k = 0; k < o; k++)
					if (newActions[k] == original)
						newvalue = k;
				s.table[i][j] = newvalue;
			}

		return s;
	}

}
