package numbers.rsa.alghoritms;

import java.math.BigInteger;

import numbers.rsa.RSAAlghoritm;

public class RSASkipAlghoritm extends RSAAlghoritm {

	@Override
	public BigInteger findFactor(BigInteger n) {

		BigInteger a = BigInteger.ZERO;
		BigInteger l = BigInteger.TWO;
		BigInteger sqrt = n.sqrt();
		
		long debug=2;
		
		int c2 = 0;
		int c3 = 0;
		int c5 = 0;
		int c7 = 0;
		
		while(l.compareTo(sqrt)<=0) {

		
			if(c2==2) {
				c2=0;
			} 
			else if(c3==3) {
				c3=0;
			}
			else if(c5==5) {
				c5=0;
			} 
			else if(c7==7) {
				c7=0;
			} 
			else 
			{
			
				if(n.mod(l)==BigInteger.ZERO) {
					a = l;
					break;
				}
				
			}
			
			c2++;
			c3++;
			c5++;
			c7++;
			
			if(debug==10000000) {
				System.out.println(l);
				debug=0;
			}
			debug++;
			
			l = l.add(BigInteger.ONE);
		}
		
		return a;
	}

}
