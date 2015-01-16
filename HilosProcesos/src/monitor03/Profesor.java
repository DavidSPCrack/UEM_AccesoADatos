package monitor03;

public class Profesor extends Thread {

	private Monitor monitor;
	private String nombre;
	private int orden;

	public Profesor(String nombre, int orden, Monitor monitor) {
		this.nombre = nombre;
		this.monitor = monitor;
		this.orden = orden;
	}

	public void run() {
		// monitor.llegar(this);
		// monitor.saludar(this);
	}

	public int getOrden() {
		return orden;
	}

	public void llegar() {
		StringBuilder sb = new StringBuilder();
		sb.append(nombre);
		sb.append(" llegó.");
		System.out.println(sb.toString());
		saludar();
	}

	public void saludar() {
		StringBuilder sb = new StringBuilder();
		sb.append("Buenos días a todos. Soy el Profesor ");
		sb.append(nombre);
		System.out.println(sb.toString());
	}

}
