package numbers.rsa;

import numbers.MException;
import numbers.rsa.alghoritms.RSAJumpArraySkipAlghoritm;
import numbers.rsa.alghoritms.RSAJumpSkipAlghoritm;
import numbers.rsa.alghoritms.RSARhoAlghoritm;
import numbers.rsa.alghoritms.RSASkipAlghoritm;
import numbers.rsa.alghoritms.RSATrialAlghoritm;
import numbers.rsa.alghoritms.RSAWheelAlghoritm;

public class RSAAlghoritmsFactory {
	
	public static final String TRIAL = "TRIAL";
	public static final String SKIP = "SKIP";
	public static final String JUMPSKIP = "JUMPSKIP";
	public static final String JUMPARRAYSKIP = "JUMPARRAYSKIP";
	public static final String WHEEL = "WHEEL";
	public static final String RHO = "RHO";
	private static String _DEFAULT = RHO;

	public static RSAAlghoritm getAlghortirm(String alghoritmLabel) throws MException {

		RSAAlghoritm alghoritm = null;
		
		if(RSAAlghoritmsFactory.TRIAL.equals(alghoritmLabel)) {
			alghoritm=new RSATrialAlghoritm();
		}else if(RSAAlghoritmsFactory.SKIP.equals(alghoritmLabel)) {
			alghoritm=new RSASkipAlghoritm();
		}else if(RSAAlghoritmsFactory.JUMPSKIP.equals(alghoritmLabel)) {
			alghoritm=new RSAJumpSkipAlghoritm();
		}else if(RSAAlghoritmsFactory.JUMPARRAYSKIP.equals(alghoritmLabel)) {
			alghoritm=new RSAJumpArraySkipAlghoritm();
		}else if(RSAAlghoritmsFactory.WHEEL.equals(alghoritmLabel)) {
			alghoritm=new RSAWheelAlghoritm();
		}else if(RSAAlghoritmsFactory.RHO.equals(alghoritmLabel)) {
			alghoritm=new RSARhoAlghoritm();
		}else {
			throw new MException(alghoritmLabel+" unknown alghortim");
		}

		return alghoritm;
	}

	public static RSAAlghoritm getDefaultAlghortirm() throws MException {
		return getAlghortirm(_DEFAULT);
	}
		
	public static RSAAlghoritm getTrialAlghortirm() throws MException {
		return getAlghortirm(TRIAL);
	}

	public static RSAAlghoritm getSkipAlghortirm() throws MException {
		return getAlghortirm(SKIP);
	}

	public static void setDefault(String def) {
		_DEFAULT = def;
	}
	
	public static void setDefaultTrial() {
		setDefault(TRIAL);
	}

	public static void setDefaultSkip() {
		setDefault(SKIP);
	}

	public static void setDefaultJumpSkip() {
		setDefault(JUMPSKIP);
	}

	public static void setDefaultJumpArraySkip() {
		setDefault(JUMPARRAYSKIP);
	}

	public static void setDefaultWheel() {
		setDefault(WHEEL);
	}

	public static void setDefaultRho() {
		setDefault(RHO);
	}

}
