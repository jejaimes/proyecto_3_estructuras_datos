package controller;

import java.io.BufferedReader;
import java.io.FileReader;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;


import java.util.Iterator;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.google.gson.Gson;
import com.teamdev.jxmaps.Distance;
import com.teamdev.jxmaps.LatLng;


import edu.princeton.cs.algs4.Stack;
import model.utils.BreathFirstSearch;

import model.data_structures.Graph.Arco;
import model.data_structures.ArregloDinamico;
import model.data_structures.Graph;
import model.data_structures.Graph.Vertice;


import model.utils.CC;

import model.data_structures.ArregloDinamico;
import model.data_structures.Graph;
import model.data_structures.PriorityQueueHeap;
import model.data_structures.TablaHashLP;
import model.data_structures.Graph.Vertice;

import view.MovingViolationsManagerView;

import model.vo.*;

public class Controller {

	// Componente vista (consola)
	private MovingViolationsManagerView view;

	//TODO Definir los atributos de estructuras de datos del modelo del mundo del proyecto
	public Graph<Interseccion,Long,Via> grafo;
	private ArregloDinamico<Long> vertices;

	private ArregloDinamico<Long>[] sectores;

	private Graph<Interseccion, Long, Via> grafoMapaGeneral;

	private PriorityQueueHeap<InterPorInfrac> heap;


	public Graph grafoIntersecciones;
	public final static double LONMIN=-77.0542;
	public final static double LONMAX=-76.9976;
	public final static double LATMIN=38.8847;
	public final static double LATMAX=38.9135;

	/**
	 * Metodo constructor
	 */
	public Controller()
	{
		view = new MovingViolationsManagerView();
		grafo= new Graph<>(5000000);
		vertices=new ArregloDinamico<Long>(746000);

	}

