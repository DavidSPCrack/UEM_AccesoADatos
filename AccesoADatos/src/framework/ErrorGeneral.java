package framework;

public class ErrorGeneral extends Exception {

	private static final long serialVersionUID = 1L;

	public ErrorGeneral(String mensaje) {
		super(mensaje);
	}

	public ErrorGeneral(Exception e) {
		super(e);
	}
}