package web.numberTheory;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import numbers.MException;
import numbers.PrimeNumbers;
import numbers.big.BigPrimes;
import numbers.big.BigPrimesFactory;
import numbers.results.Pair;
import primes.IntPrimes;
import primes.IntPrimesFactory;
import utils.MProperties;
import web.WServlet;

public class WebPrimes {

	public static void init(HttpServletRequest request, HttpServletResponse response) throws MException, InterruptedException, ClassNotFoundException, IOException {
		WebPrimes.preparePrimes(request, 1000);
	}

	public static void reload(HttpServletRequest request, HttpServletResponse response) throws MException, InterruptedException, ClassNotFoundException, IOException {
		request.getServletContext().setAttribute("intPrimes", null);
		WebPrimes.preparePrimes(request, 1000);
	}

	public static BigPrimes getCachedBigPrimes(HttpServletRequest request) throws InterruptedException, MException, ClassNotFoundException, IOException {
		BigPrimes bigPrimes = (BigPrimes) request.getServletContext().getAttribute("bigPrimes");
		
		if(bigPrimes==null) {
			MProperties p = WServlet.readMProperties(request);
			bigPrimes = BigPrimesFactory.load(p.context, p.getProperty("bigPrimes"));
			request.getServletContext().setAttribute("bigPrimes", bigPrimes);
		}
		
		return bigPrimes;
		
	}

	public static IntPrimes getCachedIntPrimes(HttpServletRequest request) throws InterruptedException, MException, ClassNotFoundException, IOException {
		
		IntPrimes intPrimes = (IntPrimes) request.getServletContext().getAttribute("intPrimes");
		
		if(intPrimes==null) {
			MProperties p = WServlet.readMProperties(request);
			intPrimes = IntPrimesFactory.load(p.context, p.getProperty("intPrimes"));
			request.getServletContext().setAttribute("intPrimes", intPrimes);
		}
		
		return intPrimes;
		
	}
	
	public static ArrayList<Integer> preparePrimes(HttpServletRequest request, int maxPrime) throws InterruptedException, MException, ClassNotFoundException, IOException {
		
		IntPrimes intPrimes = getCachedIntPrimes(request);
		
		if(maxPrime>intPrimes.limit) {
			throw new MException("too big, primes limit "+intPrimes.limit);
		}
		
		ArrayList<Integer> primes = new ArrayList<>();
		int nprimes=0;
		for(Integer p : intPrimes.primes) {
			if(p<=maxPrime || maxPrime==-1) {
				primes.add(p);
				nprimes++;
			}
		}

		request.setAttribute("intPrimes", intPrimes);
		request.setAttribute("primes", primes);
		request.setAttribute("nprimes", nprimes);

		int plotLimit = 10000;

		ArrayList<Pair> primePairs = PrimeNumbers.generatePiList(intPrimes.primes, plotLimit);
		request.setAttribute("primePairs", primePairs);
		
		ArrayList<Pair> xloxPairs = PrimeNumbers.generatexLogx(plotLimit);
		request.setAttribute("xloxPairs", xloxPairs);

		return primes;
	}

}
