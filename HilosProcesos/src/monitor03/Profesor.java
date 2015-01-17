package monitor03;

public class Profesor extends Thread implements IPersona {

	private Monitor monitor;
	private String nombre;

	public Profesor(String nombre, Monitor monitor) {
		this.nombre = nombre;
		this.monitor = monitor;
		new Thread(this).start();
	}

	public void run() {
		monitor.llegarProfesor(this);
	}

	public int getOrden() {
		return 0;
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
