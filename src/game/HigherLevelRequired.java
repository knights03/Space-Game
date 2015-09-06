package game;

public class HigherLevelRequired extends Exception {
	
	public HigherLevelRequired(String message) {
		super(message);
	}
	
	public HigherLevelRequired(String message, Throwable throwable) {
		super(message, throwable);
	}
}