	/**
	 * Metodo encargado de ejecutar los  requerimientos segun la opcion indicada por el usuario
	 */
	public void run(){

		long startTime;
		long endTime;
		long duration;

		Scanner sc = new Scanner(System.in);
		boolean fin = false;


		while(!fin){
			view.printMenu();

			int option = sc.nextInt();
			long idVertice1 = 0;
			long idVertice2 = 0;


			switch(option){

			case 0:
				String RutaArchivo = "";
				view.printMessage("Escoger el grafo a cargar: (1) Downtown  o (2)Ciudad Completa.");
				int ruta = sc.nextInt();
				if(ruta == 1)
					RutaArchivo = "./data/archivoJSONDowntown.json"; //TODO Dar la ruta del archivo de Downtown
				else
					RutaArchivo = "./data/finalGraph.json"; //TODO Dar la ruta del archivo de la ciudad completa

				startTime = System.currentTimeMillis();
				loadJSON(RutaArchivo);
				endTime = System.currentTimeMillis();
				duration = endTime - startTime;
				view.printMessage("Tiempo del requerimiento: " + duration + " milisegundos");
				// TODO Informar el total de vértices y el total de arcos que definen el grafo cargado
				break;



			case 1:

				view.printMessage("Ingrese El id del primer vertice (Ej. 901839): ");
				idVertice1 = sc.nextInt();
				view.printMessage("Ingrese El id del segundo vertice (Ej. 901839): ");
				idVertice2 = sc.nextInt();


				startTime = System.currentTimeMillis();
				caminoCostoMinimoA1(idVertice1, idVertice2);
				endTime = System.currentTimeMillis();
				duration = endTime - startTime;
				view.printMessage("Tiempo del requerimiento: " + duration + " milisegundos");
				/* 
<<<<<<< HEAD
				TODO Consola: Mostrar el camino a seguir con sus vÃ©rtices (Id, Ubicación Geográfica),
				el costo mínimo (menor cantidad de infracciones), y la distancia estimada (en Km).

=======
>>>>>>> a87e8e39a3b91dcf15405fd616e0db8c03fe0820
				TODO Google Maps: Mostrar el camino resultante en Google Maps 
				(incluyendo la ubicación de inicio y la ubicación de destino).
				 */
				break;

			case 2:

				view.printMessage("2A. Consultar los N vertices con mayor numero de infracciones. Ingrese el valor de N: ");

				view.printMessage("2A. Consultar los N vértices con mayor número de infracciones. Ingrese el valor de N: ");

				int n = sc.nextInt();


				startTime = System.currentTimeMillis();
				mayorNumeroVerticesA2(n);
				endTime = System.currentTimeMillis();
				duration = endTime - startTime;
				view.printMessage("Tiempo del requerimiento: " + duration + "milisegundos");
				/* 
				TODO Consola: Mostrar la informacion de los n vertices 
				(su identificador, su ubicación (latitud, longitud), y el total de infracciones) 
				Mostra el número de componentes conectadas (subgrafos) y los  identificadores de sus vertices 

				TODO Google Maps: Marcar la localización de los vértices resultantes en un mapa en
				Google Maps usando un color 1. Destacar la componente conectada más grande (con
				más vértices) usando un color 2. 
				 */
				break;

			case 3:			

				view.printMessage("Ingrese El id del primer vertice (Ej. 901839): ");
				idVertice1 = sc.nextInt();
				view.printMessage("Ingrese El id del segundo vertice (Ej. 901839): ");
				idVertice2 = sc.nextInt();


				startTime = System.currentTimeMillis();
				caminoLongitudMinimoaB1(idVertice1, idVertice2);
				endTime = System.currentTimeMillis();
				duration = endTime - startTime;
				view.printMessage("Tiempo del requerimiento: " + duration + " milisegundos");

				/*
				   TODO Consola: Mostrar  el camino a seguir, informando

					el total de vÃ©rtices, sus vertices (Id, Ubicación Geográfica) y la distancia estimada (en Km).

					el total de vértices, sus vértices (Id, Ubicación Geográfica) y la distancia estimada (en Km).


				   TODO Google Maps: Mostre el camino resultante en Google Maps (incluyendo la
					ubicación de inicio y la ubicación de destino).
				 */
				break;

			case 4:		
				double lonMin;
				double lonMax;
				view.printMessage("Ingrese la longitud mínima (Ej. -87,806): ");
				lonMin = sc.nextDouble();
				view.printMessage("Ingrese la longitud máxima (Ej. -87,806): ");
				lonMax = sc.nextDouble();

				view.printMessage("Ingrese la latitud mínima (Ej. 44,806): ");
				double latMin = sc.nextDouble();
				view.printMessage("Ingrese la latitud máxima (Ej. 44,806): ");
				double latMax = sc.nextDouble();

				view.printMessage("Ingrese el número de columnas");
				int columnas = sc.nextInt();
				view.printMessage("Ingrese el número de filas");
				int filas = sc.nextInt();


				startTime = System.currentTimeMillis();
				ArregloDinamico<Interseccion> arr =definirCuadriculaB2(lonMin,lonMax,latMin,latMax,columnas,filas);
				final view.Mapa example = new view.Mapa("Washington");

				for(int k=0;k < arr.darTamano();k++)
				{
					example.generateMarker(new LatLng(arr.darElemento(k).getLat(),arr.darElemento(k).getLon()));
				}
				endTime = System.currentTimeMillis();
				duration = endTime - startTime;
				view.printMessage("Tiempo del requerimiento: " + duration + " milisegundos");
				/*
				   TODO Consola: Mostrar el número de vértices en el grafo
					resultado de la aproximación. Mostar el identificador y la ubicación geográfica de cada
					uno de estos vértices. 

				   TODO Google Maps: Marcar las ubicaciones de los vértices resultantes de la
					aproximación de la cuadrícula en Google Maps.
				 */
				break;

			case 5:

				startTime = System.currentTimeMillis();
				arbolMSTKruskalC1();
				endTime = System.currentTimeMillis();
				duration = endTime - startTime;
				view.printMessage("Tiempo del requerimiento: " + duration + " milisegundos");
				/*
				   TODO Consola: Mostrar los vértices (identificadores), los arcos incluidos (Id vértice inicial e Id vértice
					final), y el costo total (distancia en Km) del Árbol.

				   TODO Google Maps: Mostrar el Árbol generado resultante en Google Maps: sus vértices y sus arcos.
				 */

				break;

			case 6:

				startTime = System.currentTimeMillis();
				arbolMSTPrimC2();
				endTime = System.currentTimeMillis();
				duration = endTime - startTime;
				view.printMessage("Tiempo del requerimiento: " + duration + " milisegundos");
				/*
				   TODO Consola: Mostrar los vértices (identificadores), los arcos incluidos (Id vértice inicial e Id vértice
				 	final), y el costo total (distancia en Km) del Árbol.

				   TODO Google Maps: Mostrar el Árbol generado resultante en Google Maps: sus vértices y sus arcos.
				 */
				break;

			case 7:

				startTime = System.currentTimeMillis();
				caminoCostoMinimoDijkstraC3();
				endTime = System.currentTimeMillis();
				duration = endTime - startTime;
				view.printMessage("Tiempo del requerimiento: " + duration + " milisegundos");
				/*
				   TODO Consola: Mostrar de cada camino resultante: su secuencia de vértices (identificadores) y su costo (distancia en Km).

				   TODO Google Maps: Mostrar los caminos de costo mínimo en Google Maps: sus vértices
					y sus arcos. Destaque el camino más largo (en distancia) usando un color diferente
				 */
				break;

			case 8:
				view.printMessage("Ingrese El id del primer vertice (Ej. 901839): ");
				idVertice1 = sc.nextInt();
				view.printMessage("Ingrese El id del segundo vertice (Ej. 901839): ");
				idVertice2 = sc.nextInt();

				startTime = System.currentTimeMillis();
				caminoMasCortoC4(idVertice1, idVertice2);
				endTime = System.currentTimeMillis();
				duration = endTime - startTime;
				view.printMessage("Tiempo del requerimiento: " + duration + " milisegundos");
				/*
				   TODO Consola: Mostrar del camino resultante: su secuencia de vértices (identificadores), 
				   el total de infracciones y la distancia calculada (en Km).

				   TODO Google Maps: Mostrar  el camino resultante en Google Maps: sus vértices y sus arcos.	  */
				break;

			case 9: 	
				fin = true;
				sc.close();
				break;
			}
		}
	}


