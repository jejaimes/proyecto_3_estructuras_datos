package model.data_structures;

import java.util.Comparator;

import model.utils.Sort;

/**
 * 2019-01-23 Estructura de Datos Arreglo Dinamico. El arreglo al llenarse
 * (llegar a su maxima capacidad) debe aumentar su capacidad.
 * 
 * @author Fernando De la Rosa
 *
 */
public class ArregloDinamico<T extends Comparable<T>> implements IArregloDinamico<T> {
	/**
	 * Capacidad maxima del arreglo
	 */
	private int tamanoMax;
	/**
	 * Numero de elementos en el arreglo (de forma compacta desde la posicion 0)
	 */
	private int tamanoAct;
	/**
	 * Arreglo de elementos de tamaNo maximo
	 */
	private T[] elementos;

	/**
	 * Construir un arreglo con la capacidad maxima inicial.
	 * 
	 * @param max
	 *            Capacidad maxima inicial
	 */
	public ArregloDinamico(int max) {
		elementos = (T[]) new Comparable[max];
		tamanoMax = max;
		tamanoAct = 0;
	}

	@SuppressWarnings("unchecked")
	public void agregar(T dato) {
		if (tamanoAct == tamanoMax) { // caso de arreglo lleno (aumentar tamaNo)
			tamanoMax = 2 * tamanoMax;
			T[] copia = elementos;
			elementos = (T[]) new Comparable[tamanoMax];
			for (int i = 0; i < tamanoAct; i++) {
				elementos[i] = copia[i];
			}

		}
		elementos[tamanoAct] = dato;
		tamanoAct++;
	}

	public int darTamano() {
		return tamanoAct;
	}

	public T darElemento(int i) {
		if (i >= tamanoAct || i < 0)
			return null;
		return elementos[i];
	}

	public T buscar(T dato) {
		for (int i = 0; i < tamanoAct; i++) {
			T u = elementos[i];
			if (u.compareTo(dato) == 0)
				return u;
		}
		return null;
	}

	public T eliminar(T dato) {
		for (int i = 0; i < tamanoAct; i++) {
			T u = elementos[i];
			if (u.compareTo(dato) == 0) {
				for (int j = i; j < tamanoAct; j++) {
					elementos[j] = elementos[j + 1];
				}
				tamanoAct--;
				return u;
			}
		}
		return null;
	}

	public T[] darArreglo() {
		T[] copy = (T[]) new Comparable[tamanoAct];

		for (int i = 0; i < copy.length; i++) {
			copy[i] = elementos[i];
		}

		return copy;
	}

	public void sort(Comparator y) {
		T[] x = darArreglo();

		Sort.sortM(x, y);
		elementos = x;
	}

	public void eliminarUltimo() {
		if (tamanoAct > 0) {
			elementos[tamanoAct - 1] = null;
			tamanoAct--;
		}
	}

}
