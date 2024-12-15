package numbers.rsa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import java.util.List;

import org.junit.jupiter.api.Test;

import numbers.MException;
import numbers.rsa.alghoritms.RSAWheelAlghoritm;

class WheelTest {
	

	@Test
	void generateWheel2() throws MException {
		
		List<Long> wheel = RSAWheelAlghoritm.generateWheel(2);
		assertEquals(2, wheel.size());
		//System.out.println(wheel);
		
	}

	@Test
	void generateWheel3() throws MException {
		
		List<Long> wheel = RSAWheelAlghoritm.generateWheel(3);
		assertEquals(8, wheel.size());
		//System.out.println(wheel);
	}

	@Test
	void generateWheel() throws MException {
		
		List<Long> wheel = RSAWheelAlghoritm.generateWheel(5);
		assert(wheel.size()>0);
		//System.out.println(wheel);
	}

    //@Test
	void wheelNPrimes() throws MException {

		for(int n=2;n<=RSAWheelAlghoritm.NPRIMES_LIMIT;n++) {
			long startTime = System.nanoTime();
			RSAWheelAlghoritm a = new RSAWheelAlghoritm();
			BigInteger s = a.findFactor(RSANumbers.M67.getN(), n);	
			assertEquals(RSANumbers.M67A, s.toString());
			long endTime   = System.nanoTime();
			long totalTime = endTime - startTime;
			System.out.println("n:"+n+" ms:"+totalTime/1000/1000);
		}
	}

}
