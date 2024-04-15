package model.data_structures;

import java.util.Iterator;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Graph<V, K extends Comparable<K>, A> {

	private TablaHashLP<Vertice<K, V, A>, K> vertices;
	private int Narcos;

	/**
	 * Crea un grafo No dirigido sin vertices y sin arcos
	 */
	public Graph(int tam) {
		vertices = new TablaHashLP<Vertice<K, V, A>, K>(tam);
		Narcos = 0;
	}

	/** 
	 * Retorna el numero de vertices del grafo
	 * 
	 * @return Numero de vertices
	 */
	public int V() {
		return vertices.size();
	}

	/**
	 * Retorna el numero de arcos del grafo. Cada arco No dirigido debe contarse
	 * una unica vez.
	 * 
	 * @return Numero de arcos
	 */
	public int E() {
		return Narcos;
	}

	/**
	 * Adiciona un vertice con un Id unico. El vertice tiene la informacion
	 * InfoVertex
	 * 
	 * @param idVertex
	 *            id del nuevo vertice. idVertex != null
	 * @param infoVertex
	 *            informacion del nuevo vertice infoVertex != null
	 */
	public void addVertex(K idVertex, V infoVertex) {
		if (vertices.get(idVertex) == null) {
			Vertice<K, V, A> x = new Vertice<K, V, A>(idVertex, infoVertex);
			vertices.put(idVertex, x);
		}
	}

	/**
	 * Adiciona el arco No dirigido entre el vertice IdVertexIni y el vertice
	 * IdVertexFin. El arco tiene la informacion infoArc.
	 * 
	 * @param idVertexIni
	 *            vertice inicial del nuevo arco. idVertexIni != null
	 * @param idVertexFin
	 *            vertice final del nuevo arco. idVertexFin != null
	 * @param infoArc
	 *            informacion del nuevo arco. infoArc != null
	 */

	public void addEdge(K idVertexIni, K idVertexFin, A infoArc) {
		Vertice<K, V, A> v1 = vertices.get(idVertexIni);
		Vertice<K, V, A> v2 = vertices.get(idVertexFin);

		if (v1 != null && v2 != null) {
			Arco<A> ar = new Arco<A>(v1, v2, infoArc);
			v1.agregarAdyacente(ar);
			v2.agregarAdyacente(ar);
			Narcos++;
		}
	}

	/**
	 * Obtener la informacion de un vertice
	 * 
	 * @param idVertex
	 *            id del vertice!= null
	 * @return informacion del vertice. null si no existe un vertice con ese id
	 */
	public V getInfoVertex(K idVertex) {
		Vertice vertex = vertices.get(idVertex);
		if (vertex != null)
			return (V) vertex.darInfo();
		return null;
	}

	/**
	 * Modificar la informacion del vertice idVertex
	 * 
	 * @param idVertex
	 *            id del vertice. idVertex != null
	 * @param infoVertex
	 *            nueva informacion para el vertice. infoVertex != null
	 */
	void setInfoVertex(K idVertex, V infoVertex) {
		if (idVertex != null && infoVertex != null) {
			Vertice vertex = vertices.get(idVertex);
			if (vertex != null)
				vertex.cambiarInfo(infoVertex);
			vertices.cambiarV(idVertex, vertex);
		}
	}

	/**
	 * Obtener la informacion de un arco
	 * 
	 * @param idVertexIni
	 *            id del vertice inicial. idVertexIni != null
	 * @param idVertexFin
	 *            id del vertice final. idVertexFin!= null
	 * @return informacion del arco. null si no se encontro un arco que conecte
	 *         ambos vertices
	 */
	public A getInfoArc(K idVertexIni, K idVertexFin) {
		Vertice<K, V, A> v1 = vertices.get(idVertexIni);
		Vertice<K, V, A> v2 = vertices.get(idVertexFin);
		Arco<A> arco = new Arco<A>(v1, v2, null);
		arco = v1.buscarEdge(arco);
		if (arco != null)
			return (A) (arco.darInfo());
		return null;
	}

	/**
	 * Modificar la informacion del arco entre los vertices idVertexIni e
	 * idVertexFin
	 * 
	 * @param idVertexIni
	 *            id del vertice inicial. idVertexIni!= null
	 * @param idVertexFin
	 *            id del vertice final. idVertexFin!= null
	 * @param infoArc
	 *            nueva informacion del arco. infoArc!= null
	 */
	public void setInfoArc(K idVertexIni, K idVertexFin, A infoArc) {
		Vertice<K, V, A> v1 = vertices.get(idVertexIni);
		Vertice<K, V, A> v2 = vertices.get(idVertexFin);
		Arco<A> arco = new Arco<A>(v1, v2, null);
		arco = v1.buscarEdge(arco);
		if (arco != null)
			arco.setInfo(infoArc);
	}

	public Vertice darVertice(K pos) {
		return vertices.get(pos);
	}
	
	

	/**
	 * Retorna los identificadores de los vertices adyacentes a idVertex
	 * 
	 * @param idVertex
	 *            id del vertice. idVertex!= null
	 * @return Iterator con los id de los vertices adyacentes al parametro. null
	 *         si no se envuentra un vertice con el id
	 */
	public Iterator<K> adj(K idVertex) {
		Vertice v = vertices.get(idVertex);
		if (v != null)
			return new IteradorLista<K>(v);
		else
			return null;
	}

	public Iterator<K> vertices() {
		return vertices.keys();
	}
	
	public TablaHashLP<Vertice<K, V, A>, K> getTabla()
	{
		return vertices;
	}

	@SuppressWarnings("hiding")
	public class IteradorLista<K extends Comparable<K>> implements Iterator<K> {
		private ArregloDinamico<Arco<A>> prox;
		private Vertice vertice;

		public IteradorLista(Vertice first) {
			prox = first.darAdyacentes();
			vertice = first;
		}

		public boolean hasNext() {
			if(prox!=null)
			return prox.darTamano() > 0;
			return false;
		}

		public K next() {
			K elemento = null;
			if (prox.darElemento(prox.darTamano() - 1).darExt1().equals(vertice)) {
				K k = (K) ((prox.darElemento(prox.darTamano() - 1).darExt2()).darId());
				elemento = k;
			}

			prox.eliminarUltimo();
			return elemento;
		}
	}
	
	

	public static class Vertice<K extends Comparable<K>, V, A> {

		private K id;
		private V info;
		private Boolean marcado;
		private K edgeTo;
		private ArregloDinamico<Arco<A>> adyacentes;

		public Vertice(K idVertex, V infoVertex) {
			marcado = false;
			id = idVertex;
			info = infoVertex;
			adyacentes = new ArregloDinamico<Arco<A>>(15);
		}

		
		public K darId() {
			return id;
		}

		public void setEdgeTo(K pEdgeTo) {
			edgeTo = pEdgeTo;
		}

		public K darEdgeTo() {
			return edgeTo;
		}

		public V darInfo() {
			return info;
		}

		public void cambiarInfo(V infoVertex) {
			info = infoVertex;
		}

		public boolean esMarcado() {
			return marcado;
		}

		public void marcar() {
			marcado = true;
		}

		public void desMarcar() {
			marcado = false;
		}

		public void agregarAdyacente(Arco<A> arco) {
			adyacentes.agregar(arco);
		}

		public Arco<A> buscarEdge(Arco<A> inf) {
			return adyacentes.buscar(inf);
		}

		public ArregloDinamico<Arco<A>> darAdyacentes() {
			return adyacentes;
		}
	}
	
	
	public  static class Arco<A> implements Comparable<Arco<A>> {

		private A info;
		private Vertice ext1;
		private Vertice ext2;

		public Arco(Vertice vert1, Vertice vert2, A inf) {
			ext1 = vert1;
			ext2 = vert2;
			info = inf;
		}

		public A darInfo() {
			return info;
		}

		public Vertice darExt1() {
			return ext1;
		}

		public Vertice darExt2() {
			return ext2;
		}

		public void setInfo(A inf) {
			info = inf;
		}

		public int compareTo(Arco o) {

			if (o.darExt1().equals(ext1) && o.darExt2().equals(ext2))
				return 0;
			if (o.darExt1().equals(ext2) && o.darExt2().equals(ext1))
				return 0;
			if (o.darExt1().equals(ext1) && !(o.darExt2().equals(ext2)))
				return -1;
			return 1;
		}
	}


	


}