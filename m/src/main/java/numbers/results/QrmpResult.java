package numbers.results;

import java.util.ArrayList;

public class QrmpResult {

	public long p;
	public ArrayList<Long> qrm = new ArrayList<Long>();
	public ArrayList<Long> consecutives = new ArrayList<Long>();
	
	public long getP() {
		return p;
	}
	public void setP(long p) {
		this.p = p;
	}
	public ArrayList<Long> getQrm() {
		return qrm;
	}
	public void setQrm(ArrayList<Long> qrm) {
		this.qrm = qrm;
	}
	public ArrayList<Long> getConsecutives() {
		return consecutives;
	}
	public void setConsecutives(ArrayList<Long> consecutives) {
		this.consecutives = consecutives;
	}
	
	


}
