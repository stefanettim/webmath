package groups;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

	public static List<Permutation> permute(Permutation p) {
		return permute(p, true);
	}

	public static List<Permutation> permute(Permutation p, boolean top) {
		List<Permutation> al = new ArrayList<Permutation>();
		int o = p.order;

		if (o == 1) {
			al.add(p);
			return al;
		}

		int[] x = new int[1];
		for (int i = 0; i < o; i++) {
			x[0] = p.set[i];
			Permutation sub = p.remove(i);
			for (Permutation s : permute(sub, false)) {
				Permutation r = new Permutation(x, s.set);
				al.add(r);

				if (top) {

				}
			}
		}

		return al;
	}

}
