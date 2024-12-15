package math;

import java.util.HashSet;

public class MyMath {

	private static long[] factorials = new long[21];

	static {
		factorials[0] = 1;
		for (int i = 1; i < 21; i++)
			factorials[i] = factorials[i - 1] * i;
	}

	public static long factorial(long n) throws Exception {
		if (n < 21)
			return factorials[(int) n];

		long p = 1;
		for (long i = 1; i < n + 1; i++) {
			p *= i;
			if (p < 0)
				throw new Exception("factorial too big " + n + "!");
		}

		return p;
	}

	public static HashSet<Long> factorials() {
		HashSet<Long> hs = new HashSet<Long>();
		for (long l : factorials)
			hs.add(l);
		return hs;
	}

	public static long pow(long l, long n) {
		if (n == 0)
			return 1;

		long r = 1;
		for (long i = 0; i < n; i++)
			r *= l;
		return r;
	}
}
