package neural.examples;

public class NeuralNand_1N {

	/*
	 * i1 O \ z1=w1*i1+w2*i2+b w1\ sigma(z) = 1/(1+e^-z) \ z O--- sigma ---
	 * threshold --- a /| w2/ | error=a-y(i1,i2) / b cost=1/2*(error)^2 i2 O
	 * 
	 * 
	 * d cost/ d w1 = 2 * ( sigma(z1) - y ) * i1 * sigmad1(z1)
	 * sigmad1(z)=e^-z/(1+e^-z)^2=sigma(z)*(1-sigma(z))
	 * 
	 */

	private float w1;
	private float w2;
	private float b;

	public NeuralNand_1N() {
		w1 = (float) Math.random();
		w2 = (float) Math.random();
		b = (float) Math.random();
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

	public float go(float i1, float i2) {
		float z = w1 * i1 + w2 * i2 + b;
		float a = sigmoid(z);

		if (a > 0.1)
			return 1;

		return 0;
	}

	public float train(float i1, float i2, float o) {
		float z = w1 * i1 + w2 * i2 + b;
		float a = go(i1, i2);

		float error = o - a;
		float cost = error * error / 2;

		// learn
		float sd = sigmoid_derivate(z);
		float lr = 1f;
		w1 += lr * 2 * i1 * error * sd;
		w2 += lr * 2 * i2 * error * sd;
		b += lr * 2 * error * sd;

		System.out.println("o " + o + " a " + a + " w1 " + w1 + " w2 " + w2 + " b " + b + " cost " + cost);
		return cost;

	}

	public static void main(String[] args) {

		NeuralNand_1N nn = new NeuralNand_1N();

		System.out.println("0 0 : " + nn.go(0, 0));
		System.out.println("1 0 : " + nn.go(1, 0));
		System.out.println("0 1 : " + nn.go(0, 1));
		System.out.println("1 1 : " + nn.go(1, 1));

		for (int i = 0; i < 1000; i++) {
			System.out.println("learn cycle " + i);
			float cost = 0;
			cost += nn.train(0, 0, 1);
			cost += nn.train(1, 0, 1);
			cost += nn.train(0, 1, 1);
			cost += nn.train(1, 1, 0);
			System.out.println("-------------------------");
			if (cost < 0.001)
				break;
		}

		System.out.println("0 0 : " + nn.go(0, 0));
		System.out.println("1 0 : " + nn.go(1, 0));
		System.out.println("0 1 : " + nn.go(0, 1));
		System.out.println("1 1 : " + nn.go(1, 1));

	}

}
