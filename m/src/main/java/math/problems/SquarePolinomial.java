package math.problems;

import math.MyMath;

public class SquarePolinomial {

	/*
	 * n^4-n^3+3n^2+5 = m^2
	 */

	public static void main(String[] args) {

		for (long n = 0; n < 100000000000l; n++) {

			long p = MyMath.pow(n, 4) - MyMath.pow(n, 3) + 3l * MyMath.pow(n, 2) + 5l;
			long r = (long) Math.sqrt(p);

			boolean square = false;
			if (r * r == p)
				square = true;

			if (square)
				System.out.printf("n: %6d p: %10d m: %6d     %b \n", n, p, r, square);

		}

	}

}
