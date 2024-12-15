package numbers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import numbers.results.ChineseReminderTheoremResult;
import numbers.results.EulerTheoremResult;
import numbers.results.LinearCongruenceResult;
import numbers.results.PolynomialCongruenceResult;
import numbers.results.PowerResult;
import numbers.results.WilsonTheoremResult;
import primes.IntPrimes;
import primes.IntPrimesFactory;

class CongruencesTest {

	@Test
	void testLinearCongruenceC5E1() throws MException {
		LinearCongruenceResult r = Congruences.solveLinearCongruence(5, 3, 8);
		assert(r.solvable);
		assertEquals(1, r.incongruentSolutions.size());
		assertEquals(7l, r.incongruentSolutions.get(0));
	}

	@Test
	void testLinearCongruenceC5E3() throws MException {
		LinearCongruenceResult r = Congruences.solveLinearCongruence(21, 11, 3);
		assert(!r.solvable);
	}

	@Test
	void testLinearCongruenceC5E4() throws MException {
		LinearCongruenceResult r = Congruences.solveLinearCongruence(15, 9, 12);
		assert(r.solvable);
		assertEquals(3, r.incongruentSolutions.size());
		assert(r.incongruentSolutions.contains(3l));
		assert(r.incongruentSolutions.contains(7l));
		assert(r.incongruentSolutions.contains(11l));
	}

	@Test
	void testLinearCongruence() throws MException {
		LinearCongruenceResult r = Congruences.solveLinearCongruence(4, 12, 2);
		assert(r.solvable);
		assertEquals(2, r.incongruentSolutions.size());
		assert(r.incongruentSolutions.contains(0l));
		assert(r.incongruentSolutions.contains(1l));
	}

	@Test
	void testWilsonTheorem() throws MException, InterruptedException, ClassNotFoundException, IOException {
		
		IntPrimes ip = IntPrimesFactory.getCachedIntPrimes();
		
		WilsonTheoremResult r;

		 r=Congruences.solveWilsonTheorem(1,ip);
		 assert(!r.completed);

		 r=Congruences.solveWilsonTheorem(4,ip);
		 assert(!r.completed);

		 r=Congruences.solveWilsonTheorem(3,ip);
		 assert(r.completed);

		 r=Congruences.solveWilsonTheorem(16,ip);
		 assert(!r.completed);

		 r=Congruences.solveWilsonTheorem(19,ip);
		 assert(r.completed);
	}

	@Test
	void testEulerTheorem() throws MException, InterruptedException, ClassNotFoundException, IOException {
		
		IntPrimes ip = IntPrimesFactory.getCachedIntPrimes();

		EulerTheoremResult r;
		
		r = Congruences.solveEulerTheorem(2,6, ip);
		assert(!r.solvable);

		int a=3;
		int m=13;
		r = Congruences.solveEulerTheorem(a,m, ip);
		assert(r.solvable);
		assertEquals(m-1, r.relativePrimesResult.reducedResidueSystem.size());
		assertEquals(m-1, r.alternatedResidueSystem.size());
		assertEquals(m-1, r.pairing.size());
		for(Long pa : r.pairing) {
			assert(r.relativePrimesResult.reducedResidueSystem.contains(pa));
		}
		
	}
	
	@Test
	void testChineseReminderTheorem() throws MException, InterruptedException {
		Long[] a = new Long[]{1l,1l,1l};
		Long[] b = new Long[]{2l,3l,2l};
		Long[] m = new Long[]{3l,5l,7l};
		ChineseReminderTheoremResult r = Congruences.solveChineseReminderTheorem(a, b, m);
		/*
		System.out.println("c:"+Arrays.toString(r.c));
		System.out.println("M:"+r.product);
		System.out.println("n:"+Arrays.toString(r.n));
		System.out.println("i:"+Arrays.toString(r.i));
		System.out.println("x0:"+r.x0);
		System.out.println("y:"+r.y);
		*/
		assertEquals(23l, r.y);
	}

	@Test
	void testChineseReminderTheoremBadA() throws MException, InterruptedException {
		Long[] a = new Long[]{6l,1l,1l};
		Long[] b = new Long[]{2l,3l,2l};
		Long[] m = new Long[]{3l,5l,7l};
		ChineseReminderTheoremResult r = Congruences.solveChineseReminderTheorem(a, b, m);
		assert(!r.correct);
		//System.out.println(r.incorrectMessage);
	}

