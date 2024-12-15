package numbers.rsa;

import org.junit.jupiter.api.Test;

import numbers.MException;

class RSANumbersTest {
	
	@Test
	void testFactorization() throws MException {
		assert( RSANumbers.F5.test() );
		assert( RSANumbers.F6.test() );
		assert( RSANumbers.F7.test() );
		assert( RSANumbers.F8.test() );
		assert( RSANumbers.F11.test() );
		assert( RSANumbers.F12.test() );
		assert( RSANumbers.M67.test() );
		assert( RSANumbers.RSA100.test() );
	}


}
