package monitor03;

public class Monitor {

	private int actual = 0;
	private int profesor = 0;

	public synchronized void escribir(String texto, int orden) {

	}

	public synchronized void llegar(IPersona persona) {
		while (persona.getOrden() != actual) {
			try {
				wait();
			} catch (InterruptedException ex) {
			}
		}
		persona.llegar();
		
		
		actual = (actual + 1) % 10;
		if(actual == 0) {
			profesor = 1;
		}
		
		notifyAll();
	}

	public synchronized void saludar(IPersona persona) {
		while (persona.getOrden() != actual) {
			try {
				wait();
			} catch (InterruptedException ex) {
			}
		}
		persona.saludar();
		actual = (actual + 1) % 10;
		notifyAll();
	}

	public synchronized void llegarProfesor(IPersona persona) {
		while (profesor == 0) {
			try {
				wait();
			} catch (InterruptedException ex) {
			}
		}
		persona.llegar();
		actual = (actual + 1) % 10;
		notifyAll();
	}

}
