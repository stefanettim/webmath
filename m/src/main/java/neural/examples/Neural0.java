package neural.examples;

public class Neural0 {

	// learning rate
	float lr = 1f;
	// value of bias
	float bias = 1f;

	float[] weights = new float[] { (float) Math.random(), (float) Math.random(), (float) Math.random() };

	public float net(float input1, float input2) {
		float outputP = input1 * weights[0] + input2 * weights[1] + bias * weights[2];
		if (outputP > 0)
			outputP = 1;
		else
			outputP = 0;
		return outputP;
	}

	public void learn(float input1, float input2, float output) {
		float outputP = net(input1, input2);

		float error = output - outputP;
		weights[0] += error * input1 * lr;
		weights[1] += error * input2 * lr;
		weights[2] += error * bias * lr;

		System.out.println("learn " + weights[0] + " " + weights[2] + " " + weights[2]);

	}

	public static void main(String[] args) {

		Neural0 n = new Neural0();

		for (int i = 0; i < 3000; i++) {
			n.learn(1, 1, 0);
			n.learn(1, 0, 1);
			n.learn(0, 1, 1);
			n.learn(0, 0, 1);
		}

		System.out.println("1 1 : " + n.net(1, 1));
		System.out.println("1 0 : " + n.net(1, 0));
		System.out.println("0 1 : " + n.net(0, 1));
		System.out.println("0 0 : " + n.net(0, 0));

	}

}
