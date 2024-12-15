package numbers.rsa;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import numbers.MException;

class FermatTest {

	@BeforeAll
	static void setAlghoritm() {
		RSAAlghoritmsFactory.setDefaultRho();
	}
	
	@Test
	void fermat5Easy() throws MException {
		assert( RSASolver.testcase( RSANumbers.F5 ) ); 
	}

	@Test
	void fermat6Easy() throws MException {
		assert( RSASolver.testcase( RSANumbers.F6 ) ); 
	}

	//@Test
	// rho 800s
	void fermat7Hard() throws MException {
		assert( RSASolver.testcase( RSANumbers.F7 ) ); 
	}

	//@Test
	// rho 59s
	void fermat8Medium() throws MException {
		assert( RSASolver.testcase( RSANumbers.F8 ) ); 
	}

	@Test
	void fermat11Easy() throws MException {
		assert( RSASolver.testcase( RSANumbers.F11 ) ); 
	}

	@Test
	void fermat12Easy() throws MException {
		assert( RSASolver.testcase( RSANumbers.F12 ) ); 
	}

}
