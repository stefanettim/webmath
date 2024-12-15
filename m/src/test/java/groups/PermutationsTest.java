package groups;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import math.MyMath;

class PermutationsTest {

	private static Permutation permutations4;

	@BeforeAll
	static void init() {
		permutations4 = new Permutation(4);
	}

	@Test
	void factorialTest() throws Exception {

		long f = 20;

		long n = MyMath.factorial(f);

		long r = n;
		for (long i = 1; i < f; i++)
			r = r / i;

		System.out.println(n + " r " + r);

	}

	@Test
	void factorial25Test() throws Exception {

		long f = 25;

		Assertions.assertThrows(Exception.class, () -> {
			long n = MyMath.factorial(f);
			System.out.println(n);
			fail();
		});

	}

	@Test
	void smallTest() throws Exception {
		Permutation p1 = new Permutation(1);
		Permutation p = p1.permute(0);
		assertEquals(p.set[0], 0);

		Permutation p2 = new Permutation(2);
		Permutation q = p2.permute(1);
		assertEquals(1, q.set[0]);
	}

	@Test
	void small3Test() throws Exception {
		/*
		 * 0 1 2 0 0 2 1 1 1 0 2 2 1 2 0 3 2 0 1 4 2 1 0 5
		 */
		Permutation p3 = new Permutation(3);
		Permutation p = p3.permute(4);
		Permutation q = new Permutation(new int[] { 2, 0, 1 });
		assertEquals(q, p);
	}

	@Test
	void firstTest() throws Exception {
		Permutation q = new Permutation(new int[] { 0, 1, 2, 3 });
		Permutation p = permutations4.permute(0);
		assertEquals(q, p);
	}

	@Test
	void lastTest() throws Exception {
		Permutation q = new Permutation(new int[] { 3, 2, 1, 0 });
		Permutation p = permutations4.permute(23);
		assertEquals(q, p);
	}

	@Test
	void equalsTest() {
		Permutation p = permutations4;
		Permutation q = new Permutation(new int[] { 0, 1, 2, 3 });
		Permutation r = new Permutation(new int[] { 8, 1, 2, 3 });

		assertEquals(p, q);
		assertNotEquals(p, r);
		assertNotEquals(q, r);

	}

	@Test
	void removeTest() {

		int[] s = { 8, 11, 34 };
		Permutation p = new Permutation(s);

		p = p.remove(1);

		assertEquals(8, p.set[0]);
		assertEquals(34, p.set[1]);

	}

	@Test
	void smallRemoveTest() {

		Permutation p = new Permutation(2);
		Permutation q = p.remove(0);
		assertEquals(1, q.set[0]);

	}

	@Test
	void printTest() throws Exception {

		Permutation f = permutations4.permute(0);
		System.out.println("0123 : " + f);

		Permutation p = new Permutation(new int[] { 3, 2, 1, 0 });
		System.out.println("3210 : " + p);

		Permutation r = permutations4.permute(11);
		System.out.println("1320 : " + r);

		for (int i = 0; i < 24; i++) {
			System.out.print(i);
			Permutation x = permutations4.permute(i);
			System.out.println("  " + x);
		}

	}

	@Test
	void permutationTest() throws Exception {

		Permutation r;

		r = permutations4.permute(0);
		assertEquals(new Permutation(new int[] { 0, 1, 2, 3 }), r);

		r = permutations4.permute(1);
		assertEquals(new Permutation(new int[] { 0, 1, 3, 2 }), r);

		r = permutations4.permute(11);
		assertEquals(new Permutation(new int[] { 1, 3, 2, 0 }), r);

		r = permutations4.permute(23);
		assertEquals(new Permutation(new int[] { 3, 2, 1, 0 }), r);

	}

	@Test
	void bigTest() throws Exception {
		Permutation ps = new Permutation(10);
		Permutation p = ps.permute(100000);
		System.out.println("big: " + p);
	}

	@Test
	void slowTest() throws Exception {
		Permutation ps = new Permutation(7);
		Permutation p = null;
		for (long l = 0; l < ps.getPermutations(); l++)
			p = ps.permute(l);
		System.out.println("prmutation : " + p.toString());
	}

	@Test
	void permuteTest() throws Exception {
		Permutation p = new Permutation(4);
		List<Permutation> l = Permutations.permute(p);
		for (Permutation x : l) {
			System.out.println(x);
		}
		assertEquals(24, l.size());
	}

}
