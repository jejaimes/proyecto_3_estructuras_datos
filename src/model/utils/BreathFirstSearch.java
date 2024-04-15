package model.utils;

import java.util.Iterator;

import model.data_structures.Queue;
import edu.princeton.cs.algs4.Stack;
import model.vo.*;

import model.data_structures.*;
public class BreathFirstSearch <K extends Comparable<K>, V, A>{
	private TablaHashLP<Boolean,K> marked;
	private TablaHashLP<K,K> edgeTo;
	private K s;
	
	public BreathFirstSearch  (Graph<Interseccion,Long,Via> G, K s) 
	{
		marked =  new  TablaHashLP<Boolean,K>(G.V()) ;
		edgeTo = new TablaHashLP<K,K>(G.V());
		this.s=s;
		bfs(G, s);
	}

	private void bfs(Graph<Interseccion,Long,Via> G, K s) 
	{
		Queue<K> q = new Queue<K>();
		marked.put(s, true);
		q.enqueue(s);

		while (!q.isEmpty())
		{
			K v = q.dequeue();
			Iterator<Long> i=G.adj((Long) v);
			while(i.hasNext())
			{
				K w=(K)i.next();
				if (marked.get(w)!=null) 
				{
					edgeTo.put(w, v);
					marked.put(w, true);
					q.enqueue(w);
				}
			}
		}
	}

	/**
	 * Is there a path between the source vertex {@code s} (or sources) and vertex {@code v}?
	 * @param v the vertex
	 * @return {@code true} if there is a path, and {@code false} otherwise
	 * @throws IllegalArgumentException unless {@code 0 <= v < V}
	 */
	public boolean hasPathTo(K v) 
	{
		if(v!=null)
		return marked.get(v);
		return false;
	}

	/**
	 * Returns a shortest path between the source vertex {@code s} (or sources)
	 * and {@code v}, or {@code null} if no such path.
	 * @param  v the vertex
	 * @return the sequence of vertices on a shortest path, as an Iterable
	 * @throws IllegalArgumentException unless {@code 0 <= v < V}
	 */
	public ArregloDinamico<K>  pathTo(K v) 
	{
		if (!hasPathTo(v)) return null;
		ArregloDinamico<K> path = new ArregloDinamico<>(100);
		for (K x = v; x!=s ;x = edgeTo.get(v))
		{
			path.agregar(x);
			v=x;
		}
		path.agregar(s);
		return path;
	}
}