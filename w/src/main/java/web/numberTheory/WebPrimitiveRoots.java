package web.numberTheory;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import numbers.MException;
import numbers.PrimitiveRoots;
import numbers.results.PrimitiveRootsResult;
import primes.IntPrimes;

public class WebPrimitiveRoots {

	private static void solve(Integer n, HttpServletRequest request) throws  MException, ClassNotFoundException, IOException, InterruptedException {

		if(n==null) {
			return;
		}

		IntPrimes ip = WebPrimes.getCachedIntPrimes(request);
		PrimitiveRootsResult primitiveRootsResult = PrimitiveRoots.searchPrimitiveRoots(n, ip);
		request.setAttribute("primitiveRootsResult", primitiveRootsResult);
	}
	
	public static void clear(HttpServletRequest request, HttpServletResponse response) throws MException {
		request.setAttribute("primitiveRootsResult", null);
	}

	public static void solve(HttpServletRequest request, HttpServletResponse response) throws MException, ClassNotFoundException, IOException, InterruptedException {
		Integer n = WebUtils.readInteger("n", request);
		solve(n,request);
	}

	public static void e1(HttpServletRequest request, HttpServletResponse response) throws MException, ClassNotFoundException, IOException, InterruptedException {
		solve(5,request);
	}
	public static void e2(HttpServletRequest request, HttpServletResponse response) throws MException, ClassNotFoundException, IOException, InterruptedException {
		solve(9,request);
	}
	public static void e3(HttpServletRequest request, HttpServletResponse response) throws MException, ClassNotFoundException, IOException, InterruptedException {
		solve(11,request);
	}
	public static void e4(HttpServletRequest request, HttpServletResponse response) throws MException, ClassNotFoundException, IOException, InterruptedException {
		solve(13,request);
	}
	public static void e5(HttpServletRequest request, HttpServletResponse response) throws MException, ClassNotFoundException, IOException, InterruptedException {
		solve(15,request);
	}
	public static void e6(HttpServletRequest request, HttpServletResponse response) throws MException, ClassNotFoundException, IOException, InterruptedException {
		solve(31,request);
	}
	public static void e7(HttpServletRequest request, HttpServletResponse response) throws MException, ClassNotFoundException, IOException, InterruptedException {
		solve(50,request);
	}

	public static void pr1(HttpServletRequest request, HttpServletResponse response) throws MException, ClassNotFoundException, IOException, InterruptedException {
		solve(2,request);
	}
	public static void pr2(HttpServletRequest request, HttpServletResponse response) throws MException, ClassNotFoundException, IOException, InterruptedException {
		solve(4,request);
	}
	public static void pr3(HttpServletRequest request, HttpServletResponse response) throws MException, ClassNotFoundException, IOException, InterruptedException {
		solve(7,request);
	}
	public static void pr4(HttpServletRequest request, HttpServletResponse response) throws MException, ClassNotFoundException, IOException, InterruptedException {
		solve(18,request);
	}
	public static void pr5(HttpServletRequest request, HttpServletResponse response) throws MException, ClassNotFoundException, IOException, InterruptedException {
		solve(25,request);
	}
	public static void pr6(HttpServletRequest request, HttpServletResponse response) throws MException, ClassNotFoundException, IOException, InterruptedException {
		solve(54,request);
	}
}
