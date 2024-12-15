package euler;

import java.math.BigInteger;

import numbers.MException;
import numbers.big.BigFactorResult;
import numbers.big.BigFactorizationResult;
import numbers.big.BigPrimes;
import numbers.big.BigPrimesFactory;

public class E3 {
	
	public static long N = 
			//13195l
			600851475143l
			;
	
	public static void main(String[] a) throws MException {
		long s=(long) Math.sqrt(N)+1l;
		
		BigPrimes p = BigPrimesFactory.generate(s);
		
		BigFactorizationResult r =  p.factorize(BigInteger.valueOf(N));
		
		BigInteger max = BigInteger.ZERO;
		
		for(BigFactorResult b:r.factors) {
			BigInteger i = b.factor;
			if(i.compareTo(max)>0) {
				max=i;
			}
		}
		
		System.out.println("max factor: "+max);
	}

}
