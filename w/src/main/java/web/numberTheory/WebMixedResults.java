package web.numberTheory;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import numbers.MException;
import numbers.NumberTheory;
import numbers.results.DivisorsResult;
import numbers.results.QrmpResult;
import numbers.results.RelativePrimesResult;
import numbers.results.SumOfPositiveIntegersResult;
import web.WException;

public class WebMixedResults {
	
	private static long LIMIT=10l*1000l;
	
	//	public static Long readN(HttpServletRequest request, HttpServletResponse response) throws WException {
	//		return readN(request,response,Long.MAX_VALUE);
	//	}

	public static Long readN(HttpServletRequest request, HttpServletResponse response, Long maxn) throws WException {
		String nString = request.getParameter("n");
		Long n = Long.parseLong(nString);
		if(n<=0) {
			throw new WException("n cannot be negative or 0");
		}
		
		if(n>maxn) {
			n=maxn;
		}
		
		request.setAttribute("n", n);
		return n;
	}

	public static void set10(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("n", 10l);
	}

	public static void set100(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("n", 100l);
	}

	public static void set200(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("n", 200l);
	}

	public static void set500(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("n", 500l);
	}

	public static void set1000(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("n", 1000l);
	}

	public static void set10000(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("n", 10000l);
	}

	public static void perfectNumbers(HttpServletRequest request, HttpServletResponse response) throws WException {
		listDivisors(true, request, response);
	}

	public static void divisors(HttpServletRequest request, HttpServletResponse response) throws WException {
		listDivisors(false, request, response);
	}
	
	public static void listDivisors(boolean onlyPerfect, HttpServletRequest request, HttpServletResponse response) throws WException {
		Long n=readN(request, response, 10000l);
		ArrayList<DivisorsResult> divisorsList = NumberTheory.listDivisors(onlyPerfect, n);
		request.setAttribute("onlyPerfect", onlyPerfect);
		request.setAttribute("divisorsResultList", divisorsList);
	}

	
	// primes
	public static void primes(HttpServletRequest request, HttpServletResponse response) throws InterruptedException, WException, MException, ClassNotFoundException, IOException {
		int n=readN(request, response,1000l*1000l).intValue();
		WebPrimes.preparePrimes(request,n);
	}
	
	// relativePrimes
	public static void relativePrimes(HttpServletRequest request, HttpServletResponse response) throws WException, InterruptedException, MException {
		Long n=readN(request, response, 1000l);
		
		ArrayList<RelativePrimesResult> relativePrimesResults = new ArrayList<>();
		for(int l=1;l<=n;l++) {
			RelativePrimesResult relativePrimesResult = NumberTheory.searchRelativePrimes(l);
			relativePrimesResults.add(relativePrimesResult);
		}
		request.setAttribute("relativePrimesResults", relativePrimesResults);
	}
	
	// sumPerfectSquares1or2
	public static void sumPerfectSquares1or2(HttpServletRequest request, HttpServletResponse response) throws InterruptedException, WException {
		Long n=readN(request, response,LIMIT);
		ArrayList<ArrayList<Long>> sumPerfectSquares1or2List = new ArrayList<>();
		for(long l=0;l<n;l++) {
			ArrayList<ArrayList<Long>> sumPerfectSquares1or2 = NumberTheory.sumPerfectSquares1or2(l);
			sumPerfectSquares1or2List.addAll(sumPerfectSquares1or2);
		}
		
		HashSet<Long> hll = new HashSet<>();
		for(ArrayList<Long> sol : sumPerfectSquares1or2List) {
			hll.add(sol.get(0));
		}
		long solutions=hll.size();
		
		request.setAttribute("sumPerfectSquares1or2List", sumPerfectSquares1or2List);
		request.setAttribute("solutions", solutions);
	}
	
