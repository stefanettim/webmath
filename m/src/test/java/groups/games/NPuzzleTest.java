package groups.games;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import groups.Group;
import groups.StatesEngine;
import groups.games.npuzzle.NPuzzleAction;
import groups.games.npuzzle.NPuzzleState;
import groups.generators.State;
import groups.generators.StatesGroup;

class NPuzzleTest {

	@Test
	void printTest() {
		NPuzzleState p = new NPuzzleState(4, 4);
		p.reset();
		p.print();
	}

	@Test
	void moveUpTest() throws CloneNotSupportedException {
		NPuzzleState s = new NPuzzleState(4, 4);
		s.reset();

		s.moveUp();
		s.print();
		assertEquals(3, s.getMblank());
		assertEquals(2, s.getNblank());

		s.moveUp();
		s.print();
		assertEquals(3, s.getMblank());
		assertEquals(1, s.getNblank());

		s.moveUp();
		s.print();
		assertEquals(3, s.getMblank());
		assertEquals(0, s.getNblank());

		s.moveUp();
		s.print();
		assertEquals(3, s.getMblank());
		assertEquals(3, s.getNblank());
	}

	@Test
	void moveDownTest() throws CloneNotSupportedException {
		NPuzzleState s = new NPuzzleState(4, 4);
		s.reset();

		s.moveDown();
		s.print();
		assertEquals(3, s.getMblank());
		assertEquals(0, s.getNblank());

		s.moveDown();
		s.moveDown();
		s.moveDown();
		s.print();
		assertEquals(3, s.getMblank());
		assertEquals(3, s.getNblank());
	}

	@Test
	void group3PuzzleTest() throws Exception {
		NPuzzleState s = new NPuzzleState(2, 2);
		s.reset();
		s.print();

		StatesGroup p3 = new StatesGroup(s, "4Puzzle");
		p3.getGenerators().add(new NPuzzleAction('U', "U"));
		p3.getGenerators().add(new NPuzzleAction('L', "L"));
		p3.generate();

		for (State x : p3.getStates().values()) {
			NPuzzleState nps = (NPuzzleState) x;
			nps.print();
		}

		int n = p3.getStates().size();
		System.out.println("4Puzzle size " + n);

		Group g = StatesEngine.states2actions(p3);

		g.printText();
	}

	// @Test
	void group8PuzzleTest() throws Exception {
		NPuzzleState s = new NPuzzleState(3, 3);
		s.reset();
		s.print();

		StatesGroup p = new StatesGroup(s, "8Puzzle");
		p.getGenerators().add(new NPuzzleAction('U', "U"));
		p.getGenerators().add(new NPuzzleAction('L', "L"));
		p.generate();

		int n = p.getStates().size();
		System.out.println("8Puzzle size " + n);
	}
}
