package groups;

import groups.generators.StatesGroup;
import groups.generators.StatesGroupsFactory;

public class Factory {

	public static Group getIdentityGroup() throws Exception {
		Group e = new Group(1, "Identity");
		e.actions.add(new Action("e"));
		e.table[0][0] = 0;
		return e;
	}

	public static Group getCyclicGroup(int order) throws Exception {
		if (order < 1)
			throw new Exception("order " + order + " invalid, must be >=1");

		Group g = new Group(order, "C" + order);

		g.actions.add(new Action("e"));
		for (int i = 1; i < order; i++) {
			if (i == 1)
				g.actions.add(i, new Action("r"));
			else
				g.actions.add(i, new Action("r" + i));
		}

		for (int i = 0; i < order; i++)
			for (int j = 0; j < order; j++) {
				int s = i + j;
				while (s >= order)
					s -= order;

				g.table[i][j] = s;
			}

		return g;
	}

	public static Group getGoodGroup() {
		Group g = new Group(2, "C2");
		g.actions.add(0, new Action("e"));
		g.actions.add(1, new Action("f"));
		g.table = new int[][] { { 0, 1 }, { 1, 0 } };
		return g;
	}

	public static Group getQ4Group() {
		Group g = new Group(8, "Q4");
		g.actions.add(0, new Action("1"));
		g.actions.add(1, new Action("i"));
		g.actions.add(2, new Action("j"));
		g.actions.add(3, new Action("k"));
		g.actions.add(4, new Action("-1"));
		g.actions.add(5, new Action("-i"));
		g.actions.add(6, new Action("-j"));
		g.actions.add(7, new Action("-k"));
		g.table = new int[][] { { 0, 1, 2, 3, 4, 5, 6, 7 }, { 1, 4, 7, 2, 5, 0, 3, 6 }, { 2, 3, 4, 5, 6, 7, 0, 1 },
				{ 3, 6, 1, 4, 7, 2, 5, 0 }, { 4, 5, 6, 7, 0, 1, 2, 3 }, { 5, 0, 3, 6, 1, 4, 7, 2 },
				{ 6, 7, 0, 1, 2, 3, 4, 5 }, { 7, 2, 5, 0, 3, 6, 1, 4 } };
		return g;
	}

	public static Group getBadIdentityGroup() {
		Group g = new Group(3, "bad");
		g.table = new int[][] { { 0, 1, 2 }, { 1, 0, 2 }, { 3, 0, 2 } };
		return g;
	}

	public static Group getBadInverseGroup() {
		Group g = new Group(2, "bad");
		g.table = new int[][] { { 0, 1 }, { 1, 1 } };
		return g;
	}
	/*
	 * public static Group badUnicityGroup() { Group g = goodGroup();
	 * g.table[1][1]=1; g.name="badUnicityGroup"; return g; }
	 */

	public static Group getBadClosureGroup() {
		Group g = new Group(2, "bad");
		g.table = new int[][] { { 0, 1 }, { 1, 2 } };
		return g;
	}

	public static Group getAlternativeC2Group() {
		Group g = new Group(2, "altC2");
		g.table = new int[][] { { 1, 5 }, { 5, 1 } };
		return g;
	}

	public static Group getAlternativeC4Group() {

		/*
		 * |0|1|2|3| |1|0|3|2| |2|3|1|0| |3|2|0|1|
		 */
		Group g = new Group(4, "mixedC4");
		g.table = new int[][] { { 0, 1, 2, 3 }, { 1, 0, 3, 2 }, { 2, 3, 1, 0 }, { 3, 2, 0, 1 } };
		return g;

	}

	public static Group getV4Group() throws Exception {
		StatesGroup sg = StatesGroupsFactory.getV4Group();
		Group g = StatesEngine.states2actions(sg);
		return g;
	}

	public static Group getS3Group() throws Exception {
		StatesGroup sg = StatesGroupsFactory.getS3Group();
		Group g = StatesEngine.states2actions(sg);
		return g;
	}

	public static Group getC2xC2xC2Group() throws Exception {
		StatesGroup sg = StatesGroupsFactory.getC2xC2xC2Group();
		Group g = StatesEngine.states2actions(sg);
		return g;
	}

	public static Group getSymmetricGroup(int s) throws Exception {
		StatesGroup sg = StatesGroupsFactory.getSymmetricGroup(s);
		Group g = StatesEngine.states2actions(sg);
		return g;
	}

	public static Group getAlternatingGroup(int s) throws Exception {
		StatesGroup sg = StatesGroupsFactory.getSymmetricGroup(s);
		Group g = StatesEngine.states2actions(sg);
		Group a = Engine.squared(g);
		a.name = "A" + s;
		return a;
	}
}
