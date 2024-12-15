package numbers.rsa.alghoritms;

import java.math.BigInteger;

import numbers.rsa.RSAAlghoritm;

public class RSAJumpSkipAlghoritm extends RSAAlghoritm {

	@Override
	public BigInteger findFactor(BigInteger n) {

		//long debug = 2;

		BigInteger a = BigInteger.ZERO;
		BigInteger sqrt = n.sqrt();
		
		for(int i : new int[]{2,3,5,7,11,13} ) {
			a = BigInteger.valueOf(i);
			if(n.mod(a) == BigInteger.ZERO) {		
				return a;
			}
		}

		int first=17;
		BigInteger l = BigInteger.valueOf(first);

		int c2  = first%2;
		int c3  = first%3;
		int c5  = first%5;
		int c7  = first%7;
		int c11 = first%11; 
		// int c13 = first%13;

		long jump = 0;
		//long maxjump = 0;
		boolean stop = true;
		
		while (l.compareTo(sqrt) <= 0) {
		//while (true) {
			
			if (n.mod(l) == BigInteger.ZERO) {
				a = l;
				break;
			}

			jump = 1;
			while (true) {

				stop = true;
				
				if (++c2 == 2) { c2 = 0; stop = false;	}

				if (++c3 == 3) { c3 = 0; stop = false;	}

				if (++c5 == 5) { c5 = 0; stop = false;	}
	
				if (++c7 == 7) { c7 = 0; stop = false;	}

				if (++c11==11) { c11 = 0; stop=false; }
				
				//if(++c13==13) { c13=0; stop=false; }

				if (stop) {
					break;
				}

				jump++;
				
				/*
				if(jump>maxjump) {
					maxjump=jump;
				}
				*/
			}

			l = l.add(BigInteger.valueOf(jump));

			/*
			if (debug == 10000000) {
				System.out.println(l);
				debug = 0;
			}
			debug++;
			 */
		}

		//System.out.println("maxjump:"+maxjump);
		
		return a;
	}

}
