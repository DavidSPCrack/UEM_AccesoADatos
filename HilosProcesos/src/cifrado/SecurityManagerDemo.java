package cifrado;

import java.io.FilePermission;
import java.security.AccessControlContext;
import java.security.AccessController;

public class SecurityManagerDemo extends SecurityManager {
	public static void main(String[] args) {
		// Se obtiene el contexto actual de Seguridad
		AccessControlContext con = AccessController.getContext();
		// Se fija el archivo de políticas para las políticas de seguridad del
		// sistema
		System.setProperty("java.security.policy", "file:/C:/java.policy");
		// Se crea el Gestor de Seguridad
		SecurityManagerDemo sm = new SecurityManagerDemo();
		// Se fija el Gestor de Seguridad del sistema
		System.setSecurityManager(sm);
		// Se realiza la prueba
		sm.checkPermission(new FilePermission("test.txt", "read,write"), con);
		// Se muestra un mensaje si se pasa la prueba
		System.out.println("Gestor de Seguridad - Creación y Configuración Permitidas!");
	}
}