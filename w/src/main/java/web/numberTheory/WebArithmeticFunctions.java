package web.numberTheory;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import numbers.MException;
import numbers.NumberTheory;
import numbers.results.EulerDivisorSumResult;
import numbers.results.EulerPropertiesResult;
import numbers.results.Pair;
import numbers.results.RelativePrimesResult;

public class WebArithmeticFunctions {

	public static void clear(HttpServletRequest request, HttpServletResponse response) throws MException, InterruptedException, ClassNotFoundException, IOException {
		request.setAttribute("relativePrimesResult", null);
		request.setAttribute("n", null);
	}

	public static void solve(Long n, HttpServletRequest request, HttpServletResponse response) throws MException, InterruptedException, ClassNotFoundException, IOException {
		RelativePrimesResult relativePrimesResult = NumberTheory.searchRelativePrimes(n);
		request.setAttribute("relativePrimesResult", relativePrimesResult);
		request.setAttribute("n", n);
	}

	public static void solve(HttpServletRequest request, HttpServletResponse response) throws MException, InterruptedException, ClassNotFoundException, IOException {
		Long n = WebUtils.readLong("n", 10l, 10000l, request);
		solve(n, request, response);
	}

	public static void solve100(HttpServletRequest request, HttpServletResponse response) throws MException, InterruptedException, ClassNotFoundException, IOException {
		solve(100l, request, response);
	}

	public static void solve29(HttpServletRequest request, HttpServletResponse response) throws MException, InterruptedException, ClassNotFoundException, IOException {
		solve(29l, request, response);
	}

	public static void solve10(HttpServletRequest request, HttpServletResponse response) throws MException, InterruptedException, ClassNotFoundException, IOException {
		solve(10l, request, response);
	}

	public static void plot(Long n, HttpServletRequest request, HttpServletResponse response) throws MException, InterruptedException, ClassNotFoundException, IOException {
		ArrayList<Pair> eulerPairs = new ArrayList<>();
		for(int i=1;i<=n;i++) {
			RelativePrimesResult r = NumberTheory.searchRelativePrimes(i);
			eulerPairs.add(new Pair(r.n,r.count));
		}
		request.setAttribute("eulerPairs", eulerPairs);
		request.setAttribute("n", n);
	}

	public static void plot(HttpServletRequest request, HttpServletResponse response) throws MException, InterruptedException, ClassNotFoundException, IOException {
		Long n = WebUtils.readLong("n", 100l, 2000l, request );
		plot(n,request,response);
	}
	
	public static void plot100(HttpServletRequest request, HttpServletResponse response) throws MException, InterruptedException, ClassNotFoundException, IOException {
		plot(100l,request,response);
	}

	public static void plot1000(HttpServletRequest request, HttpServletResponse response) throws MException, InterruptedException, ClassNotFoundException, IOException {
		plot(1000l,request,response);
	}

	public static void plot2000(HttpServletRequest request, HttpServletResponse response) throws MException, InterruptedException, ClassNotFoundException, IOException {
		plot(2000l,request,response);
	}

	public static void table(long n, HttpServletRequest request, HttpServletResponse response) throws MException, InterruptedException, ClassNotFoundException, IOException {
		ArrayList<EulerPropertiesResult> eulerTable = NumberTheory.listEulerProperties(n);
		request.setAttribute("eulerTable", eulerTable);
		request.setAttribute("n", n);
	}

	public static void table(HttpServletRequest request, HttpServletResponse response) throws MException, InterruptedException, ClassNotFoundException, IOException {
		Long n = WebUtils.readLong("n", 20l, 2000l, request );
		table(n,request,response);
	}

	public static void table20(HttpServletRequest request, HttpServletResponse response) throws MException, InterruptedException, ClassNotFoundException, IOException {
		table(20l,request,response);
	}

	public static void divisorSum(HttpServletRequest request, HttpServletResponse response) throws MException, InterruptedException, ClassNotFoundException, IOException {
		Long n = WebUtils.readLong("n", 6l, 2000l, request );
		
		ArrayList<EulerDivisorSumResult> divisorSumTable = NumberTheory.listEulerDivisorSum(n);
		long divisorSumTot=0;
		long divisorTdCountTot=0;
		long divisorPhiNOverDTot=0;
		
		for(EulerDivisorSumResult r:divisorSumTable) {
			if(r.divisor) {
				divisorSumTot+=r.relativePrimesResult.count;
				divisorTdCountTot+=r.getTdCount();
				divisorPhiNOverDTot+=r.getRelativePrimesResultNOverD().count;
			}
		}	
		
		request.setAttribute("divisorSumTable", divisorSumTable);
		request.setAttribute("divisorSumTtot", divisorSumTot);
		request.setAttribute("divisorTdCountTot", divisorTdCountTot);
		request.setAttribute("divisorPhiNOverDTot", divisorPhiNOverDTot);
		request.setAttribute("n", n);
	}
	
}
