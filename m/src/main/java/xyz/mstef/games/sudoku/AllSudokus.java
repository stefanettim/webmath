package xyz.mstef.games.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class AllSudokus {

	private static String cols = "123456789";
	private static String rows = "ABCDEFGHI";
	private static List<String> squares;
	private static Set<List<String>> units;
	private static Map<String,Set<List<String>>> unitsMap;
	private static Map<String,Set<String>> peersMap;
	private static char[] digits = "123456789".toCharArray();

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
	
	public static TreeMap<String,String> empty(){
		TreeMap<String,String> valuesSquareMap = new TreeMap<String,String>();
		for(String s : squares) {
			String hs = "123456789";				
			valuesSquareMap.put(s, hs);
		}
		return valuesSquareMap;
	}
	
	
	public static Map<String,String> parse(String grid){
		Map<String,String> valuesSquareMap = empty();
		int p=0;
		for(String s : squares) {
			char c = grid.charAt(p);
			if(c!='.'&&c!='0') {
				Map<String, String> a = assign(valuesSquareMap,s,c);
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

	private static Map<String, String> assign(Map<String, String> values, String s, char i) {
		String squareValues = values.get(s);
		
		for(char c : squareValues.toCharArray()) {
			if(c!=i) {
				Map<String, String> e = eliminate(values, s, c);
				if(e==null) {
					return null;
				}
			}
		}
		return values;
	}

	private static Map<String, String> eliminate(Map<String, String> values, String s, char v) {
		
		String squareValues = values.get(s);
		
		if(!squareValues.contains(""+v)) {
			return values;
		}
		
		squareValues = squareValues.replaceAll(""+v, "");
		
		values.put(s, squareValues);
		
		if(squareValues.length()==0) {
			return null;
		}

		if(squareValues.length()==1) {
			char r = squareValues.charAt(0);
			for(String x : peersMap.get(s)) {
				values = eliminate(values, x, r);
				if(values==null) {
					return null;
				}
			}
		}
		
		Set<String> av = null;
		for(List<String> unit : unitsMap.get(s)) {

			for(char x : digits ) {
				
				av = new HashSet<String>();
				
				for(String su : unit) {
					if(values.get(su).contains(""+x)) {
						av.add(su);
						if(av.size()>1) {
							break;
						}
				}
				}
				
				if(av.size()==1) {
					String ns = av.iterator().next();
					
					if(values.get(ns).length()>1) {
						//printAv(values);
						//System.out.println("setting "+x+" for "+ns);
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
		
		
			
		Map<String, String> values = 
				parse(".27546....8......3..1.......4..1..9....9.8....5..7..6.......3..8......4....12765.");
		
		System.out.println();
		print(values);
		
		System.out.println();
		printAv(values);
		
		System.out.println();
		Map<String, String> res = search(values);
		
		System.out.println();
		print(res);
		
	}




	static Map<String, String> search(Map<String, String> values) {
		
		if(values==null) {
			return null;
		}
		
		boolean solved = true;
		int minsize=100;
		String candidate = null;
		
		for(String s : squares) {
			int size = values.get(s).length();
			
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
		
		
		for(char i : values.get(candidate).toCharArray()) {
			
			Map<String, String> newValues = new TreeMap<String,String>(values);
			
			newValues = assign(newValues, candidate, i);
			
			Map<String, String> solution = search(newValues);
			
			if(solution!=null) {
				return solution;
			}
		}
		
		return null;
	}


	static void print(Map<String, String> values) {
		int p=1;
		for(String s : squares) {
			String v = values.get(s);
			String o=" ";
			if(v.length()==1) {
				o=""+v;
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

	private static void printAv(Map<String, String> values) {
		int p=1;
		for(String s : squares) {
			String v = values.get(s);
			String o=""+v;
			if(v.length()==1) {
				o="("+v+")";
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
