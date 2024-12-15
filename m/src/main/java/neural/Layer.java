package neural;

public class Layer implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1063592422501922634L;
	protected int prev;
	protected int next;

	protected float[][] weights;
	protected float[] bias;

	protected float[] activations;
	protected float[] errors;

	protected float[] batchActivations;
	protected float[] batchErrors;

	public Layer(int prev, int next) {
		this.prev = prev;
		this.next = next;
		weights = new float[prev][next];
		bias = new float[next];
		activations = new float[next];
		errors = new float[next];
		batchActivations = new float[next];
		batchErrors = new float[next];
	}

	public void print() {
		System.out.println("layer " + prev + " x " + next);
		if (prev > 0) {
			NeuralMath.printMatrixVector(weights, bias);
		}
		System.out.println("activations: " + NeuralMath.stringVector(batchActivations));
		if (prev > 0) {
			System.out.println("errors: " + NeuralMath.stringVector(batchErrors));
		}
	}

	public void randomizeWeights() {
		for (int k = 0; k < next; k++) {
			for (int j = 0; j < prev; j++)
				weights[j][k] = (float) Math.random() / prev / next * ((float) Math.random() - 0.5f);

			bias[k] = (float) Math.random() / next * ((float) Math.random() - 0.5f);
		}

	}

	public void startMiniBatch() {
		for (int k = 0; k < next; k++) {
			batchActivations[k] = 0;
			batchErrors[k] = 0;
		}
	}

	public void endMiniBatch(int miniBatches) {
		for (int k = 0; k < next; k++) {
			batchActivations[k] = batchActivations[k] / (float) miniBatches;
			batchErrors[k] = batchErrors[k] / (float) miniBatches;
		}
	}

	public float[] feed(float[] inputs) {

		float weighted = 0;
		for (int k = 0; k < next; k++) {
			weighted = 0;
			for (int j = 0; j < prev; j++)
				weighted += weights[j][k] * inputs[j];

			weighted += bias[k];

			activations[k] = NeuralMath.sigmoid(weighted);
			batchActivations[k] += activations[k];

		}

		return activations;
	}

	public void backpropagate(float learn, Layer prevLayer) {

		float lastError;
		
		for (int j = 0; j < prev; j++) {
			prevLayer.errors[j] = 0;
		}

		float sigmoid_d;
		for (int k = 0; k < next; k++) {
			
			sigmoid_d = batchActivations[k] * (1 - batchActivations[k]);

			for (int j = 0; j < prev; j++) {
				weights[j][k] += learn * batchErrors[k] * sigmoid_d * prevLayer.batchActivations[j];
				lastError = weights[j][k] * batchErrors[k];
				prevLayer.errors[j] += lastError;
				prevLayer.batchErrors[j] += lastError;
			}

			bias[k] += learn * batchErrors[k] * sigmoid_d;

		}

	}

	public int getPrev() {
		return prev;
	}

	public int getNext() {
		return next;
	}

	public float[][] getWeights() {
		return weights;
	}

	public float[] getBias() {
		return bias;
	}

	public float[] getActivations() {
		return activations;
	}

	public float[] getErrors() {
		return errors;
	}

}
