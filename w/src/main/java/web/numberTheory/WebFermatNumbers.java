package web.numberTheory;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import numbers.MException;
import numbers.big.BigNumbers;
import numbers.big.BigPrimes;
import numbers.results.BigFermatResult;

public class WebFermatNumbers {

	public static void init(HttpServletRequest request, HttpServletResponse response) throws MException, InterruptedException, ClassNotFoundException, IOException {

		BigPrimes bigPrimes = WebPrimes.getCachedBigPrimes(request);
		
		ArrayList<BigFermatResult> results = new ArrayList<>(); 
		
		for(int n=1;n<=12;n++) {
			BigFermatResult r = BigNumbers.analyzeFermatNumber(n, bigPrimes);
			results.add(r);
		}
		
		request.setAttribute("results", results);

	}



}
