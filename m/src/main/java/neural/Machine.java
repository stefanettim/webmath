package neural;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Machine implements java.io.Serializable {

	/*
	 * layers input|------|-------|-----| | hidden |------|-------|
	 * i1|.....l1......l2.....o
	 * 
	 * inputSize=2 hiddenLayersSize={3,3} outputSize=1 hiddenLayers=2 layers { 2 2x3
	 * 3x3 3x1 }
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = -8557314354379988365L;
	protected int inputSize;
	protected int outputSize;
	protected Layer[] layers;

	protected float learn = 1f;
	protected float outputThreshold = 0f;
	protected int miniBatchSize = 1;

	protected float miniBatchCost = 0;
	protected long miniBatchStart;
	protected long miniBatchEnd;
	protected long learnStart;
	protected long learnEnd;

	// setup empty layers
	public Machine(int inputSize, int[] hiddenLayersSize, int outputSize) {
		super();

		int hiddenLayers = hiddenLayersSize.length;

		// input
		this.inputSize = inputSize;
		Layer inputLayer = new Layer(0, inputSize);

		// output
		this.outputSize = outputSize;
		int lastInputSize;
		if (hiddenLayers > 0)
			lastInputSize = hiddenLayersSize[hiddenLayers - 1];
		else
			lastInputSize = inputSize;
		Layer outputLayer = new Layer(lastInputSize, outputSize);

		// layers
		layers = new Layer[1 + hiddenLayers + 1];

		layers[0] = inputLayer;

		int prev = inputSize;
		for (int l = 0; l < hiddenLayers; l++) {
			int layerSize = hiddenLayersSize[l];
			Layer layer = new Layer(prev, layerSize);
			layers[l + 1] = layer;
			prev = layerSize;
		}

		layers[1 + hiddenLayers] = outputLayer;

	}

	public void printLayers() {
		System.out.println("==============================================");
		for (int l = 0; l < layers.length; l++) {
			layers[l].print();
		}
	}

	public void printActivations() {
		System.out.println("activations: " + NeuralMath.stringVector(layers[layers.length - 1].batchActivations));
	}

	public void randomizeWeights() {
		for (int l = 1; l < layers.length; l++)
			layers[l].randomizeWeights();
	}

	public float[] feedfarward(float[] inputs) {

		if (inputs == null) {
			return null;
		}

		Layer inputLayer = layers[0];
		inputLayer.activations = inputs;
		for (int j = 0; j < inputSize; j++) {
			inputLayer.batchActivations[j] += inputs[j];
		}

		float[] layerInputs = inputs;

		Layer layer = null;
		for (int l = 1; l < layers.length; l++) {
			layer = layers[l];
			float[] output = layer.feed(layerInputs);
			layerInputs = output;
		}

		float[] outputs = new float[layerInputs.length];

		// last layer trigger
		if (outputThreshold > 0) {
			for (int o = 0; o < outputs.length; o++) {
				if (layerInputs[o] > outputThreshold) {
					outputs[o] = 1;
				} else {
					outputs[o] = 0;
				}
			}
		} else {
			outputs = layerInputs;
		}

		return outputs;
	}

	public Layer getLastLayer() {
		Layer lastLayer = layers[layers.length - 1];
		return lastLayer;
	}

	public void startMiniBatch() {
		miniBatchStart=System.nanoTime();
		miniBatchSize = 0;
		miniBatchCost = 0;
		for (Layer layer : layers) {
			layer.startMiniBatch();
		}
	}

	public void endMiniBatch() {
		for (Layer layer : layers) {
			layer.endMiniBatch(miniBatchSize);
		}
		
		miniBatchEnd=System.nanoTime();
	}
	
	public long getMiniBastchNanoSeconds() {
		return miniBatchEnd-miniBatchStart;
	}

	public float evaluate(float[] inputs, float[] outputs) {

		float[] activations = feedfarward(inputs);
		float cost = 0;

		Layer lastLayer = getLastLayer();

		float lastError = 0;
		for (int o = 0; o < outputs.length; o++) {
			lastError = outputs[o] - activations[o];
			lastLayer.errors[o] = lastError;
			lastLayer.batchErrors[o] += lastError;
			cost += lastError * lastError;
		}

		// cost = cost/((float)outputs.length);

		miniBatchCost += cost;
		miniBatchSize++;

		return cost;

	}

	public long learnMiniBatch() {
		
		long start = System.nanoTime();

		for (int l = layers.length - 1; l > 0; l--) {
			Layer layer = layers[l];
			Layer prevLayer = layers[l - 1];
			layer.backpropagate(learn, prevLayer);
		}

		long end = System.nanoTime();
		
		return end-start;
	}

	// 1 size batch
	public float learnStochasticGradientDescent(float[] inputs, float[] outputs) {

		startMiniBatch();

		float cost = evaluate(inputs, outputs);

		endMiniBatch();

		learnMiniBatch();

		return cost;
	}

	public float getLearn() {
		return learn;
	}

	public void setLearn(float learn) {
		this.learn = learn;
	}

	public float getThreshold() {
		return outputThreshold;
	}

	public int getInputSize() {
		return inputSize;
	}

	public int getOutputSize() {
		return outputSize;
	}

	public Layer[] getLayers() {
		return layers;
	}

	public int getMiniBatchSize() {
		return miniBatchSize;
	}

	public float getMiniBatchCost() {
		return miniBatchCost;
	}

	public float getOutputThreshold() {
		return outputThreshold;
	}

	public void setOutputThreshold(float outputThreshold) {
		this.outputThreshold = outputThreshold;
	}

	public void save(String prefix) throws IOException {
		FileOutputStream fileOut = new FileOutputStream("neural/serialize/" + prefix + "Machine.ser");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(this);
		out.close();
		fileOut.close();

	}

	public static Machine load(String prefix) throws IOException, ClassNotFoundException {
		FileInputStream fileIn = new FileInputStream("neural/serialize/" + prefix + "Machine.ser");
		ObjectInputStream in = new ObjectInputStream(fileIn);
		Machine machine = (Machine) in.readObject();
		in.close();
		fileIn.close();
		return machine;
	}

	public Machine loadClassResource(String prefix) throws IOException, ClassNotFoundException {
		InputStream is = getClass().getClassLoader().getResourceAsStream(prefix + "Machine.ser");
		ObjectInputStream in = new ObjectInputStream(is);
		Machine machine = (Machine) in.readObject();
		in.close();
		is.close();
		return machine;
	}

}
