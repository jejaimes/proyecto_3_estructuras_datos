package model.data_structures;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class TestTablaHashLP extends TestCase{

	TablaHashLP<String, String> tabla;

	@Before
	public void setUp()
	{
		tabla= new TablaHashLP<String,String>(2000);
		System.out.println("numero parejas: "+tabla.tamano());
		System.out.println("Tamano inicial del arreglo: 2000");
		tabla.put("placax","BCDE234" );
		for (int i = 1; i <10000 ; i++) {
			tabla.put("placa"+i,"ABCD"+i);
		}
		assertEquals(10125,tabla.tamano());
		System.out.println("tamao final del arreglo: "+tabla.tamano());
		System.out.println("Factor de carga: "+ tabla.factor());
		System.out.println("Cantidad rehashes: "+ tabla.darNRehashes());
	}

	public void testGet()
	{
		long startTime = System.nanoTime();
		assertEquals( "BCDE234", tabla.get("placax"));
		long endTime = System.nanoTime() - startTime;
		long promedio = endTime/tabla.size();
		System.out.println("Tiempo promedio: "+ promedio+"ns");
	}


}