	// TODO El tipo de retorno de los métodos puede ajustarse según la conveniencia


	/**
	 * Cargar el Grafo No Dirigido de la malla vial: Downtown o Ciudad Completa
	 * @param rutaArchivo ruta del archivo json a cargar
	 */

	public void loadJSON(String rutaArchivo) 
	{
		try
		{
			Gson gson=new Gson();
			BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
			Interseccion[] actual =  gson.fromJson(br, Interseccion[].class);
			for (int i = 0; i < actual.length; i++) 
			{
				grafo.addVertex(actual[i].getId(),actual[i]);
				vertices.agregar(actual[i].getId());
			}
			crearArcos();
			crearHeap();
			view.printMessage("Se cargaron: "+grafo.E()+" arcos y "+grafo.V()+" vertices" );	
		}

		catch(Exception e)
		{ 
			e.printStackTrace(); 
		}

	}
	public void crearArcos()
	{
		//		final Mapa example = new Mapa("Washington");
		for (int i =0;i<vertices.darTamano();i++)
		{
			Interseccion vActual=grafo.getInfoVertex(vertices.darElemento(i));
			for (Long adyacente : vActual.getAdj()) 
			{
				if(grafo.getInfoArc(vActual.getId(), adyacente)==null)
				{
					Interseccion I1=grafo.getInfoVertex(vActual.getId());
					Interseccion I2=grafo.getInfoVertex(adyacente);
					grafo.addEdge(vActual.getId(),adyacente,new Via( ( Haversine.distance (I1.getLat(),I1.getLon(),I2.getLat(),I2.getLon()) ) ,vActual.getId()));
					//					example.generateSimplePath(new LatLng(I1.getLat(),I1.getLon()), new LatLng(I2.getLat(),I2.getLon()), false);
				}
			}
		}
	}




	// TODO El tipo de retorno de los métodos puede ajustarse según la conveniencia
	/**
	 * Requerimiento 1A: Encontrar el camino de costo mínimo para un viaje entre dos ubicaciones geográficas.
	 * @param idVertice2: vertice final
	 * @param idVertice1: vertice inicial
	 */
	public void caminoCostoMinimoA1(long idVertice1, long idVertice2) {
		// TODO Auto-generated method stub
	}

