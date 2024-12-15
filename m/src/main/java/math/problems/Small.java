package math.problems;

import math.MyMath;

public class Small {

	/*
	 * 2^a+3^b+5^c=n!
	 * 
	 * controllo manualmnente fino a 4! per il 5! serve divisibilità 8,3,5 e
	 * controllo triplette resti, vedi Triplette
	 * 
	 * 1!=1 troppo piccolo 2!=2 troppo piccolo 3!=6 4!=24 5!=120 divisibile per 8 3
	 * 5 --> Triplette
	 * 
	 * possibili fattoriali 6 e 24
	 * 
	 * 2^a<24 a<=4 2^4=16 2^5=32 3^b<24 b<=2 3^2=9 3^3=27 5^c<24 c<=1 5^1=5 5^2=25
	 * 
	 */

	public static void main(String[] args) {

		for (int a = 0; a <= 4; a++)
			for (int b = 0; b <= 2; b++)
				for (int c = 0; c <= 1; c++) {
					int s = (int) (MyMath.pow(2, a) + MyMath.pow(3, b) + MyMath.pow(5, c));
					if ((s == 6) || (s == 24))
						System.out.println("2^" + a + "+3^" + b + "+5^" + c + "=" + s);
				}

	}

}
