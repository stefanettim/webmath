package neural;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NeuralMath {

	public static float sigmoid(float z) {
		float s = (float) (1 / (1 + Math.exp(-1 * z)));
		return s;
	}

	public static float sigmoid_derivate(float z) {
		float s = sigmoid(z);
		float d = s * (1 - s);
		return d;
	}

	public static void printMatrixVector(float[][] matrix, float[] vector) {
		int a = matrix.length;
		int b = matrix[0].length;
		for (int n = 0; n < b; n++) {
			if (n == 0)
				System.out.print(" /");
			else if (n < b - 1)
				System.out.print(" |");
			else
				System.out.print(" \\");

			for (int p = 0; p < a; p++) {
				System.out.printf(" %1.4f ", matrix[p][n]);
			}

			if (n == 0)
				System.out.print("\\");
			else if (n < a - 1)
				System.out.print("|");
			else
				System.out.print("/");

			System.out.printf(" | %1.4f |", vector[n]);

			System.out.println();
		}

	}

	public static void printVector(float[] vector) {
		int a = vector.length;
		for (int n = 0; n < a; n++) {
			System.out.printf(" | %1.4f |", vector[n]);
			System.out.println();
		}

	}

	public static String stringVector(float[] vector) {
		int a = vector.length;
		String s = "< ";
		for (int n = 0; n < a; n++) {
			s += String.format("%+1.3f ", vector[n]);
		}
		s += ">";

		return s;

	}

	public static String stringVector(Integer[] vector) {
		int a = vector.length;
		String s = "< ";
		for (int n = 0; n < a; n++) {
			s += String.format("%+03d ", vector[n]);
		}
		s += ">";

		return s;

	}

	public static int activationsToInt(float[] output) {

		if (output == null)
			return -1;

		float max = 0;
		int r = 0;
		for (int j = 0; j < output.length; j++) {
			if (output[j] > max) {
				max = output[j];
				r = j;
			}
		}
		return r;
	}

	public static float[] digitToArray(int i) {
		return digitToArray(i, 10);
	}

	public static float[] digitToArray(int i, int max) {
		float[] a = new float[max];
		for (int j = 0; j < max; j++) {
			if (j == i)
				a[j] = 1f;
			else
				a[j] = 0f;
		}
		return a;
	}

	public static Integer[] shuffle(Integer[] intArray) {
		List<Integer> intList = Arrays.asList(intArray);

		Collections.shuffle(intList);

		Integer[] out = new Integer[intArray.length];

		intList.toArray(out);

		return out;
	}

	public static Integer[] shuffle(int size) {
		Integer[] a = new Integer[size];
		for (int i = 0; i < size; i++)
			a[i] = i;

		Integer[] s = NeuralMath.shuffle(a);
		return s;
	}

}