	// TODO El tipo de retorno de los métodos puede ajustarse según la conveniencia
	/**
	 * Requerimiento 2A: Determinar los n vértices con mayor número de infracciones. Adicionalmente identificar las
	 * componentes conectadas (subgrafos) que se definan únicamente entre estos n vértices
	 * @param  int n: numero de vertices con mayor numero de infracciones  
	 */
	public void mayorNumeroVerticesA2(int n) {
		InterPorInfrac[] arreglo = new InterPorInfrac[n];
		//		CC cc = new CC(grafo);
		ArregloDinamico<Integer> id = new ArregloDinamico<Integer>(n);
		ArregloDinamico<InterPorInfrac> inf = new ArregloDinamico<>(n);
		for(int i = 0; i < n; i++)
		{
			InterPorInfrac inter = heap.remove();
			arreglo[i] = inter;
			System.out.println("id = "+ inter.getId() +"latitud = "+ inter.getLat() +"longitud = "+ inter.getLon() +"totalInfracciones = " + inter.getTotalInfractions());}
		//			if(id.darTamano() > 0) {
		//			for(int j = 0; j < id.darTamano(); j++)
		//			{
		//				if(!cc.connected(id.buscar(j), inter.getId()))
		//				{
		//					id.agregar(inter.getId());
		//					inf.agregar(inter);
		//				}
		//			}
		//			}
		//			else
		//			{
		//				id.agregar(inter.getId());
		//				inf.agregar(inter);
		//			}
		//		}
		//		System.out.println("Componentes conectadas = " + id.darTamano());
		//		for(int i = 0; i < id.darTamano(); i++)
		//		{
		//			int color = id.eliminar(i);
		//			Long conectado = inf.eliminar(inf.darElemento(i)).getId();
		//			System.out.println("Componente " + i);
		//			for(int j = 0; j < vertices.darTamano(); j++)
		//			{
		//				if(cc.connected(conectado, vertices.darElemento(j)))
		//					System.out.println(vertices.darElemento(j));
		//			}
		//		}
	}

	private void crearHeap()
	{
		TablaHashLP<Vertice<Long, Interseccion, Via>, Long> th = grafo.getTabla();
		Iterator it = th.keys();
		for(int i = 0; it.hasNext(); i++)
		{
			Long id = (Long) it.next();
			Interseccion actual = th.get(id).darInfo();
			InterPorInfrac inter = new InterPorInfrac(id, actual.getLat(), actual.getLon(), actual.getAdj(), actual.getInfractions(),i);
			heap.insert(inter);
		}
	}

	// TODO El tipo de retorno de los métodos puede ajustarse según la conveniencia
	/**
	 * Requerimiento 1B: Encontrar el camino más corto para un viaje entre dos ubicaciones geográficas 
	 * @param idVertice2 
	 * @param idVertice1 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void caminoLongitudMinimoaB1(long idVertice1, long idVertice2)
	{
		// TODO Auto-generated method stub
		BreathFirstSearch bfs=new BreathFirstSearch(grafo,idVertice1);
		ArregloDinamico<Long> pila=  bfs.pathTo(idVertice2);

		if(pila!=null){
			view.printMessage("Se obtuvieron: "+pila.darTamano()+" vértices");
			double distEst=0.0;

			for(int i=0;i<pila.darTamano()-1;i++)
			{
				Interseccion actual=grafo.getInfoVertex(pila.darElemento(i));
				Interseccion siguiente=grafo.getInfoVertex(pila.darElemento(i+1));
				view.printMessage("Id: "+actual.getId()+" Ubicación geográfica ("+actual.getLon()+","+actual.getLat()+")");
				distEst+=Haversine.distance(actual.getLat(), actual.getLon(), siguiente.getLat(), siguiente.getLon());

			}
			view.printMessage("Distancia: "+distEst);
		}
	}

	// TODO El tipo de retorno de los métodos puede ajustarse según la conveniencia
	/**
	 * Requerimiento 2B:  Definir una cuadricula regular de N columnas por M filas. que incluya las longitudes y latitudes dadas
	 * @param  lonMin: Longitud minima presente dentro de la cuadricula
	 * @param  lonMax: Longitud maxima presente dentro de la cuadricula
	 * @param  latMin: Latitud minima presente dentro de la cuadricula
	 * @param  latMax: Latitud maxima presente dentro de la cuadricula
	 * @param  columnas: Numero de columnas de la cuadricula
	 * @param  filas: Numero de filas de la cuadricula
	 */
	public ArregloDinamico<Interseccion> definirCuadriculaB2(double lonMin, double lonMax, double latMin, double latMax, int columnas, int filas)
	{
		ArregloDinamico<Interseccion> minimos=new ArregloDinamico<Interseccion>(filas*columnas);
		for(double i= lonMin;i<lonMax;i+=(lonMax-lonMin)/filas)
		{
			for(double j=latMin;j<latMax;j+=(latMax-latMin)/columnas)
			{
				double minDist=Long.MAX_VALUE;
				Interseccion verMin=new Interseccion(0,0,0);

				for (int k =0;k<vertices.darTamano();k++)
				{
					Interseccion vActual=grafo.getInfoVertex(vertices.darElemento(k));
					if(Haversine.distance(j, i, vActual.getLat(), vActual.getLon())<minDist)
					{
						minDist=Haversine.distance(j, i, vActual.getLat(), vActual.getLon());
						verMin=vActual;
					}
				}
				minimos.agregar(verMin);
			}
		}
		return minimos;
	}

