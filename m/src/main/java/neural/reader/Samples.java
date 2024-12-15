package neural.reader;

import java.io.FileNotFoundException;
import java.io.IOException;

import neural.NeuralMath;

public class Samples {

	public int[] labels;
	public float[][] images;
	public int labelsMagic;
	public int imagesMagic;
	public int size;
	public int rows;
	public int columns;
	public String prefix;

	public Samples(String prefix) throws FileNotFoundException, IOException {
		super();
		this.prefix = prefix;
	}

	public float[] getImageBytes(int p) {
		return images[p];
	}

	public void printImages() throws IOException {
		System.out.println("Images : " + size);
		System.out.println("Rows : " + rows);
		System.out.println("Columns : " + columns);
		float[] i = getImageBytes(0);
		System.out.println("bytes 28x28 : " + NeuralMath.stringVector(i));
	}

}
