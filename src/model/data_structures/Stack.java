package model.data_structures;

import java.util.Iterator;

public class Stack<T extends Comparable<T>> implements IStack<T>{

	private ArregloDinamico<T> arreglo;
	private int top;
	
	public Stack(ArregloDinamico<T> ar)
	{
		arreglo = ar;
		top = 0;
	}
	
	public Iterator iterator() {
		return new StackIterator(arreglo);
	}

	public boolean isEmpty() {
		return arreglo.darTamano() == 0;
	}

	public int size() {
		return arreglo.darTamano();
	}

	public T pop() {
		T element = arreglo.darElemento(size() - 1);
		arreglo.eliminarUltimo();
		return element;
	}

	public void push(T t) {
		arreglo.agregar(t);
	}

}
class StackIterator<T extends Comparable<T>> implements Iterator<T>{

	private ArregloDinamico<T> ar;
	private int ac;
	
	public StackIterator(ArregloDinamico<T> t)
	{
		ar = t;
		ac = -1;
	}
	
	public boolean hasNext() {
		if(ar.darElemento(ac+1) != null)
			return true;
		else
			return false;
	}

	public T next() {
		ac++;
		return ar.darElemento(ac);
	}
	
}