	// TODO El tipo de retorno de los métodos puede ajustarse según la conveniencia
	/**
	 * Requerimiento 1C:  Calcular un árbol de expansión mínima (MST) con criterio distancia, utilizando el algoritmo de Kruskal.
	 */
	public void arbolMSTKruskalC1() {
		// TODO Auto-generated method stub

	}

	// TODO El tipo de retorno de los métodos puede ajustarse según la conveniencia
	/**
	 * Requerimiento 2C: Calcular un árbol de expansión mínima (MST) con criterio distancia, utilizando el algoritmo de Prim. (REQ 2C)
	 */
	public void arbolMSTPrimC2() {
		// TODO Auto-generated method stub

	}

	// TODO El tipo de retorno de los métodos puede ajustarse según la conveniencia
	/**
	 * Requerimiento 3C: Calcular los caminos de costo mínimo con criterio distancia que conecten los vértices resultado
	 * de la aproximación de las ubicaciones de la cuadricula N x M encontrados en el punto 5.
	 */
	public void caminoCostoMinimoDijkstraC3() {
		// TODO Auto-generated method stub

	}

	// TODO El tipo de retorno de los métodos puede ajustarse según la conveniencia
	/**
	 * Requerimiento 4C:Encontrar el camino más corto para un viaje entre dos ubicaciones geográficas escogidas aleatoriamente al interior del grafo.
	 * @param idVertice2 
	 * @param idVertice1 
	 */
	public void caminoMasCortoC4(long idVertice1, long idVertice2) {
		// TODO Auto-generated method stub

	}
	public int cargarInformacionMeses( String pRuta , char delimitador, boolean comasLocas)
	{
		int contador = 0;
		try
		{
			CSVFormat format = CSVFormat.newFormat(delimitador).withHeader();
			Reader reader = Files.newBufferedReader(Paths.get(pRuta));
			CSVParser csvParser = new CSVParser(reader, format);
			for (CSVRecord csvRecord : csvParser) 
			{
				String OBJECTID = csvRecord.get("OBJECTID");
				String LONG = comasLocas  ? csvRecord.get("LONG") :  csvRecord.get("LONG").replaceAll(",", "");
				String LAT = comasLocas  ? csvRecord.get("LAT") :  csvRecord.get("LAT").replaceAll(",", "");
				VOMovingViolations infracciones = new VOMovingViolations(Integer.parseInt(OBJECTID),  Double.parseDouble(LAT), Double.parseDouble(LONG));
				contador++;

				int sector = darSector(infracciones.getYCoord(), infracciones.getXCoord());
				ArregloDinamico<Long> llaves = sectores[sector];

				Interseccion menor = grafoMapaGeneral.getInfoVertex(llaves.darElemento(0));
				double distanciaMenor = Haversine.distance(infracciones.getYCoord(), infracciones.getXCoord(), menor.getLat(), menor.getLon());
				for (int j = 0; j<llaves.darTamano() ; j++)
				{
					Interseccion actual = grafoMapaGeneral.getInfoVertex(llaves.darElemento(j));
					double distancia = Haversine.distance(infracciones.getYCoord(), infracciones.getXCoord(), actual.getLat(), actual.getLon());
					if(distancia<distanciaMenor)
					{
						distanciaMenor = distancia;
						menor = actual;
					}

				}
				menor.agregarInfracciones(new Long(infracciones.objectId()));

			}
		}
		catch(Exception e){}
		return contador;


	}


	public int darSector(double pLat, double pLon)
	{
		//Min  Lat:38.7916214 Lon: -77.041409
		//Max  Lat:38.9869984 Lon: -77.0221222

		if(pLat<38.905591316 && pLon < -77.0349800667 )
			return 0;
		else if( pLat < 38.921872733 && pLon < -77.0349800667)
			return 1;
		else if(pLat < 38.9869984 && pLon < -77.0349800667)
			return 2;
		else if(pLat < 38.905591316  && pLon < -77.0285511333)
			return 3;
		else if(pLat < 38.921872733 && pLon < -77.0285511333)
			return 4;
		else if (pLat < 38.9869984 && pLon < -77.0285511333)
			return 5;
		else if(pLat < 38.905591316 && pLon <-77.0221222)
			return 6;
		else if(pLat < 38.921872733 && pLon < -77.0221222)
			return 7;
		else 	
			return 8;
	}


}