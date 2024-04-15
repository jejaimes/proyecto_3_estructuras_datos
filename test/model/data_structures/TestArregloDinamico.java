package model.data_structures;

import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;

public class TestArregloDinamico extends TestCase{
	
	private ArregloDinamico<Integer> ad;
	
	@Before
	public void setUp()
	{
		ad = new ArregloDinamico<Integer>(10);
		ad.agregar(new Integer(1));
	}
	
	@Test
	public void testAgregar()
	{
		ad.agregar(new Integer(2));
		assertEquals(2, ad.darTamano());
	}
	
	@Test
	public void testDarTamano()
	{
		assertEquals(1, ad.darTamano());
	}
	
	@Test
	public void testDarElemento()
	{
		assertEquals(null, ad.darElemento(5));
		assertEquals(new Integer(1), ad.darElemento(0));
	}
	
	@Test
	public void testBuscar()
	{
		assertEquals(null, ad.buscar(new Integer(9)));
		assertEquals(new Integer(1), ad.buscar(new Integer(1)));
	}
	
	@Test
	public void testEliminar()
	{
		assertEquals(null, ad.eliminar(new Integer(8)));
		assertEquals(new Integer(1), ad.eliminar(new Integer(1)));
		assertEquals(0, ad.darTamano());
	}
}
