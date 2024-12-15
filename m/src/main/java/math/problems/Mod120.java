package math.problems;

import java.util.HashSet;
import java.util.Set;

import math.MyMath;

public class Mod120 {

	public static void main(String[] args) {

		Set<Long> e2Mod120 = new HashSet<Long>();
		Set<Long> e3Mod120 = new HashSet<Long>();
		Set<Long> e5Mod120 = new HashSet<Long>();

		for (int n = 0; n < 12; n++) {

			long ne2 = MyMath.pow(2l, n);
			long ne3 = MyMath.pow(3l, n);
			long ne5 = MyMath.pow(5l, n);

			Long t2 = ne2 % 120l;
			Long t3 = ne3 % 120l;
			Long t5 = ne5 % 120l;

			boolean b2 = e2Mod120.contains(t2);
			boolean b3 = e3Mod120.contains(t3);
			boolean b5 = e5Mod120.contains(t5);

			if (!b2)
				e2Mod120.add(t2);
			if (!b3)
				e3Mod120.add(t3);
			if (!b5)
				e5Mod120.add(t5);

			System.out.printf("%3d %3d %3d %3d\n", n, t2, t3, t5);
		}

		System.out.println("t2:" + e2Mod120.size() + " t3:" + e3Mod120.size() + " t5:" + e5Mod120.size());

		System.out.println("2^n mod 120");
		for (Long t : e2Mod120)
			System.out.println(t);

		System.out.println("3^n mod 120");
		for (Long t : e3Mod120)
			System.out.println(t);

		System.out.println("5^n mod 120");
		for (Long t : e5Mod120)
			System.out.println(t);

		int c = 1;
		for (Long t2 : e2Mod120)
			for (Long t3 : e3Mod120)
				for (Long t5 : e5Mod120) {
					long tot = t2 + t3 + t5;
					long m120 = tot % 120l;

					boolean ok = (m120 == 0);

					System.out.printf("%5d ) %3d %3d %3d ---> %3d %5b\n", c++, t2, t3, t5, m120, ok);
				}

	}

}
