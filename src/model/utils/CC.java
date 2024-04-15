package model.utils;
import java.util.Iterator;

import model.data_structures.ArregloDinamico;
import model.data_structures.Graph;
import model.vo.Interseccion;
import model.vo.Via;

/**
 * Adaptacion de https://algs4.cs.princeton.edu/41graph/CC.java.html
 */

public class CC {
    private boolean[] marked;   // marked[v] = has vertex v been marked?
    private Long[] id;           // id[v] = id of connected component containing v
    private int[] size;         // size[id] = number of vertices in given component
    private int count;          // number of connected components

    /**
     * Computes the connected components of the undirected graph {@code G}.
     *
     * @param G the undirected graph
     */
    public CC(Graph<Interseccion,Long,Via> G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        size = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
            	Long l = G.
                dfs(G, v, v);
                count++;
            }
        }
    }

    // depth-first search for a Graph
    private void dfs(Graph<Interseccion,Long,Via> G, Long v, int i) {
        marked[i] = true;
        id[i] = (long) count;
        size[count]++;
        Iterator it = G.adj(v);
        for (int n = 0; it.hasNext(); n++) {
        	Long w = (Long) it.next();
            if (!marked[n]) {
                dfs(G, w, n);
            }
        }
    }

    /**
     * Returns the component id of the connected component containing vertex {@code v}.
     *
     * @param  v the vertex
     * @return the component id of the connected component containing vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int id(int v) {
        return id[v];
    }

    /**
     * Returns the number of vertices in the connected component containing vertex {@code v}.
     *
     * @param  v the vertex
     * @return the number of vertices in the connected component containing vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int size(int v) {
        return size[id[v]];
    }

    /**
     * Returns the number of connected components in the graph {@code G}.
     *
     * @return the number of connected components in the graph {@code G}
     */
    public int count() {
        return count;
    }

    /**
     * Returns true if vertices {@code v} and {@code w} are in the same
     * connected component.
     *
     * @param  v one vertex
     * @param  w the other vertex
     * @return {@code true} if vertices {@code v} and {@code w} are in the same
     *         connected component; {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     * @throws IllegalArgumentException unless {@code 0 <= w < V}
     */
    public boolean connected(Long v, Long w) {
        return id(v) == id(w);
    }
    
    public int[] getID()
    {
    	return id;
    }

}