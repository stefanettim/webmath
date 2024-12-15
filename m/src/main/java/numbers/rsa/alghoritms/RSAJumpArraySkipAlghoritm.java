package numbers.rsa.alghoritms;

import java.math.BigInteger;

import numbers.rsa.RSAAlghoritm;

public class RSAJumpArraySkipAlghoritm extends RSAAlghoritm {

	@Override
	public BigInteger findFactor(BigInteger n) {

		long debug=2;

		BigInteger a = BigInteger.ZERO;
		BigInteger sqrt = n.sqrt();
		BigInteger l = BigInteger.TWO;
		
		int[] primes = new int[] {2,3,5,7,11,13,17,19};
		int[] partial = new int[] {0,2,2,2,2,2,2,2};
		int primesLimit=8;
				
		for(int i : primes ) {
			a = BigInteger.valueOf(i);
			if(n.mod(a) == BigInteger.ZERO) {		
				return a;
			}
		}

		long jump=0;
		boolean stop=true;
		
		while(l.compareTo(sqrt)<=0) {
			
			if(n.mod(l)==BigInteger.ZERO) {
				a = l;
				break;
			}
			
			jump=1;			
			while(true) {

				stop=true;
				for(int i=0;i<primesLimit;i++) {
					if(++partial[i]==primes[i]) {
						partial[i]=0;
						stop=false;
					}
				}
				
				if(stop) {
					break;
				}
				
				jump++;
			}			
			
			//System.out.println(l+" jump:"+jump);
			l = l.add(BigInteger.valueOf(jump));
			
			if(debug==10000000) {
				System.out.println(l);
				debug=0;
			}
			debug++;

		}
		
		return a;
	}

}
