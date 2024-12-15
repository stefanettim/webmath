package math.problems;

import java.util.HashSet;

import math.MyMath;

public class Brute {

	/*
	 * 2^a+3^b+5^c=n!
	 * 
	 */

	public static void main(String[] args) {

		HashSet<Long> factorials = math.MyMath.factorials();

		int t = 0;

		for (int a = 0; a < 60; a++)
			for (int b = 0; b < 60; b++)
				for (int c = 0; c < 60; c++) {
					long s = MyMath.pow(2l, a) + MyMath.pow(3l, b) + MyMath.pow(5l, c);
					if (factorials.contains(s))
						System.out.println("2^" + a + " + 3^" + b + " + 5^" + c + " = " + s);
					t++;
				}

		System.out.println(t);
	}

}
