package math.problems;

public class DescendingPowers {

	public static long f(long n) {
		long f = 0;

		for (long k = 1; k <= n; k++) {
			long p = 1;
			long j = 0;
			for (j = n - k + 1; j >= 1; j--) {
				p = p * k;
			}
			f = f + p;
		}

		return f;
	}

	public static void main(String[] args) {

		long fp = 1;
		for (long n = 1; n <= 20; n++) {
			long fn = f(n);
			float M = (float) fn / (float) fp;
			System.out.printf("%4d:%16d %3.3f \n", n, fn, M);
			fp = fn;
		}
	}

}
