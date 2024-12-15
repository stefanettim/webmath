package numbers.rsa;

import java.math.BigInteger;

import numbers.MException;

public abstract class RSAAlghoritm {
	
	public abstract BigInteger findFactor(BigInteger problem) throws MException;


}
