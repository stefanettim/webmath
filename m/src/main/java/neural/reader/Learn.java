package neural.reader;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Learn {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

		ReadMachine rm = new ReadMachine();
		rm.learn(0, "lastRead");

		System.out.println("==============================================");
		rm.trainingFullTest();
		rm.testFullTest();

	}

}
