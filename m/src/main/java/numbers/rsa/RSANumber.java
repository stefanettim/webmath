package numbers.rsa;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class RSANumber {
	
	String nString;
	String[] fStrings;

	public RSANumber(String n, String[] f) {
		super();
		this.nString = n;
		this.fStrings = f;
	}

	public String getnString() {
		return nString;
	}

	public String[] getfStrings() {
		return fStrings;
	}


	public BigInteger getN() {
		return new BigInteger(nString);
	}
	
	
	public List<BigInteger> getF() {
		List<BigInteger> f = new ArrayList<>();
		for(int i=0;i<fStrings.length;i++) {
			f.add(new BigInteger(fStrings[i]));
		}
		return f;
	}
	
	public boolean test() {
		System.out.println("-----------------------------------");
		System.out.println("Testing : "+getN());
		BigInteger r = getN();
		for(BigInteger i : getF()) {
			System.out.println("Factor:"+i);
			r = r.divide(i);
			System.out.println("other:"+r);
		}

		return r.equals(BigInteger.ONE);
	}
	
}
