package ship;

public class StatNotPresent extends Exception {
	
	public StatNotPresent(String message) {
		super(message);
	}
	
	public StatNotPresent(String message, Throwable throwable) {
		super(message, throwable);
	}

}
