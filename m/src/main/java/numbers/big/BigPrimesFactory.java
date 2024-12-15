package numbers.big;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;

import utils.MProperties;

public class BigPrimesFactory {
	
	private static BigPrimes loadedBigPrimes = null;

	public static BigPrimes generate(BigInteger limit) {
		BigPrimes bigPrimes = new BigPrimes();
		build(bigPrimes,limit);
		return bigPrimes;
	}
	
	public static BigPrimes generate(long limit) {
		return generate(BigInteger.valueOf(limit));
	}

	public static void build(BigPrimes bigPrimes) {
		build(bigPrimes, BigInteger.valueOf(1*1000*1000));
	}

	public static void build(BigPrimes bigPrimes,long limit) {
		BigInteger limitBig = BigInteger.valueOf(limit);
		build(bigPrimes, limitBig);
	}

	
	private static void buildFirstLot(BigPrimes bigPrimes) {
		
		bigPrimes.clearLot();
		
		for(int i=2;i<bigPrimes.lotSize;i++) {
			if(bigPrimes.lot[i]) {
				bigPrimes.primes.add(BigInteger.valueOf(i));
				int j=i+i;
				while(j<bigPrimes.lotSize) {
					bigPrimes.lot[j]=false;
					j=j+i;
				}
			}
		}
	}

	public static void build(BigPrimes bigPrimes, BigInteger limit) {
		
		bigPrimes.limit = limit;
		
		if(limit.compareTo(BigInteger.valueOf(bigPrimes.lotSize))<0) {
			bigPrimes.lotSize = limit.intValue();
		}
		
		System.out.println("Building primes up to "+limit);
		
		buildFirstLot(bigPrimes);
		
		BigInteger lotStart = BigInteger.valueOf(bigPrimes.lotSize);
		while(lotStart.compareTo(limit)<0) {
			
			BigInteger lotEnd = lotStart.add(BigInteger.valueOf(bigPrimes.lotSize));
			bigPrimes.clearLot();
			//System.out.println("new lot "+lotStart+" - "+lotEnd);
			
			for(BigInteger p : bigPrimes.primes) {
				BigInteger loop = lotStart.divide(p).multiply(p);
				if(loop.compareTo(lotStart)<0) {
					loop = loop.add(p);
				}
				while(loop.compareTo(lotEnd)<0) {
					int offset = loop.subtract(lotStart).intValue();
					bigPrimes.lot[offset]=false;
					loop=loop.add(p);
				}
			}
			
			for(int i=0;i<bigPrimes.lotSize;i++) {
				if(bigPrimes.lot[i]) {
					BigInteger newPrime = lotStart.add(BigInteger.valueOf(i));
					bigPrimes.primes.add(newPrime);
				}
			}
			
			lotStart=lotEnd;
		}
		
		System.out.println("maxPrime:"+bigPrimes.last()+" size "+bigPrimes.primes.size());
	}
	
	public static void saveMain(BigPrimes bigPrimes) throws IOException {
		save("m", "main",bigPrimes);
	}

	public static BigPrimes loadMain() throws IOException, ClassNotFoundException {
		return load("m", "main");
	}

	public static void save(String label, BigPrimes bigPrimes) throws IOException {
		save("m", label,bigPrimes);
	}

	public static BigPrimes load(String label) throws IOException, ClassNotFoundException {
		return load("m", label);
	}

	private static String serializeName(String context, String label) throws IOException {
		MProperties mProperties = MProperties.load(context);
		String name = mProperties.getProperty("primesFactory.dir");
		if(name == null || name.trim().isEmpty()) {
			throw new IOException("property primesFactory.dir not found");
		}
		name+=File.separator+"primesFactory_big_"+label+".ser";
		return name;
	}
	
	public static void save(String context, String label, BigPrimes bigPrimes) throws IOException {
		String serializeName = serializeName(context, label);
		System.out.println("Saving "+serializeName);
		FileOutputStream fileOut = new FileOutputStream(serializeName);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(bigPrimes);
		out.close();
		fileOut.close();
	}

	public static BigPrimes load(String context, String label) throws IOException, ClassNotFoundException {
		String serializeName = serializeName(context, label);
		System.out.println("Loading "+serializeName);
		BigPrimes primes = null;

		try {
		FileInputStream fileIn = new FileInputStream(serializeName);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		primes = (BigPrimes) in.readObject();
		in.close();
		fileIn.close();
		System.out.println("BigPrimes loaded to "+primes.limit+" last "+primes.last());
		}catch(Exception e) {
			System.out.println("BigPrimes load error : "+e.getMessage());
		}
		
		if(primes==null) {
			primes = BigPrimesFactory.generate(100000);
			save(context, label, primes );
		}
		
		return primes;
	}

	public static BigPrimes loadTestcase() throws ClassNotFoundException, IOException {
		return load("testcase");
	}

	public static void saveTestcase(BigPrimes primes) throws IOException {
		save("testcase",primes);
	}

	public synchronized static BigPrimes load() throws IOException, ClassNotFoundException {
		
		if(loadedBigPrimes!=null) {
			return loadedBigPrimes;
		}
		
		MProperties mProperties = MProperties.load(); 
		loadedBigPrimes = load(mProperties.getProperty("bigPrimes"));
		
		return loadedBigPrimes;
	}
	
}
