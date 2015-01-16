package monitor03;

public class Alumno implements Runnable, IPersona {

	private Monitor monitor;
	private String nombre;
	private int orden;

	public Alumno(String nombre, int orden, Monitor monitor) {
		this.nombre = nombre;
		this.monitor = monitor;
		this.orden = orden;
		new Thread(this).start();
	}

	public void run() {
		monitor.llegar(this);
		monitor.saludar(this);
	}

	public int getOrden() {
		return orden;
	}

	public void llegar() {
		StringBuilder sb = new StringBuilder();
		sb.append(nombre);
		sb.append(" llegó.");
		System.out.println(sb.toString());
	}

	public void saludar() {
		StringBuilder sb = new StringBuilder();
		sb.append("Buenos días, profesor. Soy ");
		sb.append(nombre);
		System.out.println(sb.toString());
	}

}
