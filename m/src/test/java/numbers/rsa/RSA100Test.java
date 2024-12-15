package numbers.rsa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

import numbers.MException;

class RSA100Test {
	

	@Test
	void RSA100MultiplyTest() throws MException {
		BigInteger a = new BigInteger(RSANumbers.RSA100A);
		BigInteger b = new BigInteger(RSANumbers.RSA100B);
		BigInteger n = a.multiply(b);
		assertEquals(RSANumbers.RSA100N, n.toString());
	}
	
	//@Test
	void RSA100Testcase() throws MException {
		BigInteger s = RSAAlghoritmsFactory.getDefaultAlghortirm().findFactor(RSANumbers.RSA100.getN());
		assertEquals(RSANumbers.RSA100A, s.toString());
	}	

}
