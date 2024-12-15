package numbers.rsa.alghoritms;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import numbers.MException;
import numbers.rsa.RSAAlghoritm;

public class RSAWheelAlghoritm extends RSAAlghoritm {
	
	private final static int[] BASE_PRIMES = new int[] {2,3,5,7,11,13,17,19,23};
	public final static int NPRIMES_LIMIT = BASE_PRIMES.length;

	@Override
	public BigInteger findFactor(BigInteger problem) throws MException {
		return findFactor(problem, 7);
	}


	public BigInteger findFactor(BigInteger n, int nprimes) throws MException {
		
		if(n==BigInteger.ONE) {
			return n;
		}

		//long debug = 2;

		BigInteger a = n;
		BigInteger sqrt = n.sqrt();
		
		for(int i : BASE_PRIMES ) {
			BigInteger p = BigInteger.valueOf(i);
			if(n.mod(p) == BigInteger.ZERO) {		
				return p;
			}
		}
		
		List<Long> wheel = generateWheel(nprimes);
		int wheelsize=wheel.size();

		int w=1;
		long start=1+wheel.get(0);
		BigInteger l = BigInteger.valueOf(start);

		while (l.compareTo(sqrt) <= 0) {

			if (n.mod(l) == BigInteger.ZERO) {
				a = l;
				break;
			}
			
			l = l.add(BigInteger.valueOf(wheel.get(w)));

			/*
			if(debug==10000000) {
				System.out.println(l);
				debug=0;
			}
			debug++;
			 */
			
			if(++w==wheelsize) {
				w=0;
			}

		}
		

		return a;
	}

	public static List<Long> generateWheel(int nprimes) throws MException {
		
		List<Long> wheel = new ArrayList<Long>();
		
		if(nprimes>BASE_PRIMES.length) {
			throw new MException("wheel to big, max "+BASE_PRIMES.length);
		}
		
		int limit =1;
		for(int i=0;i<nprimes;i++) {
			limit=limit*BASE_PRIMES[i];
		}
		
		int[] base = new int[limit+2];
		for(int i=0;i<limit;i++) {
			base[i]=1;
		}
		
		for(int i=0;i<nprimes;i++) {
			for(int j=0;j<limit;j++) {
				int p = BASE_PRIMES[i];
				if(p*j>0 && p*j<limit) {
					base[p*j]=0;
				}
			}
		}
		
		int position=1;
		int step=0;
		
		while(position<limit+1) {
			
			step=1;
			while(base[position+step]==0 && position+step<limit+1) {
				step++;
			}
			wheel.add((long)step);
			position=position+step;
			
		}
				
		return wheel;
	}
}
