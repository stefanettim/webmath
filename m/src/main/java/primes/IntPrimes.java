package primes;

import java.io.Serializable;
import java.util.ArrayList;

import numbers.MException;
import numbers.results.FactorResult;
import numbers.results.FactorizationResult;

public class IntPrimes implements Serializable {

	private static final long serialVersionUID = 5272217718969079859L;
	
	public int limit = 100;
	public ArrayList<Integer> primes = new ArrayList<>();

	public boolean isPrime(long m) throws MException {
		if(m>Integer.MAX_VALUE) {
			throw new MException("too big, cannot cast to int");
		}
		return isPrime((int) m);
	}
	
	public boolean isPrime(int m) throws MException {

		if(m>limit) {
			throw new MException("too big, max "+limit);
		}
		
		return primes.contains(m);
	}

	public int getLast() {
		return primes.get(primes.size()-1);
	}

	public int getCount() {
		return primes.size();
	}

	public int getLimit() {
		return limit;
	}

	public ArrayList<Integer> getPrimes() {
		return primes;
	}
	
	public FactorizationResult factorize(int n) throws MException {
		
		if(n>limit) {
			throw new MException("too big, cannot factorize "+n);
		}
		
		FactorizationResult r = new FactorizationResult(n);
		
		
		if(isPrime(n)) {
			r.factorsCount++;
			r.maxPower=1;
			r.factors.add(new FactorResult(n, 1));
			return r;
		}
		
		int left = n;

		for(int d:primes) {
			FactorResult fr = null;
				
				while(left>1 && left%d==0) {
					if(fr==null) {
						fr=new FactorResult(d, 1);
					}
					else{
						fr.power++;
					}
					left=left/d;
					
				}
				
				if(fr!=null) {
					r.factorsCount++;
					r.factors.add(fr);
					if(fr.power>r.maxPower) {
						r.maxPower=fr.power;
					}					
				}
		}
		
		if(left>1) {
			throw new MException("algorithm error");
		}
		
		return r;
	}

	
}