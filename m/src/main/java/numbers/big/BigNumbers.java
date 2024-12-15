package numbers.big;

import java.io.IOException;
import java.math.BigInteger;

import numbers.MException;
import numbers.results.BigFermatResult;

public class BigNumbers {

	static BigInteger myPower(BigInteger base, BigInteger exp) throws MException  {

		if(exp.longValue()<0l) {
			throw new MException("exponent must be positive");
		}
		
		BigInteger r=BigInteger.ONE;
		BigInteger l=BigInteger.ZERO;
		while(l.compareTo(exp)<0) {
			r = r.multiply(base);
			l = l.add(BigInteger.ONE);
		}
		
        return r;
	}

	public static BigInteger generateFermatNumber(int n) throws MException {
		BigInteger r = BigInteger.TWO.pow(n);
		
		if(r.compareTo(BigInteger.valueOf(Integer.MAX_VALUE))>0) {
			throw new MException("too big "+r);
		}
		
		r = BigInteger.TWO.pow(r.intValue());
		r = r.add(BigInteger.ONE);
		return r;
	}

	public static BigFermatResult analyzeFermatNumber(int n, BigPrimes bigPrimes) throws MException, ClassNotFoundException, IOException {
		
		BigFermatResult r = new BigFermatResult();
		r.setOrder(BigInteger.valueOf(n));
		
		BigInteger f = generateFermatNumber(n);
		r.setFermatNumber(f);
		
		
		
		r.setPrime(true);
		for(BigInteger p:bigPrimes.primes) {
			
			if(p.compareTo(f)>=0) {
				break;
			}
			
			if(f.mod(p).equals(BigInteger.ZERO)) {
				r.setPrime(false);
				r.getDivisors().add(p);
				r.setDivisor(p);
				break;
			}
		}
		
		r.setVerified(true);
		
		BigInteger sqrt = f.sqrt();
		if(r.getPrime() && sqrt.compareTo(bigPrimes.limit)>0) {
			r.setVerified(false);
		}

		return r;
	}
}
