package xyz.mstef.games.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class AllSudokusArrayList {

	private static String cols = "123456789";
	private static String rows = "ABCDEFGHI";
	private static List<String> squares;
	private static Set<List<String>> units;
	private static Map<String,Set<List<String>>> unitsMap;
	private static Map<String,Set<String>> peersMap;
	private static ArrayList<Integer> digits = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
 

	/*
	 * def cross(A, B): "Cross product of elements in A and elements in B." 
	 * return [a+b for a in A for b in B]
	 */
	public static List<String> cross(String a, String b) {
		List<String> c = new ArrayList<String>();
		for (char ca : a.toCharArray()) {
			for (char cb : b.toCharArray()) {
				c.add("" + ca + cb);
			}
		}
		return c;
	}

	static  {
		
		squares = cross(rows,cols);
		
		units = new HashSet<List<String>>();
		
		for(String c : new String[] {"123","456","789"} ) {
			for(String r : new String[] {"ABC","DEF","GHI"} ) {
				List<String> u = cross(r,c);
				units.add(u);
			}
		}
		
		for(char r : rows.toCharArray()) {
			List<String> u = cross(""+r,cols);
			units.add(u);
		}

		for(char c : cols.toCharArray()) {
			List<String> u = cross(rows,""+c);
			units.add(u);
		}
		
		unitsMap = new TreeMap<String, Set<List<String>>>();
		for(String s : squares) {
			Set<List<String>> squareUnits = new HashSet<List<String>>();
			for(List<String> u : units) {
				if(u.contains(s)) {
					squareUnits.add(u);
				}
			}
			unitsMap.put(s, squareUnits);
		}
		
		peersMap=new TreeMap<String, Set<String>>();
		for(String s : squares) {
			HashSet<String> squarePeers = new HashSet<String>();
			for(List<String> u : unitsMap.get(s)) {
				squarePeers.addAll(u);
			}
			squarePeers.remove(s);
			peersMap.put(s, squarePeers);
		}

	}
	
	public static TreeMap<String,List<Integer>> empty(){
		TreeMap<String,List<Integer>> valuesSquareMap = new TreeMap<String,List<Integer>>();
		for(String s : squares) {
			ArrayList<Integer> hs = new ArrayList<>();				
			hs.addAll(digits);
			valuesSquareMap.put(s, hs);
		}
		return valuesSquareMap;
	}
	
	
	public static Map<String,List<Integer>> parse(String grid){
		Map<String,List<Integer>> valuesSquareMap = empty();
		int p=0;
		for(String s : squares) {
			char c = grid.charAt(p);
			if(c!='.'&&c!='0') {
				Integer i = Integer.parseInt(""+c);
				Map<String, List<Integer>> a = assign(valuesSquareMap,s,i);
				if(a==null) {
					return null;
				}
			}
			p++;
		}
		if(p==81) {
			return valuesSquareMap;
		}
		
		return null;

	}

	private static Map<String, List<Integer>> assign(Map<String, List<Integer>> values, String s, Integer i) {
		
		List<Integer> squareValues = new ArrayList<Integer>(values.get(s));
		
		for(Integer v : squareValues) {
			if(v!=i) {
				values = eliminate(values, s, v);
				if(values==null) {
					return null;
				}
			}
		}
		return values;
	}

	private static Map<String, List<Integer>> eliminate(Map<String, List<Integer>> values, String s, Integer v) {
		
		List<Integer> squareValues = values.get(s);
		
		boolean rm = squareValues.remove(v);
		
		if(!rm) {
			return values;
		}

		if(squareValues.size()==0) {
			return null;
		}

		if(squareValues.size()==1) {
			Integer r = squareValues.iterator().next();
			for(String x : peersMap.get(s)) {
				//System.out.println("OnlyValue on "+s+" eliminate "+x+" for "+r);
				values = eliminate(values, x, r);
				if(values==null) {
					return null;
				}
			}
		}
		
		Set<String> av = null;
		
		for(List<String> unit : unitsMap.get(s)) {
			
			for(Integer x : digits) {

				av = new HashSet<String>();
				
				for(String su : unit) {
					if(values.get(su).contains(x)) {
						av.add(su);
						if(av.size()>1) {
							break;
						}
					}
				}
				
				if(av.size()==1) {
					String ns = av.iterator().next();
					List<Integer> lp = values.get(ns);
					if(lp.size()>1) {
						//System.out.println("NakedSinlge setting "+x+" for "+ns);
						values = assign(values, ns, x);
						if(values==null) {
							return null;
						}
					}
				}

			}
		}
		
		return values;
	}

	public static void main(String[] args) {

		boolean ok=true;
		
		ok&= squares.size()==81;
		
		ok&= units.size()==27;
		
		for(Set<List<String>> us : unitsMap.values() ) {
			ok&= us.size()==3;
		}
		for(Set<String> p : peersMap.values()) {
			ok&= p.size()==20;
		}
		
		ok&= unitsMap.get("C2").equals( new HashSet<List<String>>(
			Arrays.asList(
					new ArrayList<String>(Arrays.asList("A2", "B2", "C2", "D2", "E2", "F2", "G2", "H2", "I2")),
					new ArrayList<String>(Arrays.asList("C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9")),
					new ArrayList<String>(Arrays.asList("A1", "A2", "A3", "B1", "B2", "B3", "C1", "C2", "C3")) ) ) );

		ok&= peersMap.get("C2").equals( new HashSet<String>(
				Arrays.asList("A2", "B2", "D2", "E2", "F2", "G2", "H2", "I2", "C1", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "A1", "A3", "B1", "B3")
				) );
		
		if(!ok) {
			System.exit(1);
		}
		
		System.out.println("Tests passed");
		
		
			
		Map<String, List<Integer>> values = 
				parse(".......12........3..23..4....18....5.6..7.8.......9.....85.....9...4.5..47...6...");
		
		System.out.println();
		print(values);
		
		System.out.println();
		printAv(values);
		
		System.out.println();
		Map<String, List<Integer>> res = search(values);
		
		System.out.println();
		print(res);
		
	}




	static Map<String, List<Integer>> search(Map<String, List<Integer>> values) {
		
		if(values==null) {
			return null;
		}
		
		boolean solved = true;
		int minsize=100;
		String candidate = null;
		
		for(String s : squares) {
			int size = values.get(s).size();
			
			if(size>1) {
				solved=false;
			}
			
			if(minsize>size && size>1) {
				minsize=size;
				candidate=s;
			}
			
			if(size==2) {
				break;
			}

		}
		
		if(solved) {
			return values;
		}
		
		
		for(Integer i : values.get(candidate)) {
			
			Map<String, List<Integer>> newValues = clone(values);
			
			newValues = assign(newValues, candidate, i);
			
			Map<String, List<Integer>> solution = search(newValues);
			
			if(solution!=null) {
				return solution;
			}
		}
		
		return null;
	}


	private static Map<String, List<Integer>> clone(Map<String, List<Integer>> values) {
		Map<String, List<Integer>> clone = new TreeMap<String,List<Integer>>();
		for(String s : values.keySet()) {
			List<Integer> ci = new ArrayList<Integer>( values.get(s) );
			clone.put(s, ci);
		}
		return clone;
	}


	static void print(Map<String, List<Integer>> values) {
		int p=1;
		for(String s : squares) {
			List<Integer> v = values.get(s);
			String o=" ";
			if(v.size()==1) {
				o=""+v.iterator().next();
			}
			
			System.out.print(o);
			
			if(p%27==0 && p>0 && p<81) {
				System.out.println();
				System.out.println("---+---+---");
			}else if(p%9==0) {
				System.out.println();
			}
			else if(p%3==0) {
				System.out.print("|");
			}
			p++;
		}
	}

	private static void printAv(Map<String, List<Integer>> values) {
		int p=1;
		for(String s : squares) {
			List<Integer> v = values.get(s);
			String o="";
			for(Integer i : v) {
				o+=""+i;
			}
			
			if(v.size()==1) {
				o="("+v.iterator().next()+")";
			}

			System.out.printf("%9s",o);
			
			if(p%27==0 && p>0 && p<81) {
				System.out.println();
				System.out.println("---------------------------+---------------------------+---------------------------");
			}else if(p%9==0) {
				System.out.println();
			}
			else if(p%3==0) {
				System.out.print("|");
			}
			p++;
		}
	}
}
