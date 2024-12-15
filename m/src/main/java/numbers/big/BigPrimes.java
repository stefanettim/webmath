package numbers.big;

import java.math.BigInteger;
import java.util.ArrayList;

import numbers.MException;

public class BigPrimes implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -195662599065752008L;
	
	public int lotSize = 1*1000*1000;
	public BigInteger maxPrime = BigInteger.ZERO;
	public BigInteger limit = BigInteger.valueOf(1*1000*1000);

	public ArrayList<BigInteger> primes=new ArrayList<>();

	protected boolean[] lot = new boolean[lotSize];

	public boolean isPrime(long l) throws MException {
		BigInteger lb = BigInteger.valueOf(l);
		return isPrime(lb);
	}

	public boolean isPrime(BigInteger lb) throws MException {
		if(lb.compareTo(limit)>0) {
			throw new MException("input is over limit");
		}
		return primes.contains(lb);
	}

	protected void clearLot() {
		for(int i=0;i<lotSize;i++) {
			lot[i]=true;
		}
	}

	public BigInteger last() {
		return primes.get(primes.size()-1);
	}
	
	public BigFactorizationResult factorize(BigInteger n) throws MException {
		
		BigFactorizationResult r = new BigFactorizationResult(n);
		
		//	if(isPrime(n)) {
		//		r.factors.add(new BigFactorResult(n, 1));
		//	}
		
		BigInteger left = n;

		for(BigInteger d:primes) {
			BigFactorResult fr = null;
							
				while(left.compareTo(BigInteger.ONE)>0 && left.remainder(d).equals(BigInteger.ZERO)) {
					
					if(fr==null) {
						fr=new BigFactorResult(d, 1);
					}
					else{
						fr.power++;
					}
					left=left.divide(d);
					
				}
				
				if(fr!=null) {
					r.factorsCount++;
					r.factors.add(fr);
					if(fr.power>r.maxPower) {
						r.maxPower=fr.power;
					}					
				}
		}
		
		if(left.compareTo(BigInteger.ONE)>0) {
			throw new MException("algorithm error");
		}
		
		return r;
	}

}
