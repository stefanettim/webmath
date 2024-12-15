package math.problems;

import numbers.MException;
import numbers.NumberTheory;

public class WildestExponential {

	/*
	 * https://www.youtube.com/watch?v=2MLvSz5ThFw&list=PLBC2mAC-ElrCuY70uDDzPbKMXwXGjUiaJ&index=1
	 * m^n^2 = n^(m+2)
	 */

	public static long wild(long a, long b) throws MException {
			long e1 = b*b;
			long m1 = NumberTheory.power(a,e1);
			long e2 = a+2;
			long m2 = NumberTheory.power(b,e2);
			long d=m1-m2;
			System.out.println("a="+a+" b="+b+" m1="+m1+" m2="+m2+" d="+d);
			
			return d;
	}
	
	public static void main(String[] args) throws MException {
		
		for (long a = 1; a < 6; a++) {
			for (long b = 1; b < 6; b++) {
				wild(a,b);
			}
		}

	}

}
