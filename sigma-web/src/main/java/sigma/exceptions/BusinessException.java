package sigma.exceptions;

public class BusinessException extends Exception {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 963309402209175448L;

	public enum TypeError {
		MENSAJE, GENERAL
	};

	private TypeError typeError = null;

	public BusinessException(String msg, Throwable th, TypeError typeError) {
		super(msg, th);
		this.typeError = typeError;
	}
	
	public BusinessException(String msg, TypeError typeError) {
		super(msg);
		this.typeError = typeError;
	}

	public BusinessException(String msg, Throwable th) {
		super(msg, th);
	}

	public BusinessException(Throwable th) {
		super(th);
	}

	public TypeError getTypeError() {
		return typeError;
	}

}
