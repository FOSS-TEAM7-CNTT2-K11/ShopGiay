package nhom7.shopgiay.custom;

import java.io.Serializable;

public class StatusMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean error;
	private String message;

	public StatusMessage() {}
	
	public StatusMessage(boolean er, String mess) {
		this.error = er;
		this.message = mess;
	}
	
	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
