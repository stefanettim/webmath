package groups;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CombinationsTest {

	private static Combinations combinations3_2;
	private static Combinations combinations4_2;
	private static Combinations combinations4_3;
	private static Combinations combinations6_3;
	private static Combinations bigCombinations;

	@BeforeAll
	public static void setup() throws Exception {
		combinations3_2 = new Combinations(3, 2);
		combinations4_2 = new Combinations(4, 2);
		combinations4_3 = new Combinations(4, 3);
		combinations6_3 = new Combinations(6, 3);
		bigCombinations = new Combinations(20, 5);
	}

	@Test
	void halfTest() throws Exception {
		Permutation p = combinations6_3.getCombination(4);
		assertEquals(p.set[0], 0);
	}

	@Test
	void smallTest() throws Exception {
		Permutation p = combinations3_2.getCombination(0);
		assertEquals(p.set[0], 0);
	}

	@Test
	void bigTest() throws Exception {
		HashSet<String> t = new HashSet<String>();
		long tot = bigCombinations.getSize();
		for (long i = 0; i < tot; i++) {
			Permutation p = bigCombinations.getCombination(i);
			t.add(p.toString());
		}
		System.out.println(bigCombinations.toString());
		assertEquals(bigCombinations.getSize(), t.size());
	}

	@Test
	void totalTest() throws Exception {
		long t = combinations3_2.getSize();
		assertEquals(3, t);

		t = combinations4_2.getSize();
		assertEquals(6, t);

		t = combinations4_3.getSize();
		assertEquals(4, t);
	}

	@Test
	void printTest() throws Exception {
		combinations3_2.print();
		combinations4_3.print();
		combinations4_2.print();
		combinations6_3.print();
		// bigCombinations.print();
	}
}
