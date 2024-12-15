package numbers;

import java.util.ArrayList;

import numbers.results.Pair;

public class PrimeNumbers {
	
	public static ArrayList<Pair> generatePiList(ArrayList<Integer> primes, int limit){
		
		ArrayList<Pair> pairs = new ArrayList<>();
		pairs.add(new Pair(0, 0));
		
		int pi=0;
		for(int p : primes) {
			
			if(p>limit) {
				break;
			}
			
			pairs.add(new Pair(p, pi));
			pi++;
			pairs.add(new Pair(p, pi));
			
		}

		return pairs;
	}

	public static ArrayList<Pair> generatexLogx(int i) {
		ArrayList<Pair> pairs = new ArrayList<>();
		for(int x=2;x<i;x++) {
			double xd = (double)x;
			double yd = xd / Math.log(xd);
			int y = (int) yd;
			pairs.add(new Pair(x, y));
		}
		return pairs;
	}

}
