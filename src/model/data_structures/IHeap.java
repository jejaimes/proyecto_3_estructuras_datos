/**
 * Interface que define los metodos de un heap
 */
package model.data_structures;

/**
 * @author je.hernandezr
 *
 */
public interface IHeap<T extends Comparable<T>> {

	/**
	 * agrega un objeto al heap
	 * 
	 * @param algo
	 */
	void insert(T algo);

	/**
	 * elimina el objeto con mayor prioridad
	 * 
	 * @return el objeto con mayor prioridad
	 */
	T remove();

	/**
	 * Retorna true si la Cola esta vacia
	 * 
	 * @return true si la Cola esta vacia, false de lo contrario
	 */

	boolean isEmpty();

	/**
	 * retorna el tamaño del heap
	 */
	int darTamano();

	/**
	 * retorna la cabeza de la estructura heap
	 * 
	 * @return elemento cabeza
	 */
	T darElementoCabeza();

}
