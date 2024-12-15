package math.problems;

import java.util.HashSet;
import java.util.Set;

import math.MyMath;

/*
 * 2^a+3^b+5^c = n!
 * 
 * per n<5 vedi Small
 * 
 * n>=5 
 * 5!=120 divide tutti i successivi n! 
 * => 8*3*5 divide n!
 * 
 * calcolo possibili resti di 
 * 2^a mod8 mod3 mod5  triplette del 2 
 * 3^b mod8 mod3 mod5  triplette del 3
 * 5^c mod8 mod3 mod5  triplette del 5
 * 
 * la somma di 2^a mod 8 + 3^b mod 3 + 5^c mod 5 deve essere multiplo di 8 
 * 
 *  
 * i resti si ripetono, le triplette sono poche
 */

class Tripletta // tripletta di resti
{
	long p;
	int m8;
	int m3;
	int m5;

	public Tripletta(long ne2, int m8, int m3, int m5) {
		super();
		this.p = ne2;
		this.m8 = m8;
		this.m3 = m3;
		this.m5 = m5;
	}

	@Override
	public boolean equals(Object x) {
		Tripletta t = (Tripletta) x;
		return t.hashCode() == this.hashCode();
	}

	@Override
	public int hashCode() {
		return (int) (m5 + 10l * m3 + 100l * m8);
	}

	public String toString() {
		return String.format("%8d - %2d %2d %2d ", p, m8, m3, m5, hashCode());
	}

}

public class Triplette {

	public static void main(String[] args) {

		/*
		 * hashSet contengono triplette univoche
		 */
		Set<Tripletta> triplette2 = new HashSet<Tripletta>();
		Set<Tripletta> triplette3 = new HashSet<Tripletta>();
		Set<Tripletta> triplette5 = new HashSet<Tripletta>();

		for (int n = 0; n < 12; n++) {

			long ne2 = MyMath.pow(2l, n);
			long ne3 = MyMath.pow(3l, n);
			long ne5 = MyMath.pow(5l, n);

			Tripletta t2 = new Tripletta(ne2, (int) ne2 % 8, (int) ne2 % 3, (int) ne2 % 5);
			Tripletta t3 = new Tripletta(ne3, (int) ne3 % 8, (int) ne3 % 3, (int) ne3 % 5);
			Tripletta t5 = new Tripletta(ne5, (int) ne5 % 8, (int) ne5 % 3, (int) ne5 % 5);

			boolean b2 = triplette2.contains(t2);
			boolean b3 = triplette3.contains(t3);
			boolean b5 = triplette5.contains(t5);

			if (!b2)
				triplette2.add(t2);
			if (!b3)
				triplette3.add(t3);
			if (!b5)
				triplette5.add(t5);

			System.out.printf("%3d ", n);
			System.out.print(t2);
			System.out.print(t3);
			System.out.print(t5);

			System.out.println();
		}

		System.out.println("t2:" + triplette2.size() + " t3:" + triplette3.size() + " t5:" + triplette5.size());

		System.out.println("Triplette 2");
		for (Tripletta t : triplette2)
			System.out.println(t);

		System.out.println("Triplette 3");
		for (Tripletta t : triplette3)
			System.out.println(t);

		System.out.println("Triplette 5");
		for (Tripletta t : triplette5)
			System.out.println(t);

		/*
		 * provo tutte le combinazioni di triplette 2 3 5 e controllo le somme dei
		 * mod8=0, dei mod3=0, dei mod5=0
		 */
		int c = 1;
		for (Tripletta t2 : triplette2)
			for (Tripletta t3 : triplette3)
				for (Tripletta t5 : triplette5) {
					int tot8 = t2.m8 + t3.m8 + t5.m8;
					int tot3 = t2.m3 + t3.m3 + t5.m3;
					int tot5 = t2.m5 + t3.m5 + t5.m5;

					int m8 = tot8 % 8;
					int m3 = tot3 % 3;
					int m5 = tot5 % 5;

					Tripletta m = new Tripletta(0, m8, m3, m5);

					boolean ok = (m8 == 0) && (m3 == 0) && (m5 == 0);

					System.out.printf("%5d ) " + t2 + " " + t3 + " " + t5 + " ---> " + m + " " + ok + "\n", c++);
				}

	}

}
