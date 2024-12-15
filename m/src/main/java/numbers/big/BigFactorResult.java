package numbers.big;

import java.math.BigInteger;

public class BigFactorResult {

	public BigInteger factor = BigInteger.ZERO;
	public int power = 0;
	
	
	
	public BigFactorResult(BigInteger factor, int power) {
		super();
		this.factor = factor;
		this.power = power;
	}
	public BigInteger getFactor() {
		return factor;
	}
	public void setFactor(BigInteger factor) {
		this.factor = factor;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	
	
	
}
