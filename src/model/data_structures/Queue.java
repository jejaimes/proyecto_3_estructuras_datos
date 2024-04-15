package model.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<T>  implements IQueue<T> {

	int size;

	NodoLista<T> primero;

	NodoLista<T> ultimo;


	public Queue() {
		primero=null;
		size = 0;
		ultimo = null;
	}



	public Iterator<T> iterator() {
		IteradorLista<T> it = new IteradorLista<T>(primero);
		return it;

	}

	public boolean isEmpty() {

		return primero==null;
	}

	
	public int size() {
		return size;
	}

	
	public void enqueue(T t) {
		NodoLista<T> nuevo = new  NodoLista<T>(t);
		
		if(primero == null)
		{
			primero = nuevo;
			ultimo = primero;
			size++;
		}
		else if (primero !=null)
		{
			ultimo.cambiarSiguiente(nuevo);
			ultimo= nuevo;
			size++;
		}
	}

	public T dequeue() {

		try {
			T el =primero.darElemento();
			primero = primero.darSiguiente();
			size--;
			return el;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}




	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////             CLASE QUE FORMA LOS NODOS PARA LISTA             //////////////////////////    
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public class NodoLista<T> {

		T objeto;
		
		NodoLista<T> siguiente;



		public NodoLista(T object) 
		{
			objeto = object;
			siguiente = null;
		}

		public T darElemento()
		{
			return objeto;
		}
		public NodoLista<T> darSiguiente()
		{
			return siguiente;
		}

		public void	cambiarSiguiente(NodoLista<T> nodo)
		{


			siguiente = nodo;
		}


	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////             CLASE QUE ITERA LA LISTA ENCADENADA	            //////////////////////////    
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public class IteradorLista<T> implements Iterator<T> 
	{
		private NodoLista<T>  prox;

		public IteradorLista(NodoLista<T> first ) 
		{
			prox = first;
		}

		public boolean hasNext() {

			return prox!=null;
		}

		public T next() {

			if ( prox == null )
			{ 
				throw new NoSuchElementException("no hay proximo"); 
			}
			T elemento = prox.darElemento(); 

			prox = prox.darSiguiente(); 

			return elemento;
		}

	}

}
