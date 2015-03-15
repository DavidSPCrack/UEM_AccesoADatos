package cifrado;

public class Ejemplo1 {
	public static void main(String[] args) {
		// Se introducen las Propiedades del sistema en un array
		String t[] = { "java.class.path", "java.home", "java.vendor", "java.version", "os.name", "os.version", "user.dir", "user.home", "user.name" };
		// Gestor de Seguridad de Java
		// System.setSecurityManager(new SecurityManager());
		for (int i = 0; i < t.length; i++) {
			System.out.print("Propiedad: " + t[i]);
			try {
				// Valor de la Propiedad
				String s = System.getProperty(t[i]);
				System.out.println("\t ==> " + s);
			} catch (Exception e) {
				System.err.println("\n\t Exception " + e.toString());
			}
		}
	}
}