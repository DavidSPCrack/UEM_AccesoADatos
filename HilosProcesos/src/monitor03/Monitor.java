package monitor03;

public class Monitor {

	private int actual = 0;
	private boolean turnProfesor = false;

	public synchronized void llegar(IPersona persona) {
		while (persona.getOrden() != actual || turnProfesor) {
			try {
				wait();
			} catch (InterruptedException ex) {
			}
		}
		persona.llegar();

		actual = (actual + 1);
		if (actual == 10) {
			actual = 0;
			turnProfesor = true;
		}

		notifyAll();
	}

	public synchronized void saludar(IPersona persona) {
		while (persona.getOrden() != actual || turnProfesor) {
			try {
				wait();
			} catch (InterruptedException ex) {
			}
		}
		persona.saludar();
		actual = (actual + 1);
		if (actual == 10) {
			actual = 0;
			turnProfesor = true;
		}
		notifyAll();
	}

	public synchronized void llegarProfesor(IPersona persona) {
		while (!turnProfesor) {
			try {
				wait();
			} catch (InterruptedException ex) {
			}
		}
		persona.llegar();
		turnProfesor = false;
		actual = 0;
		notifyAll();
	}

}
