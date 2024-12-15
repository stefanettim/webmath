package neural.reader;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

import neural.ImageUtils;
import neural.Machine;
import neural.NeuralMath;

public class ReadMachine extends Machine {

	private static final long serialVersionUID = 3268158854115273144L;

	protected static int[] hiddenLayers = new int[] { 320 };
	protected float learn = 0.1f;
	protected int epochs = 10;
	protected int batchSize = 1;
	protected int repetita = 1;
	protected boolean rotations = false;
	protected float maxRotation = 5f;

	/* fixed square image
	TRAINING GOOD:99,84
	FINAL TEST GOOD:98,03
	protected static int[] hiddenLayers = new int[] { 320 };
	protected float learn = 0.1f;
	protected int epochs = 10;
	protected int batchSize = 1;
	protected int repetita = 1;
	protected boolean rotations = false;
	protected float maxRotation = 5f;
	 */
	
	/*
	 * TRAINING GOOD:100,00 FINAL TEST GOOD:98,12 protected static int[]
	 * hiddenLayers = new int[]{320}; protected float learn=0.1f; protected int
	 * epochs=20; protected int batchSize=1; protected int repetita=1; protected
	 * boolean rotations=false; protected float maxRotation=5f;
	 */

	/*
	 * TRAINING GOOD:99,79 FINAL TEST GOOD:98,08
	 * 
	 * protected static int[] hiddenLayers = new int[]{280}; protected float
	 * learn=0.1f; protected int epochs=10; protected int batchSize=1; protected int
	 * repetita=1; protected boolean rotations=false; protected float
	 * maxRotation=5f;
	 */

	/*
	 * rotations TRAINING GOOD:99.10 FINAL TEST GOOD:98.29 protected int[]
	 * hiddenLayers = new int[]{280}; protected float learn=0.1f; protected int
	 * epochs=50; protected int batchSize=1; protected int repetita=1; protected
	 * boolean rotations=true; protected float maxRotation=10f;
	 */

	/*
	 * bestReadMachine.ser TRAINING GOOD:99,78 FINAL TEST GOOD:98,17 protected int[]
	 * hiddenLayers = new int[]{280}; protected float learn=0.1f; protected int
	 * epochs=10; protected int batchSize=1; protected int repetita=1; protected
	 * boolean rotations=false; protected float
	 * maxRotation=(float)(5f/360f*2f*Math.PI);
	 */

	public ReadMachine() throws FileNotFoundException, IOException {
		super(784, hiddenLayers, 10);
		randomizeWeights();
		setLearn(learn);
	}

	public void learn(int samplesLimit, String prefix) throws FileNotFoundException, IOException {
		Samples trainingSamples = SamplesFactory.getTrainingSamples();

		int print = 0;
		int samples = 0;
		

		for (int epoch = 0; epoch < epochs; epoch++) {
			
			long miniBatchNanoSeconds=0;
			long learnNanoSeconds=0;

			Integer[] shuffled = NeuralMath.shuffle(trainingSamples.size);
			float[][] outputs = new float[trainingSamples.size][10];  
			for(int i = 0; i < trainingSamples.size; i++) { 
				outputs[i]=NeuralMath.digitToArray(trainingSamples.labels[i]);
			}

			int batches = trainingSamples.size / batchSize;
			for (int batchNumber = 0; batchNumber < batches; batchNumber++) {
				startMiniBatch();

				// batch
				for (int batchSample = 0; batchSample < batchSize; batchSample++) {
					int progressive = batchNumber * batchSize + batchSample;
					int sample = shuffled[progressive];
					float[] output = outputs[sample] ;
							// NeuralMath.digitToArray(trainingSamples.labels[sample]);

					for (int rep = 0; rep < repetita; rep++) {
						float[] image = trainingSamples.getImageBytes(sample);

						if (rotations) {
							BufferedImage bi = ImageUtils.floatToImage(image);
							BufferedImage r = bi;
							float angle = 2 * (0.5f - (float) Math.random()) * maxRotation;
							r = ImageUtils.rotate(bi, angle);
							ImageChain ic = MnistImageUtils.mnistProcess(r);
							image = ic.mnist;
						}

						evaluate(image, output);
						samples++;
						if ((samplesLimit > 0) && (samples > samplesLimit))
							return;
					}

					// System.out.println("cost:"+singlecost);
					// System.out.println( "Label "+trl.labels[t]
					// +" Activations "+NeuralMath.stringVector( m.getLayers()[2].getActivations() )
					// +" Errors "+NeuralMath.stringVector( m.getLayers()[2].getErrors() ) );
					// System.out.println("e "+NeuralMath.stringVector( m.getLayers()[2].getErrors()
					// ));
					// m.getLayers()[2].print();
				}

				endMiniBatch();
				miniBatchNanoSeconds+=getMiniBastchNanoSeconds();
				long nano=learnMiniBatch();
				learnNanoSeconds+=nano;

				print += getMiniBatchSize();
				if (print >= 1000) {
					float pGood = test(100, trainingSamples, false);
					float epochProgress = 100f * (float) batchNumber / (float) batches;
					System.out.printf(
							"epoch:%02d epochProgress:%02.0f totalSamples:%07d lastMiniBatchCost:%02.5f bs:%02d good:%03.2f%% elapsed:%04d ms %04d ms\n",
							epoch + 1, epochProgress, samples, getMiniBatchCost(), batchSize, pGood, miniBatchNanoSeconds/1000/1000, learnNanoSeconds/1000/1000);
					print = 0;
					miniBatchNanoSeconds=0;
					learnNanoSeconds=0;
				}

			}
			// float learn=m.getLearn();
			// learn=learn/2;
			// m.setLearn(learn);

			// batchSize=batchSize*4;

			if (prefix != null) {
				save(prefix);
			}

		}

	}

	public void testFullTest() throws FileNotFoundException, IOException {
		Samples s = SamplesFactory.getTestSamples();
		float p = test(0, s, false);
		System.out.printf("FINAL TEST GOOD:%03.2f\n", p);
	}

	public void trainingFullTest() throws FileNotFoundException, IOException {
		Samples s = SamplesFactory.getTrainingSamples();
		float p = test(0, s, false);
		System.out.printf("TRAINING GOOD:%03.2f\n", p);
	}

	public float test(int limit, Samples s, boolean dumpErrors) throws FileNotFoundException, IOException {

		float tot = 0;
		float good = 0;
		if (limit == 0)
			limit = s.size;

		for (int i = 0; i < limit; i++) {
			float[] output = feedfarward(s.images[i]);
			int r = NeuralMath.activationsToInt(output);
			tot++;
			if (r == s.labels[i])
				good++;
			else if (dumpErrors) {
				String filename = String.format(s.prefix + "_%05d_O%1d_A%1d", i, s.labels[i], r);
				ImageUtils.writeImage(s.images[i], "neural/errors", filename);
				System.out.println("ERROR " + filename + " " + NeuralMath.stringVector(output));
			}

		}
		float pGood = 100f * good / tot;

		// System.out.println("tot:"+tot+" good:"+good+" p:"+String.format("%01.2f",p));
		return pGood;
	}

	public ReadResult process(float[] input) throws IOException {

		ReadResult rr = new ReadResult();

		float[] output = feedfarward(input);
		rr.setActivations(output);

		int r = NeuralMath.activationsToInt(output);
		rr.setResult(r);

		return rr;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

		ReadMachine rm = (ReadMachine) ReadMachine.load("lastRead");
		rm.trainingFullTest();
		rm.testFullTest();
	}

}
