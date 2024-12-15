package neural.examples;

public class NeuralNand_2N_1N {

	/*
	 * b1_1 | w1_11 | i1 -------O z1_1 -- sigmoid -- a1_1 \ / \ w1_12\ / \w2_1 \ / \
	 * X b2 --- O z2 -- sigmoid -- a2 / \ / w1_21/ \ /w2_2 / \ / i2 -------O z1_2 --
	 * sigmoid -- a1_2 w1_21 | | b1_2
	 * 
	 * 
	 * 
	 */

	private float w1_11 = (float) Math.random();
	private float w1_12 = (float) Math.random();
	private float w1_21 = (float) Math.random();
	private float w1_22 = (float) Math.random();
	private float b1_1 = (float) Math.random();
	private float b1_2 = (float) Math.random();

	private float w2_1 = (float) Math.random();
	private float w2_2 = (float) Math.random();
	private float b2 = (float) Math.random();

	float z1_1;
	float a1_1;
	float z1_2;
	float a1_2;
	float z2;
	float a2;
	float r;

	public NeuralNand_2N_1N() {
	}

	public float sigmoid(float z) {
		float s = (float) (1 / (1 + Math.exp(-1 * z)));
		return s;
	}

	public float sigmoid_derivate(float z) {
		float s = sigmoid(z);
		float d = s * (1 - s);
		return d;
	}

	public float feedfarward(float i1, float i2) {
		z1_1 = w1_11 * i1 + w1_12 * i2 + b1_1;
		a1_1 = sigmoid(z1_1);

		z1_2 = w1_21 * i1 + w1_22 * i2 + b1_2;
		a1_2 = sigmoid(z1_2);

		z2 = w2_1 * a1_1 + w2_2 * a1_2 + b2;
		a2 = sigmoid(z2);

		if (a2 > 0.1)
			r = 1;
		else
			r = 0;

		return r;
	}

	public float learn(float i1, float i2, float o) {
		float r = feedfarward(i1, i2);

		float error = o - r;
		float cost = error * error;

		// backpropagation layer2

		float learn2 = 2f;
		float sd2 = sigmoid_derivate(z2);
		w2_1 += learn2 * a1_1 * error * sd2;
		w2_2 += learn2 * a1_2 * error * sd2;
		b2 += learn2 * error * sd2;

		// backpropagation layer1

		float learn1 = 1f;
		float sd1_1 = sigmoid_derivate(z1_1);
		float sd1_2 = sigmoid_derivate(z1_2);
		w1_11 += learn1 * w2_1 * error * i1 * sd2 * sd1_1;
		w1_21 += learn1 * w2_2 * error * i1 * sd2 * sd1_1;
		w1_12 += learn1 * w2_1 * error * i2 * sd2 * sd1_2;
		w1_22 += learn1 * w2_2 * error * i2 * sd2 * sd1_2;
		b1_1 += learn1 * w2_1 * error * 1 * sd2 * sd1_1;
		b1_2 += learn1 * w2_2 * error * 1 * sd2 * sd1_2;

		System.out.println(
				"o " + o + " r " + r + " a " + a2 + " w1 " + w2_1 + " w2 " + w2_2 + " b " + b2 + " cost " + cost);
		return cost;

	}

	public static void main(String[] args) {

		NeuralNand_2N_1N nn = new NeuralNand_2N_1N();
		float a = nn.feedfarward(0, 1);
		System.out.println(a);

		for (int i = 0; i < 1000; i++) {
			System.out.println("minibatch " + i);
			float cost = 0;
			cost += nn.learn(0, 0, 1);
			cost += nn.learn(1, 0, 1);
			cost += nn.learn(0, 1, 1);
			cost += nn.learn(1, 1, 0);
			System.out.println("-------------------------");
			if (cost < 0.001)
				break;
		}

		System.out.println("0 0 : " + nn.feedfarward(0, 0));
		System.out.println("1 0 : " + nn.feedfarward(1, 0));
		System.out.println("0 1 : " + nn.feedfarward(0, 1));
		System.out.println("1 1 : " + nn.feedfarward(1, 1));

	}

}
