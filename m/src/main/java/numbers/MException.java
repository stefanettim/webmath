package numbers;
public class MException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8091513447002627545L;

	public MException(String s) {
		super(s);
	}

	public MException(Throwable targetException) {
		super(targetException);
	}
}
