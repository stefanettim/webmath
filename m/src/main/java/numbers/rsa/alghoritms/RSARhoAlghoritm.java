package numbers.rsa.alghoritms;

import java.math.BigInteger;

import numbers.MException;
import numbers.rsa.RSAAlghoritm;

public class RSARhoAlghoritm extends RSAAlghoritm {

	
	/*
	https://en.wikipedia.org/wiki/Pollard%27s_rho_algorithm

    x ← 2 // starting value
    y ← x
    d ← 1

    while d = 1:
        x ← g(x)
        y ← g(g(y))
        d ← gcd(|x - y|, n)

    if d = n: 
        return failure
    else:
        return d
        
	 */
	
	@Override
	public BigInteger findFactor(BigInteger n) throws MException {
		
		//long debug = 0;

		BigInteger start = BigInteger.TWO;
		BigInteger x;
		BigInteger y;
		BigInteger d = null;
		BigInteger delta;
		
		while(start.compareTo(new BigInteger("10"))<0) {

			x=start;
			y = x;
			d = BigInteger.ONE;
			delta = BigInteger.ZERO;
			
			while(d.equals(BigInteger.ONE)) {
				
				x = x.multiply(x).add(BigInteger.ONE).mod(n);
				y = y.multiply(y).add(BigInteger.ONE).mod(n);
				y = y.multiply(y).add(BigInteger.ONE).mod(n);
				delta = y.subtract(x).abs();
				d = n.gcd(delta);		
				
				/*
				if (debug == 1000000) {
					System.out.println(delta);
					debug = 0;
				}
				debug++;
				*/
			}			
			
			if(!d.equals(BigInteger.ONE)) {
				break;
			}
			
			start = start.add(BigInteger.ONE);
			System.out.println("new start : "+start);
		}
		
		if(d==n) {
			throw new MException("solution not found, try a different starting point");
		}
		
		return d;
	}

}
