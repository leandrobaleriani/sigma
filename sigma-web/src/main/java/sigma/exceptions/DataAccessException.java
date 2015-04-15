package sigma.exceptions;

public class DataAccessException extends Exception {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = -6569955421097989240L;

	public DataAccessException(String msg, Throwable th) {
		super(msg, th);
	}

	public DataAccessException(Throwable th) {
		super(th);
	}
}
