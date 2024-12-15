package numbers.results;

import numbers.MException;

public class ChineseReminderTheoremResult {

	public Long[] a;
	public Long[] b;
	public Long[] m;
	public long[] c;
	public long[] n;
	public long[] i;
	public long product;
	public long x0;
	public long y;
	
	public int l;
	
	public String incorrectMessage="";
	public boolean correct=true;
	public boolean completed=false;
	
	public ChineseReminderTheoremResult(Long[] a, Long[] b, Long[] m) throws MException {
		
		super();
		
		this.a = a;
		this.b = b;
		this.m = m;
		
		l=0;
		for(int k=0;k<a.length;k++) {
			if(a[k]==0l) {
				break;
			}

			l++;

		}
		
		if(l==0) {
			throw new MException("a0 cannot be 0");
		}
		
		if(b.length!=a.length) {
			throw new MException("b size differ from a size");
		}
		if(m.length!=a.length) {
			throw new MException("m size differ from a size");
		}
		
		c=new long[l];
		n=new long[l];
		i=new long[l];
		


	}

	public Long[] getA() {
		return a;
	}

	public Long[] getB() {
		return b;
	}

	public Long[] getM() {
		return m;
	}

	public long[] getC() {
		return c;
	}

	public void setC(long[] c) {
		this.c = c;
	}

	public long[] getN() {
		return n;
	}

	public void setN(long[] n) {
		this.n = n;
	}

	public long[] getI() {
		return i;
	}

	public void setI(long[] i) {
		this.i = i;
	}

	public long getX0() {
		return x0;
	}

	public void setX0(long x0) {
		this.x0 = x0;
	}

	public long getY() {
		return y;
	}

	public void setY(long y) {
		this.y = y;
	}

	public int getL() {
		return l;
	}

	public void setL(int l) {
		this.l = l;
	}

	public String getIncorrectMessage() {
		return incorrectMessage;
	}

	public void setIncorrectMessage(String incorrectMessage) {
		this.incorrectMessage = incorrectMessage;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public long getProduct() {
		return product;
	}

	public void setProduct(long mproduct) {
		product = mproduct;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	
}
