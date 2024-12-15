package web;

public class WException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8091513447002627545L;

	public WException(String s) {
		super(s);
	}

	public WException(Throwable targetException) {
		super(targetException);
	}
}
