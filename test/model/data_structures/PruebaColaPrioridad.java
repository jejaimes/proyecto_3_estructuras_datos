package model.data_structures;


import org.junit.Before;
import org.junit.Test;

import controller.Controller;
import junit.framework.TestCase;
import model.data_structures.ArregloDinamico;

public class PruebaColaPrioridad extends TestCase{

	private PriorityQueueHeap<Integer> heap;

	@Before
	public void setUp() throws Exception{

		heap = new PriorityQueueHeap<>(1001);
	}

	@Test
	public  void testAgregar1()
	{
		try {

			for (int i = 0; i < 1000; i++) {

				heap.insert(i);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(1000, heap.darTamano());
		assertEquals(999, (int)heap.darElementoCabeza());
	}


	@Test
	public void testDelMax()
	{
		testAgregar1();
		int x = heap.remove();
		
		assertEquals( 999,x);
	}
}
