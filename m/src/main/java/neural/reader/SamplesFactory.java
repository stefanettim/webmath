package neural.reader;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

import neural.ImageUtils;

public class SamplesFactory {

	public static int fromByteArray(byte[] bytes) {
		return ByteBuffer.wrap(bytes).getInt();
	}

	public static byte[] readMnistFile(String filename) throws IOException {
		File file = new File(filename);
		byte[] bytes = new byte[(int) file.length()]; // file.lenght gzip??
		DataInputStream dis = new DataInputStream(new FileInputStream(file));
		dis.readFully(bytes);
		dis.close();

		return bytes;
	}

	public static void loadMnistImages(Samples samples, String filename) throws IOException {

		byte[] bytes = readMnistFile(filename);

		samples.imagesMagic = fromByteArray(new byte[] { bytes[0], bytes[1], bytes[2], bytes[3] });
		samples.size = fromByteArray(new byte[] { bytes[4], bytes[5], bytes[6], bytes[7] });
		samples.rows = fromByteArray(new byte[] { bytes[9], bytes[9], bytes[10], bytes[11] });
		samples.columns = fromByteArray(new byte[] { bytes[12], bytes[13], bytes[14], bytes[15] });

		samples.images = new float[samples.size][28 * 28];

		int offset = 16;
		int image = 0;
		while (offset < bytes.length) {
			samples.images[image] = new float[28 * 28];
			for (int j = 0; j < 28 * 28; j++) {
				samples.images[image][j] = (float) (bytes[offset + j] & 0xFF) / 255f;
			}

			offset += 28 * 28;
			image++;
		}

	}

	public static void loadMnistLabels(Samples samples, String filename) throws IOException {

		byte[] bytes = readMnistFile(filename);

		samples.labelsMagic = fromByteArray(new byte[] { bytes[0], bytes[1], bytes[2], bytes[3] });
		samples.size = fromByteArray(new byte[] { bytes[4], bytes[5], bytes[6], bytes[7] });

		samples.labels = new int[samples.size];

		int offset = 8;
		int label = 0;
		for (int i = offset; i < bytes.length; i++) {
			samples.labels[label++] = Integer.valueOf(bytes[i]);
		}

	}

	public static Samples getTrainingSamples() throws FileNotFoundException, IOException {
		Samples s = new Samples("training");
		loadMnistLabels(s, "neural/MNIST/train-labels.idx1-ubyte");
		loadMnistImages(s, "neural/MNIST/train-images.idx3-ubyte");
		return s;
	}

	public static Samples getTestSamples() throws FileNotFoundException, IOException {
		Samples s = new Samples("test");
		loadMnistLabels(s, "neural/MNIST/t10k-labels.idx1-ubyte");
		loadMnistImages(s, "neural/MNIST/t10k-images.idx3-ubyte");
		return s;
	}

	public static void generateTraining(String dir, int limit) throws FileNotFoundException, IOException {
		Samples s = getTrainingSamples();
		generate(dir, s, limit);
	}

	public static void generateTest(String dir, int limit) throws FileNotFoundException, IOException {
		Samples s = getTestSamples();
		generate(dir, s, limit);
	}

	public static void generate(String dir, Samples s, int limit) throws FileNotFoundException, IOException {

		int items = s.size;

		for (int j = 0; j < items; j++) {
			int label = s.labels[j];
			String filename = String.format(s.prefix + "_%05d_%1d", j, label);
			if ((limit == 0) || (j < limit) || (j >= items - limit))
				ImageUtils.writeImage(s.images[j], "neural/" + dir, filename);
		}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {

		generateTraining("images", 5);
		generateTest("images", 5);

	}

	public static Samples getMarcoSamples() throws FileNotFoundException, IOException {
		Samples s = new Samples("marco");

		s.labels = new int[10];
		s.images = new float[10][28 * 28];
		s.size = 10;
		s.columns = 28;
		s.rows = 28;

		for (int i = 0; i < 10; i++) {
			s.labels[i] = i;
			String filename = "neural/marco/m" + i + ".JPG";
			s.images[i] = MnistImageUtils.readJpgImage(filename);
		}

		return s;
	}

}
