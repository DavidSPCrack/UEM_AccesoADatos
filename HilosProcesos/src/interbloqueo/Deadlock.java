package interbloqueo;

public class Deadlock {
	static class Amigo {
		private final String name;

		public Amigo(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}

		public synchronized void inclinarse(Amigo Quiensaluda) {
			System.out.format("%s: %s" + " me ha saludado inclinándose!%n", this.name, Quiensaluda.getName());
			Quiensaluda.erguirse(this);
		}

		public synchronized void erguirse(Amigo Quiensaluda) {
			System.out.format("%s: %s" + " ha terminado el saludo y se ha erguido !%n", this.name, Quiensaluda.getName());
		}
	}

	public static void main(String[] args) {
		final Amigo alberto = new Amigo("Alberto");
		final Amigo carlos = new Amigo("Carlos");
		new Thread(new Runnable() {
			public void run() {
				alberto.inclinarse(carlos);
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				carlos.inclinarse(alberto);
			}
		}).start();
	}
}
