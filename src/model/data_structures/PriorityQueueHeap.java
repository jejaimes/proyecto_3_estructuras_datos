package model.data_structures;

public class PriorityQueueHeap<T extends Comparable<T>> implements IHeap<T> {

	T[] arreglo;
	int tamano;

	@SuppressWarnings("unchecked")
	public PriorityQueueHeap(int size) {

		arreglo = (T[]) new Comparable[size];
		tamano = 0;
	}

	public void insert(T dato) {
		if (tamano == arreglo.length - 1)
			resize(2 * tamano + 1);
		arreglo[++tamano] = dato;
		swim(tamano);
	}

	public T remove() {
		
		if (isEmpty())
			return null;
		T dato = arreglo[1];
	
		exch(1, tamano--);
	
		arreglo[tamano + 1] = null;
		
		sink(1);
		
		// resize el arreglo
		if (tamano == (arreglo.length - 1) / 4)
			resize((arreglo.length - 1) / 2 + 1);
		return dato;
	}

	public boolean isEmpty() {
		return tamano == 0;
	}

	private void resize(int pTamano) {
		@SuppressWarnings("unchecked")
		T[] copia = (T[]) new Comparable[pTamano];
		for (int i = 1; i <= tamano; i++)
			copia[i] = arreglo[i];
		arreglo = copia;
	}

	private void swim(int k) {
		while (k > 1 && less(k / 2, k)) {
			exch(k / 2, k);
			k = k / 2;
		}
	}

	private void sink(int k) {
		while (2 * k < tamano) {
			int j = 2 * k;
			if (j < tamano && less(j, j + 1))
				j = j + 1;
			if (less(j, k))
				break;
			exch(k, j);
			k = j;
		}
	}

	private boolean less(int i, int j) {
		if (arreglo[i].compareTo(arreglo[j]) < 0)
			return true;
		return false;
	}

	private void exch(int i, int j) {
		T temp = arreglo[i];
		arreglo[i] = arreglo[j];
		arreglo[j] = temp;
	}

	public int darTamano() {
		return tamano;
	}

	public T darElementoCabeza() {
		if (isEmpty())
			return null;
		T dato = arreglo[1];
		return dato;
	}

}