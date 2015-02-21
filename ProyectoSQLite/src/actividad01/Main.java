package actividad01;

public class Main {

	public static void main(String[] args) {

		Model bbdd = new Model();

		bbdd.insertPrediccion("20141110", 4, 20, 0, 1029, "tiempo");
		bbdd.insertPrediccion("20141111", 6, 22, 2, 1031, "tiempo");
		bbdd.insertPrediccion("20141112", 8, 24, 4, 1045, "tiempo");
		System.out.println("");
		bbdd.imprimirPrediciones("tiempo");
		System.out.println("");
		bbdd.borrarTabla("tiempo");
		System.out.println("");
		System.out.println("");

	}

}