	// sumPerfectSquares1or2or3
	public static void sumPerfectSquares1or2or3(HttpServletRequest request, HttpServletResponse response) throws InterruptedException, WException {
		Long n=readN(request, response,LIMIT);
		ArrayList<ArrayList<Long>> sumPerfectSquares1or2or3List = new ArrayList<>();
		for(long l=0;l<n;l++) {
			ArrayList<ArrayList<Long>> sumPerfectSquares1or2or3 = 
					NumberTheory.sumPerfectSquares1or2or3(l);
			sumPerfectSquares1or2or3List.addAll(sumPerfectSquares1or2or3);
		}
		
		HashSet<Long> hll = new HashSet<>();
		for(ArrayList<Long> sol : sumPerfectSquares1or2or3List) {
			hll.add(sol.get(0));
		}
		long solutions=hll.size();
		
		request.setAttribute("sumPerfectSquares1or2or3List", sumPerfectSquares1or2or3List);
		request.setAttribute("solutions", solutions);
	}
	
	// sumPerfectSquares1or2or3or4
	public static void sumPerfectSquares1or2or3or4(HttpServletRequest request, HttpServletResponse response) throws InterruptedException, WException {
		Long n=readN(request, response,LIMIT);
		ArrayList<ArrayList<Long>> sumPerfectSquares1or2or3or4List = new ArrayList<>();
		for(long l=0;l<n;l++) {
			ArrayList<ArrayList<Long>> sumPerfectSquares1or2or3or4 = 
					NumberTheory.sumPerfectSquares1or2or3or4(l);
			sumPerfectSquares1or2or3or4List.addAll(sumPerfectSquares1or2or3or4);
		}
		
		HashSet<Long> hll = new HashSet<>();
		for(ArrayList<Long> sol : sumPerfectSquares1or2or3or4List) {
			hll.add(sol.get(0));
		}
		long solutions=hll.size();
		
		request.setAttribute("sumPerfectSquares1or2or3or4List", sumPerfectSquares1or2or3or4List);
		request.setAttribute("solutions", solutions);
	}
	
	// listQuadraticResidueModp
	public static void listQuadraticResidueModp(HttpServletRequest request, HttpServletResponse response) throws WException, InterruptedException, MException, ClassNotFoundException, IOException {
		int n=readN(request, response, 1000l).intValue();
		ArrayList<QrmpResult> listQRMP = new ArrayList<>();
		ArrayList<Integer> primes = WebPrimes.preparePrimes(request, n);
		for(int p : primes) {
			if(p>0 && p<=n) {
				QrmpResult qrmpResult = NumberTheory.searchQuadraticResidueModuloP(p);
			    listQRMP.add(qrmpResult);
			}
		}
		request.setAttribute("listQRMP", listQRMP);
		request.removeAttribute("primes");
	}

	
	private static void sumPositiveIntegers(String kind,String message,HttpServletRequest request, HttpServletResponse response) throws WException, InterruptedException, MException {
		Long n=readN(request, response, 40l);
		
		ArrayList<SumOfPositiveIntegersResult> listSumPositiveIntegers = new ArrayList<>();
		for(int l=1;l<=n;l++) {
			SumOfPositiveIntegersResult SPI = NumberTheory.sumOfPositiveIntegers(l, kind);
			listSumPositiveIntegers.add(SPI);
		}
		request.setAttribute("sumPositiveIntegersMessage", message);
		request.setAttribute("listSumPositiveIntegers", listSumPositiveIntegers);
	}

	public static void sumDistinctPositiveIntegers(HttpServletRequest request, HttpServletResponse response) throws WException, InterruptedException, MException {
		sumPositiveIntegers("DISTINCT", "distinct", request, response);
	}

	public static void sumOddPositiveIntegers(HttpServletRequest request, HttpServletResponse response) throws WException, InterruptedException, MException {
		sumPositiveIntegers("ODD", "odd", request, response);
	}
	
	public static void sumPowerOf2PositiveIntegers(HttpServletRequest request, HttpServletResponse response) throws WException, InterruptedException, MException {
		sumPositiveIntegers("POWER2", "power of 2", request, response);
	}
	
	public static void sumNotMultipleOf3PositiveIntegers(HttpServletRequest request, HttpServletResponse response) throws WException, InterruptedException, MException {
		sumPositiveIntegers("NOTMUL3", "not multiple of 3", request, response);
	}

	public static void sumMax2DistinctPositiveIntegers(HttpServletRequest request, HttpServletResponse response) throws WException, InterruptedException, MException {
		sumPositiveIntegers("MAX2", "no more than twice", request, response);
	}
	
}
