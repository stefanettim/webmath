package groups.generators;

import java.util.List;

import groups.Permutation;
import groups.Permutations;

public class StatesGroupsFactory {

	public static StatesGroup getC4Group() throws Exception {
		StatesGroup c4 = new StatesGroup(4, "C4");
		c4.getGenerators().add(new FullCycleAction());
		c4.generate();
		return c4;
	}

	public static StatesGroup getC2Group() throws Exception {
		StatesGroup c2 = new StatesGroup(2, "C2");
		c2.getGenerators().add(new FlipAction(0, "f"));
		c2.generate();
		return c2;
	}

	public static StatesGroup getV4Group() throws Exception {
		StatesGroup v4 = new StatesGroup(4, "V4");
		v4.getGenerators().add(new FlipAction(0, "h"));
		v4.getGenerators().add(new FlipAction(2, "v"));
		v4.generate();
		return v4;
	}

	public static StatesGroup getS3Group() throws Exception {
		StatesGroup s3 = new StatesGroup(3, "S3");
		s3.getGenerators().add(new FullCycleAction());
		s3.getGenerators().add(new FlipAction(0, "f"));
		s3.generate();
		return s3;
	}

	public static StatesGroup getC2xC2xC2Group() throws Exception {
		StatesGroup c2c2c2 = new StatesGroup(6, "C2xC2xC2");
		c2c2c2.getGenerators().add(new FlipAction(0, "i"));
		c2c2c2.getGenerators().add(new FlipAction(2, "j"));
		c2c2c2.getGenerators().add(new FlipAction(4, "k"));
		c2c2c2.generate();
		return c2c2c2;
	}

	public static StatesGroup getSymmetricGroup(int s) throws Exception {
		StatesGroup g = new StatesGroup(s, "Symmetric" + s);
		g.getStates().clear();

		Permutation p = new Permutation(s);
		List<Permutation> l = Permutations.permute(p);

		for (Permutation x : l) {
			State st = new State(s);
			for (int i = 0; i < s; i++)
				st.state[i] = (char) (x.set[i] + '0');
			String key = st.toString();
			st.getActions().add(new PermutationAction(key, x.set));
			g.getStates().put(key, st);
		}

		return g;
	}

}
