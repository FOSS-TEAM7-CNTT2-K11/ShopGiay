package nhom7.shopgiay.custom;

public class MyException extends Exception{

	private static final long serialVersionUID = 1L;
	private String message;
	
	public MyException(String string) {
		message = string;
	}
	
	public MyException() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getMessage() {
		return message;
	}

}
