package primes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import utils.MProperties;

public class IntPrimesFactory {
	
	private static IntPrimes intPrimes;
	
	public static IntPrimes getCachedIntPrimes() throws ClassNotFoundException, IOException, InterruptedException {
		if(intPrimes==null) {
			System.out.println("initializing intPrimes cache");
			intPrimes=load();
		}
		return intPrimes;
	}
	
	public static void saveMain(IntPrimes bigPrimes) throws IOException {
		save("m", "main",bigPrimes);
	}

	public static IntPrimes loadMain() throws IOException, ClassNotFoundException, InterruptedException {
		return load("m", "main");
	}

	public static void save(String label, IntPrimes bigPrimes) throws IOException {
		save("m", label,bigPrimes);
	}

	public static IntPrimes load(String label) throws IOException, ClassNotFoundException, InterruptedException {
		return load("m", label);
	}

	private static String serializeName(String context, String label)  {
		MProperties mProperties = MProperties.load(context);
		String name = mProperties.getProperty("primesFactory.dir");
		name+=File.separator+"primesFactory_int_"+label+".ser";
		return name;
	}
	
	public static void save(String context, String label, IntPrimes bigPrimes) throws IOException {
		String serializeName = serializeName(context, label);
		System.out.println("Saving "+serializeName);
		FileOutputStream fileOut = new FileOutputStream(serializeName);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(bigPrimes);
		out.close();
		fileOut.close();
	}

	public static IntPrimes load(String context, String label) throws ClassNotFoundException, InterruptedException, IOException {
		String serializeName = serializeName(context, label);
		System.out.println("Loading "+serializeName);
		
		IntPrimes primes = null;
		
		try {
		FileInputStream fileIn = new FileInputStream(serializeName);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		primes = (IntPrimes) in.readObject();
		in.close();
		fileIn.close();
		System.out.println("IntPrimes loaded to "+primes.limit+" last "+primes.getLast());
		} catch (Exception e) {
			System.out.println("Error loading properties : "+e.getMessage());
		} 
		
		if(primes==null) {
			primes = generate(10000);
			save(context,label,primes);
		}
		
		return primes;
	}

	public static IntPrimes loadTestcase() throws ClassNotFoundException, IOException, InterruptedException {
		return load("testcase");
	}

	public static void saveTestcase(IntPrimes primes) throws IOException {
		save("testcase",primes);
	}

	public static IntPrimes generate(int limit) throws InterruptedException {
		IntPrimesBuilder b = new EratosteneBlockParallelBuilder(limit, 8, 256, 0);
		b.build();
		IntPrimes pl = b.getPrimesList();
		System.out.println("maxPrime:"+pl.getLast()+" size "+pl.getCount());

		return b.getPrimesList();
	}

	public static IntPrimes load() throws IOException, ClassNotFoundException, InterruptedException {
		MProperties mProperties = MProperties.load(); 
		return load(mProperties.getProperty("intPrimes"));
	}



}