package ejercicios.act07;

public interface IADBicicleta {

	/**
	 * Guarda una bicicleta
	 * 
	 * @param bici
	 *            Bicicleta que se va a guardar
	 * @return devuelve true si se ha guardado con exito
	 */
	public boolean guardarBici(Bicicleta bici);

	/**
	 * Modifica una bicicleta
	 * 
	 * @param bici
	 *            Bicicleta que se va a eliminar
	 * @return devuelve true si se ha modificado con exito
	 */
	public boolean modificarBici(Bicicleta bici);

	/**
	 * Elimina una bicicleta
	 * 
	 * @param bici
	 *            Bicicleta que se va a eliminar
	 * @return devuelve true si se ha eliminado con exito
	 */
	public boolean eliminarBici(Bicicleta bici);

	/**
	 * Busca y recupera una bicicleta por un ID
	 * 
	 * @param id
	 *            Id de la bicicleta que se va a recuperar
	 * @return devuelve un objeto bicicleta, devuelve <code>null</code> si no se
	 *         ha guardado bien.
	 */
	public Bicicleta obtenerBici(int id);

	/**
	 * Busca y recupera una bicicleta por un ID
	 * 
	 * @return devuelve todas las bicicletas guardadas
	 */
	public Bicicleta[] obtenerBicis();

}
