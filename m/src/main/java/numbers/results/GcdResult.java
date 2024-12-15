package numbers.results;

import java.util.ArrayList;
import java.util.Collections;

public class GcdResult {

	public boolean completed=false;
	public long a;
	public long b;
	public long gcd;
	public ArrayList<Long[]> qrl = new ArrayList<Long[]>();

	

	public GcdResult(long a, long b) {
		super();
		this.a = a;
		this.b = b;
	}

	public String toString() {
		
		String s = "=======================================\n";
				s+="d=gcd("+a+","+b+")="+gcd;
		
		Long m = a;
		Long n = b;
		for(int i=0;i<qrl.size();i++) {
			s+="\n"+m+"= "+n+"*"+qrl.get(i)[0]+" + "+qrl.get(i)[1];
			m=n;
			n=qrl.get(i)[1];
		}
				
		return s;
	}

	public ArrayList<Long[]> getQrlRev() {
		ArrayList<Long[]> rev = new ArrayList<>(qrl);
		Collections.reverse(rev);
		return rev;
	}
	//----------------------------

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public long getA() {
		return a;
	}

	public void setA(long a) {
		this.a = a;
	}

	public long getB() {
		return b;
	}

	public void setB(long b) {
		this.b = b;
	}

	public long getGcd() {
		return gcd;
	}

	public void setGcd(long gcd) {
		this.gcd = gcd;
	}

	public ArrayList<Long[]> getQrl() {
		return qrl;
	}

	public void setQrl(ArrayList<Long[]> qrl) {
		this.qrl = qrl;
	}



	
}
