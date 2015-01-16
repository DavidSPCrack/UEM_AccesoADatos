package framework.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import framework.ErrorGeneral;

/**
 * 
 * @author David
 * 
 * 
 */

public final class Reflexion {

	public Reflexion() {
		super();
	}

	public static Object ejecutarMetodoEstatico(String sourceName, String metodo) throws ErrorGeneral {
		Class<?> invokedClass = null;
		try {
			invokedClass = Class.forName(sourceName);
		} catch (ClassNotFoundException e) {
			throw getError(e, "Clase " + sourceName + " no encontrada");
		}
		Method method = null;
		try {
			method = invokedClass.getMethod(metodo, new Class[0]);
		} catch (SecurityException e1) {
			throw getError(e1, "Excepción de seguridad en  " + invokedClass);
		} catch (NoSuchMethodException e1) {
			throw getError(e1, "No existe mÃ©todo " + metodo + " en " + invokedClass);
		}
		try {
			return method.invoke(null, new Object[0]);
		} catch (IllegalArgumentException e2) {
			throw getError(e2, "Argumento Ilegal al invocar método " + metodo + " en " + invokedClass);
		} catch (IllegalAccessException e2) {
			throw getError(e2, "Acceso Ilegal al invocar método " + metodo + " en " + invokedClass);
		} catch (InvocationTargetException e2) {
			throw getError(e2, "Excepción en destino de invocación para  " + invokedClass);
		}
	}

	public static Object ejecutarMetodoEstatico(String sourceName, String metodo, String argumento) throws ErrorGeneral {
		Class<?> invokedClass = null;
		try {
			invokedClass = Class.forName(sourceName);
		} catch (ClassNotFoundException e) {
			throw getError(e, "Clase " + sourceName + " no encontrada");
		}
		Method method = null;
		Class<?>[] tipoArgs = { String.class };
		try {
			method = invokedClass.getMethod(metodo, tipoArgs);
		} catch (SecurityException e1) {
			throw getError(e1, "Excepción de seguridad en  " + invokedClass);
		} catch (NoSuchMethodException e1) {
			throw getError(e1, "No existe mÃ©todo " + metodo + " en " + invokedClass);
		}
		Object[] args = { argumento };
		try {
			return method.invoke(null, args);
		} catch (IllegalArgumentException e2) {
			throw getError(e2, "Argumento Ilegal al invocar método " + metodo + " en " + invokedClass);
		} catch (IllegalAccessException e2) {
			throw getError(e2, "Acceso Ilegal al invocar método " + metodo + " en " + invokedClass);
		} catch (InvocationTargetException e2) {
			ErrorGeneral eg = new ErrorGeneral("Excepción en destino de invocación para  " + invokedClass);
			throw new ErrorGeneral(eg);
		}
	}

	public static Object ejecutarMetodoEstatico(String sourceName, String metodo, Object[] args) throws ErrorGeneral {
		Class<?> invokedClass = null;
		try {
			invokedClass = Class.forName(sourceName);
		} catch (ClassNotFoundException e) {
			throw getError(e, "Clase " + sourceName + " no encontrada");
		}
		Method method = null;
		Class<?>[] tipoArgs = new Class[args.length];
		for (int i = 0; i < tipoArgs.length; i++) {
			tipoArgs[i] = args[i].getClass();
		}
		try {
			method = invokedClass.getMethod(metodo, tipoArgs);
		} catch (SecurityException e1) {
			throw getError(e1, "Excepción de seguridad en  " + invokedClass);
		} catch (NoSuchMethodException e1) {
			throw getError(e1, "No existe método " + metodo + " en " + invokedClass);
		}
		try {
			return method.invoke(null, args);
		} catch (IllegalArgumentException e2) {
			throw getError(e2, "Argumento Ilegal al invocar método " + metodo + " en " + invokedClass);
		} catch (IllegalAccessException e2) {
			throw getError(e2, "Acceso Ilegal al invocar método " + metodo + " en " + invokedClass);
		} catch (InvocationTargetException e2) {
			ErrorGeneral eg = new ErrorGeneral("Excepción en destino de invocación para  " + invokedClass);
			throw new ErrorGeneral(eg);
		}
	}

	public static Object newInstace(String nameClass, Class<?>[] tiposArgumentos, Object[] argumentos) throws ErrorGeneral {
		Class<?> clase = getClass(nameClass);
		return newInstace(clase, tiposArgumentos, argumentos);
	}

	public static Object newInstace(Class<?> clase, Class<?>[] tiposArgumentos, Object[] argumentos) throws ErrorGeneral {
		Constructor<?> constructor = getConstructor(clase, tiposArgumentos);
		try {
			return constructor.newInstance(argumentos);
		} catch (IllegalArgumentException e) {
			ErrorGeneral eg = new ErrorGeneral( clase.getName());
			throw eg;
		} catch (InstantiationException e) {
			ErrorGeneral eg = new ErrorGeneral(clase.getName());
			throw eg;
		} catch (IllegalAccessException e) {
			ErrorGeneral eg = new ErrorGeneral( clase.getName());
			throw eg;
		} catch (InvocationTargetException e) {
			Throwable th = e.getTargetException();
			if (th instanceof ErrorGeneral) {
				throw (ErrorGeneral) th;
			}
			ErrorGeneral eg = new ErrorGeneral(clase.getName() + th.getMessage());
			throw eg;
		}
	}

	public static Object newInstace(String nameClass) throws ErrorGeneral {
		Class<?>[] tiposArgumentos = new Class[0];
		Object[] argumentos = new Object[0];
		Class<?> clase = getClass(nameClass);
		return newInstace(clase, tiposArgumentos, argumentos);
	}

	public static Object newInstace(Class<?> clase) throws ErrorGeneral {
		Class<?>[] tiposArgumentos = new Class[0];
		Object[] argumentos = new Object[0];
		return newInstace(clase, tiposArgumentos, argumentos);
	}

	public static Class<?> getClass(String nameSource) throws ErrorGeneral {
		Class<?> clase;
		try {
			clase = Class.forName(nameSource);
			return clase;
		} catch (ClassNotFoundException e1) {
			ErrorGeneral eg = new ErrorGeneral( nameSource);
			eg.setStackTrace(e1.getStackTrace());
			throw eg;
		} catch (Throwable e2) {
			ErrorGeneral eg = new ErrorGeneral(nameSource);
			eg.setStackTrace(e2.getStackTrace());
			throw eg;
		}
	}

	private static Constructor<?> getConstructor(Class<?> clase, Class<?>[] tiposArgumentos) throws ErrorGeneral {
		try {
			Constructor<?> constructor = clase.getConstructor(tiposArgumentos);
			return constructor;
		} catch (SecurityException e2) {
			ErrorGeneral eg = new ErrorGeneral(clase.getName());
			eg.setStackTrace(e2.getStackTrace());
			throw eg;
		} catch (NoSuchMethodException e2) {
			ErrorGeneral eg = new ErrorGeneral(clase.getName());
			eg.setStackTrace(e2.getStackTrace());
			throw eg;
		}
	}

	private static ErrorGeneral getError(Exception e, String mensaje) {
		ErrorGeneral eG = new ErrorGeneral(mensaje);
		eG.setStackTrace(e.getStackTrace());
		return eG;
	}
}