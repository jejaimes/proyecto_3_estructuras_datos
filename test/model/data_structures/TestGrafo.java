package model.data_structures;
import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;

public class TestGrafo extends TestCase{
		
		private Graph<Integer, Integer, Integer> grafo;
		
		@Before
		public void setUp()
		{
			grafo = new Graph<>(100);
		}
		
		@Test
		public void testV()
		{
			assertEquals(0, grafo.V());
		}
		
		@Test
		public void testE()
		{
			assertEquals(0, grafo.E());
		}
		
		@Test
		public void testAddVertex()
		{
			grafo.addVertex(1, 1);
			assertEquals(1,grafo.V());
		}

		@Test
		public void testAddEdge()
		{
			grafo.addVertex(0, 0);
			grafo.addVertex(1, 1);
			grafo.addEdge(1, 0, 1);
			assertEquals(1, grafo.E());
		}

		@Test
		public void testGetInfoVertex()
		{
			grafo.addVertex(1, 1);
			int i = grafo.getInfoVertex(1);
			assertEquals(1, i);
		}

		@Test
		public void testSetInfoVertex()
		{
			grafo.addVertex(1, 1);
			grafo.setInfoVertex(1, 2);
			int i = grafo.getInfoVertex(1);
			assertEquals(2, i);
		}

		@Test
		public void testGetInfoArc()
		{
			grafo.addVertex(0, 0);
			grafo.addVertex(1, 1);
			grafo.addEdge(1, 0, 1);
			int i = grafo.getInfoArc(1, 0);
			assertEquals(1, i);
		}

		@Test
		public void testSetInfoArc()
		{
			grafo.addVertex(0, 0);
			grafo.addVertex(1, 1);
			grafo.addEdge(1, 0, 1);
			grafo.setInfoArc(1, 0, 2);
			int i = grafo.getInfoArc(1, 0);
			assertEquals(2, i);
		}
}
