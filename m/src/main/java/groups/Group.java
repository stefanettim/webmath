package groups;

import java.util.LinkedList;

public class Group implements Cloneable {

	protected String name;
	protected int order;
	protected LinkedList<Action> actions = new LinkedList<Action>();
	protected int[][] table;

	public Object clone() throws CloneNotSupportedException {
		Group x = new Group(order, name);

		for (Action a : actions) {
			Action b = (Action) a.clone();
			x.actions.add(b);
		}

		for (int i = 0; i < order; i++)
			for (int j = 0; j < order; j++) {
				x.table[i][j] = table[i][j];
			}

		return x;
	}

	public Group(int order, String name) {
		this.name = name;
		this.order = order;
		actions = new LinkedList<Action>();

		for (int i = 0; i < order; i++)
			actions.add(i, new Action("" + i));

		table = new int[order][order];

		table[0][0] = 0;

	}

	public Group(int order) {
		this(order, "group" + order);
	}

	public String actionsString() {
		String s = "{";
		for (int i = 0; i < order - 1; i++)
			s += actions.get(i).getName() + ",";
		s += actions.get(order - 1).getName() + "}";
		return s;
	}

	public String toString() {
		return "Group " + name;
	}

	public void printText() {
		System.out.println("=== Group " + name);
		int m = 0;
		for (int i = 0; i < order; i++) {

			if (actions.get(i).getName() == null || actions.get(i).getName().isEmpty())
				actions.get(i).setName("" + i);

			int s = actions.get(i).getName().length();
			if (s > m)
				m = s;
		}

		for (int i = 0; i < order; i++) {
			for (int j = 0; j < order; j++) {
				int a = table[i][j];
				Action action = new Action("" + a);
				if (a < actions.size())
					action = actions.get(a);
				System.out.printf("|%" + m + "s", action.getName());
			}
			System.out.println("|");
		}

		System.out.println();
	}

	public void rebuildTable() {

		int max = 0;
		for (int n = 0; n < order; n++)
			if (table[0][n] > max)
				max = table[0][n];

		int[] map = new int[max + 1];
		for (int n = 0; n < order; n++)
			map[table[0][n]] = n;

		for (int i = 0; i < order; i++)
			for (int j = 0; j < order; j++) {
				table[i][j] = map[table[i][j]];
			}

	}

	public String getName() {
		return name;
	}

	public int getOrder() {
		return order;
	}

	public LinkedList<Action> getActions() {
		return actions;
	}

	public int[][] getTable() {
		return table;
	}

}
