package model.data_structures;

import java.util.Iterator;

public interface ITablaHash<V, K extends Comparable<K>> {

	/**
	 * Agregar una dupla (K, V) a la tabla. Si la llave K existe, se reemplaza
	 * su valor V asociado. V no puede ser null.
	 * 
	 * @param key
	 *            Llave
	 * @param value
	 *            Valor asociado a la llave
	 * @throws Exception
	 */
	void put(K key, V value) throws Exception;

	/**
	 * Obtener el valor V asociado a la llave K. V no puede ser null.
	 * 
	 * @param key
	 *            Llave
	 * @return Valor asociado a la llave
	 */
	V get(K key);

	/**
	 * Borrar la dupla asociada a la llave K. Se obtiene el valor V asociado a
	 * la llave K. Se obtiene null si la llave K no existe.
	 * 
	 * @param key
	 *            Llave
	 * @return Valor asociado a la llave
	 */
	V delete(K key);

	/**
	 * Conjunto de llaves K presentes en la tabla.
	 * 
	 * @return Iterador de llaves
	 */
	Iterator<K> keys();

	/**
	 * Aumenta el tamaño del arreglo y reorganiza los elementos
	 */
	void rehash();
}