	@Test
	void testChineseReminderTheoremBadM() throws MException, InterruptedException {
		Long[] a = new Long[]{1l,1l,1l};
		Long[] b = new Long[]{2l,3l,2l};
		Long[] m = new Long[]{3l,5l,9l};
		ChineseReminderTheoremResult r = Congruences.solveChineseReminderTheorem(a, b, m);
		assert(!r.correct);
		//System.out.println(r.incorrectMessage);
	}

	@Test
	void testChineseReminderTheoremZeros() throws MException, InterruptedException {
		Long[] a = new Long[]{1l,0l,1l};
		Long[] b = new Long[]{2l,3l,2l};
		Long[] m = new Long[]{3l,5l,9l};
		ChineseReminderTheoremResult r = Congruences.solveChineseReminderTheorem(a, b, m);
		//System.out.println("r.l="+r.l);
		//System.out.println(r.incorrectMessage);
		assert(r.correct);
	}
	
	@Test
	void testPolynomialCongruenceT0() throws MException, ClassNotFoundException, IOException, InterruptedException {
		PolynomialCongruenceResult r = Congruences.solvePolynomialCongruence(new Long[] {1l,1l}, 3, IntPrimesFactory.getCachedIntPrimes());
		assertEquals(1, r.degree);
		assertEquals(1, r.incongruentSolutions.size());
		assertEquals(2l, r.incongruentSolutions.get(0));
	}

	@Test
	void testPolynomialCongruenceE1a() throws MException, ClassNotFoundException, IOException, InterruptedException {
		PolynomialCongruenceResult r = Congruences.solvePolynomialCongruence(new Long[] {1l,1l,1l}, 11, IntPrimesFactory.getCachedIntPrimes());
		assertEquals(0, r.incongruentSolutions.size());
	}
	@Test
	void testPolynomialCongruenceE1b() throws MException, ClassNotFoundException, IOException, InterruptedException {
		PolynomialCongruenceResult r = Congruences.solvePolynomialCongruence(new Long[] {1l,0l,1l,1l}, 13, IntPrimesFactory.getCachedIntPrimes());
		assertEquals(1, r.incongruentSolutions.size());
		assertEquals(7l, r.incongruentSolutions.get(0));
	}
	@Test
	void testPolynomialCongruenceE1c() throws MException, ClassNotFoundException, IOException, InterruptedException {
		PolynomialCongruenceResult r = Congruences.solvePolynomialCongruence(new Long[] {1l,1l,0l,0l,2l}, 7, IntPrimesFactory.getCachedIntPrimes());
		assertEquals(1, r.incongruentSolutions.size());
		assertEquals(4l, r.incongruentSolutions.get(0));
	}
	

	@Test
	void testModPower() throws MException {
		long r = Congruences.modPower(3l,4l,10l);
		assertEquals(1l, r);
	}

	@Test
	//https://www.youtube.com/watch?v=9Dwyvf9rJZI&t=131s
	void solvePowerForBase1() throws MException {
		PowerResult r = Congruences.solvePowerForBase(3,14,2,23);
		assert(r.getSolutions().contains(9l));
		assert(r.getSolutions().contains(14l));
		assertEquals(2,r.getSolutions().size());
	}

	@Test
	//https://www.youtube.com/watch?v=9Dwyvf9rJZI&t=131s
	void solvePowerForBase2() throws MException {
		PowerResult r = Congruences.solvePowerForBase(1,3,5,17);
		assert(r.getSolutions().contains(11l));
		assertEquals(1,r.getSolutions().size());
	}
	
	@Test
	//https://www.youtube.com/watch?v=9Dwyvf9rJZI&t=131s
	void solvePowerForExponent1() throws MException {
		PowerResult r = Congruences.solvePowerForExponent(1,13,5,23);
		assertEquals(0,r.getSolutions().size());
	}
	
	@Test
	//https://www.youtube.com/watch?v=9Dwyvf9rJZI&t=131s
	void solvePowerForExponent2() throws MException {
		PowerResult r = Congruences.solvePowerForExponent(1,5,17,19);
		assert(r.getSolutions().contains(4l));
		assert(r.getSolutions().contains(13l));
		assertEquals(2,r.getSolutions().size());
	}
	
}
