package numbers.rsa.alghoritms;

import java.math.BigInteger;

import numbers.rsa.RSAAlghoritm;

public class RSATrialAlghoritm extends RSAAlghoritm {

	@Override
	public BigInteger findFactor(BigInteger n) {

		if(n==BigInteger.ONE) {
			return n;
		}

		BigInteger a = n;
		BigInteger l = BigInteger.TWO;
		BigInteger sqrt = n.sqrt();
		
		//long debug=2;

		while(l.compareTo(sqrt)<=0) {
			
			if(n.mod(l)==BigInteger.ZERO) {
				a = l;
				break;
			}
			
			/*
			if(debug==100000) {
				System.out.println(l);
				debug=0;
			}
			debug++;
			*/
			
			l = l.add(BigInteger.ONE);
			
		}

		return a;
	}

}
