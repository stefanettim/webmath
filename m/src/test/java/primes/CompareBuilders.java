package primes;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

import org.junit.jupiter.api.Test;

import numbers.MException;

// 2.300e+10       1008309544        22999999987     6417   6.36e-04

public class CompareBuilders {

	@Test
	void testCompareBuilders() throws InterruptedException, MException {

		OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
		int cpus = osBean.getAvailableProcessors();
		String arc = osBean.getArch();
		System.out.println("processors     : " + cpus + " (" + arc + ")");

		int max = 1000 * 1000 * 1000 * 10;
		// max = 10000;  // 1229
		max = 1000 * 1000 * 100;

		System.out.println("max            : " + max);

		int threads = cpus;
		System.out.println("threads        : " + threads);

		int kBlockSize = 256;
		System.out.println("k block size   : " + kBlockSize);

		int mDelay = 0;
		System.out.println("delay ms       : " + mDelay);

		{
			System.out.println("--------- Block Parallel ---------");
			IntPrimesBuilder epb = new EratosteneBlockParallelBuilder(max, threads, kBlockSize, mDelay);
			epb.test();
		}
		
		  { 
		    System.out.println("--------- Block    ---------"); 
		    IntPrimesBuilder ebb = new EratosteneBlockBuilder(max,kBlockSize); 
			ebb.test();
		  }
		  
		  { 
			  System.out.println("--------- Parallel ---------"); 
			  IntPrimesBuilder ebp = new EratosteneParallelBuilder(max, threads); 
			  ebp.test();
		  }
		  
		  { 
			  System.out.println("--------- Simple   ---------"); 
			  IntPrimesBuilder ebs = new EratosteneSimpleBuilder(max); 
			  ebs.test();
		}
		 
		  
		System.out.println("----------------------------------- DONE ----------------------------");
	}

}