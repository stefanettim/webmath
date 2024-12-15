package numbers.results;

import java.math.BigInteger;
import java.util.ArrayList;

public class BigFermatResult {

	BigInteger order;
	BigInteger fermatNumber;
	BigInteger divisor;
	ArrayList<BigInteger> divisors = new ArrayList<>();
	Boolean prime;
	Boolean verified;
	
	public BigInteger getOrder() {
		return order;
	}
	public void setOrder(BigInteger order) {
		this.order = order;
	}
	public BigInteger getFermatNumber() {
		return fermatNumber;
	}
	public void setFermatNumber(BigInteger fermatNumber) {
		this.fermatNumber = fermatNumber;
	}
	public ArrayList<BigInteger> getDivisors() {
		return divisors;
	}
	public void setDivisors(ArrayList<BigInteger> divisors) {
		this.divisors = divisors;
	}
	public Boolean getPrime() {
		return prime;
	}
	public void setPrime(Boolean prime) {
		this.prime = prime;
	}
	public Boolean getVerified() {
		return verified;
	}
	public void setVerified(Boolean verified) {
		this.verified = verified;
	}
	public BigInteger getDivisor() {
		return divisor;
	}
	public void setDivisor(BigInteger divisor) {
		this.divisor = divisor;
	}

	
}
