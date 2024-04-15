package model.data_structures;

import java.util.Iterator;

public class TablaHashLP<V, K extends Comparable<K>> implements ITablaHash<V, K> {
	private int N;
	private int M = 100;
	private K[] keys;
	private V[] vals;
	private int rahash;

	@SuppressWarnings("unchecked")
	public TablaHashLP(int m) {
		M = m;
		rahash = 0;
		keys = (K[]) new Comparable[M];
		vals = (V[]) new Object[M];
	}

	private int hash(K key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}

	public void put(K key, V val) {

		if (N / M >= 0.75) {
			rehash();
			rahash++;
		}

		int i;
		for (i = hash(key); keys[i] != null; i = (i + 1) % M)
			if (keys[i].equals(key)) {
				vals[i] = val;
				return;
			}
		keys[i] = key;
		vals[i] = val;
		N++;
	}

	public V get(K key) {
		for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
			if (keys[i].equals(key))
				return vals[i];
		return null;
	}

	public V delete(K key) {
		V valorRetorno = null;
		int i = hash(key);
		while (!key.equals(keys[i]))
			i = (i + 1) % M;

		valorRetorno = vals[i];
		keys[i] = null;
		vals[i] = null;
		N--;
		return valorRetorno;
	}

	public void cambiarV(K key, V val) {
		for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
			if (keys[i].equals(key)) {
				vals[i] = val;
			}
	}

	public Iterator<K> keys() {
		return new IteradorLP(keys);
	}

	public class IteradorLP implements Iterator<K> {
		private K[] key;
		private int contador;

		public IteradorLP(K[] pK) {
			key = pK;
			contador = 0;
		}

		public boolean hasNext() {
			try {
				int pos = contador + 1;
				return key[pos] != null;
			} catch (Exception e) {
				return false;
			}
		}

		public K next() {
			K next = key[contador];
			contador++;
			return next;
		}

	}

	public void rehash() {
		TablaHashLP<V, K> t;
		t = new TablaHashLP<V, K>((int) (M * 1.5));
		for (int i = 0; i < M; i++)
			if (keys[i] != null)
				t.put(keys[i], vals[i]);
		keys = t.keys;
		vals = t.vals;
		M = t.M;

	}

	public int size() {
		return N;
	}

	public int tamano() {
		return M;
	}

	public double factor() {
		return (double) N / (double) M;
	}

	public int darNRehashes() {
		return rahash;
	}
}