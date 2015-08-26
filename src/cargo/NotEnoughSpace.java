package cargo;

public class NotEnoughSpace extends Exception {
	
	public NotEnoughSpace(String message) {
		super(message);
	}
	
	public NotEnoughSpace(String message, Throwable throwable) {
		super(message, throwable);
	}

}
