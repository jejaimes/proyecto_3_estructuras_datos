package model.utils;

import java.util.Comparator;
import java.util.Random;

public class Sort<T extends Comparable<T>> {

	/**
	 * Ordenar datos aplicando el algoritmo ShellSort
	 * 
	 * @param datos
	 *            - conjunto de datos a ordenar (inicio) y conjunto de datos
	 *            ordenados (final)
	 */
	public static void ordenarShellSort(Comparable[] datos) {
		int N = datos.length;
		int h = 1;
		while (h < N / 3)
			h = 3 * h + 1;
		while (h >= 1) {
			for (int i = h; i < N; i++) {
				for (int j = i; j >= h && less(datos[j], datos[j - h]); j -= h) {
					exchange(datos, j, j - h);
				}
			}
			h = h / 3;
		}
	}

	/**
	 * Ordenar datos aplicando el algoritmo MergeSort
	 * 
	 * @param datos
	 *            - conjunto de datos a ordenar (inicio) y conjunto de datos
	 *            ordenados (final)
	 */
	public static void ordenarMergeSort1(Comparable[] datos) {
		Comparable[] aux = new Comparable[datos.length];
		mergeSort1(datos, aux, 0, datos.length - 1);
	}

	private static void mergeSort1(Comparable[] a, Comparable[] aux, int lo, int hi) {
		if (hi <= lo)
			return;
		int mid = lo + (hi - lo) / 2;
		mergeSort1(a, aux, lo, mid);
		mergeSort1(a, aux, mid + 1, hi);
		merge1(a, aux, lo, mid, hi);
	}

	private static void merge1(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		for (int k = lo; k <= hi; k++)
			aux[k] = a[k];
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid)
				a[k] = aux[j++];
			else if (j > hi)
				a[k] = aux[i++];
			else if (less(aux[j], aux[i]))
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private static Comparator comparadorActual;

	/***
	 * Ordenar datos aplicando el algoritmo MergeSort* @param datos - conjunto
	 * de datos a ordenar (inicio) y conjunto de datos ordenados (final)
	 * * @param hi* @param mid* @param lo* @param aux
	 */

	public static void ordenarMergeSort(Comparable[] datos, Comparable[] aux, int lo, int mid, int hi) {
		for (int k = lo; k <= hi; k++) {
			aux[k] = datos[k];
		}
		int i = lo;
		int j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid)
				datos[k] = aux[j++];
			else if (j > hi)
				datos[k] = aux[i++];
			else if (lessCm(aux[j], aux[i]))
				datos[k] = aux[j++];
			else
				datos[k] = aux[i++];
		}
	}

	private static void sortM(Comparable[] datos, Comparable[] aux, int lo, int hi) {
		if (hi <= lo)
			return;
		int mid = lo + (hi - lo) / 2;
		sortM(datos, aux, lo, mid);
		sortM(datos, aux, mid + 1, hi);
		ordenarMergeSort(datos, aux, lo, mid, hi);
	}

	public static void sortM(Comparable[] datos, Comparator entrada) {
		comparadorActual = entrada;
		Comparable[] aux = new Comparable[datos.length];
		sortM(datos, aux, 0, datos.length - 1);
	}

	/**
	 * Comparar 2 objetos usando la comparacion "natural" de su clase*param v
	 * primer objeto de comparacion
	 * 
	 * @param w
	 *            segundo objeto de comparacion* @return true si v es menor que
	 *            w usando el metodo compareTo. false en caso contrario.
	 */
	private static boolean lessCm(Comparable v, Comparable w) {
		return comparadorActual.compare(v, w) < 0;
	}

	public static void invertirMuestra(Comparable[] datos) {
		Comparable[] muestraInvertida = new Comparable[datos.length];
		int j = datos.length - 1;
		for (int i = 0; i < datos.length - 1; i++) {
			muestraInvertida[i] = datos[j];
			j--;
		}
		datos = muestraInvertida;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Ordenar datos aplicando el algoritmo QuickSort
	 * 
	 * @param datos
	 *            - conjunto de datos a ordenar (inicio) y conjunto de datos
	 *            ordenados (final)
	 */
	public static void ordenarQuickSort(Comparable[] datos) {
		shuffle(datos);
		sort(datos, 0, datos.length - 1);
	}

	private static void shuffle(Comparable[] array) {
		int index;
		Comparable temp;
		for (int i = array.length - 1; i >= 0; i--) {
			index = (int) (Math.random() * array.length);
			temp = array[index];
			array[index] = array[i];
			array[i] = temp;
		}
	}

	private static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo)
			return;
		int j = partition(a, lo, hi);
		sort(a, lo, j - 1);
		sort(a, j + 1, hi);
	}

	private static int partition(Comparable[] a, int lo, int hi) {
		int i = lo;
		int j = hi + 1;
		Comparable v = a[lo];
		while (true) {

			// find item on lo to swap
			while (less(a[++i], v)) {
				if (i == hi)
					break;
			}

			// find item on hi to swap
			while (less(v, a[--j])) {
				if (j == lo)
					break; // redundant since a[lo] acts as sentinel
			}

			// check if pointers cross
			if (i >= j)
				break;

			exchange(a, i, j);
		}

		// put partitioning item v at a[j]
		exchange(a, lo, j);

		// now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
		return j;
	}

	/**
	 * Comparar 2 objetos usando la comparacion "natural" de su clase
	 * 
	 * @param v
	 *            primer objeto de comparacion
	 * @param w
	 *            segundo objeto de comparacion
	 * @return true si v es menor que w usando el metodo compareTo. false en
	 *         caso contrario.
	 */
	@SuppressWarnings("unchecked")
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	/**
	 * Intercambiar los datos de las posicion i y j
	 * 
	 * @param datos
	 *            contenedor de datos
	 * @param i
	 *            posicion del 1er elemento a intercambiar
	 * @param j
	 *            posicion del 2o elemento a intercambiar
	 */
	private static void exchange(Comparable[] datos, int i, int j) {
		Comparable swap = datos[i];
		datos[i] = datos[j];
		datos[j] = swap;
	}

}
