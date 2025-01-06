package jcook.Exceptions;

public class InvalidRecipeException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidRecipeException(String msg) {
		super(msg);
	}
}
