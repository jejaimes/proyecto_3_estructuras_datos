package model.utils;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import main.MVC;
import model.data_structures.ArregloDinamico;
import model.vo.Haversine;
import model.vo.Interseccion;
import model.vo.Via;

public class NodeHandler extends DefaultHandler 
{

	int numeroWays = 0;
	int ndsWays= 0;
	int tagsWays = 0;
	int highWaysTags=0;
	
	ArregloDinamico<Long> verticesWay = new ArregloDinamico<Long>(10);
	Long idWay = null;
	boolean isInWay = false;
	boolean tagHighWay = false;
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException 
	{
		if (qName.equalsIgnoreCase("node")) 
		{
			String sId = attributes.getValue("id");
			long id = Long.parseLong(sId);
			String sLat = attributes.getValue("lat");
			double lat = Double.parseDouble(sLat);
			String sLon = attributes.getValue("lon");
			double lon = Double.parseDouble(sLon);

			MVC.controller.grafoIntersecciones.addVertex(id, new Interseccion(id, lat, lon));
		}
		if(qName.equalsIgnoreCase("way") )
		{
			idWay = Long.parseLong(attributes.getValue("id"));
			numeroWays++;
			isInWay = true;
		}
		if(qName.equalsIgnoreCase("nd") && isInWay)
		{
			verticesWay.agregar(Long.parseLong(attributes.getValue("ref")));
			ndsWays++;
		}
		if(qName.equalsIgnoreCase("tag") && isInWay)
		{
			if(attributes.getValue("k").equalsIgnoreCase("highway"))
			{
				tagHighWay = true;
				highWaysTags++;
			}
			tagsWays++;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException
	{
		if(qName.equalsIgnoreCase("way") &&  isInWay ) {
			if(tagHighWay)
			{
				for(int i = 0; i<verticesWay.darTamano()-1;i++)
				{
					Long idIn = verticesWay.darElemento(i);
					Long idFin = verticesWay.darElemento(i+1);
					Interseccion iInicial = (Interseccion) MVC.controller.grafoIntersecciones.getInfoVertex(idIn);
					Interseccion iFinal = (Interseccion) MVC.controller.grafoIntersecciones.getInfoVertex(idFin);

					double distancia =Haversine.distance(iInicial.darLatitud(), iInicial.darLongitud(), iFinal.darLatitud(), iFinal.darLongitud());
					Via nuevo = new Via(distancia, idWay);	
					MVC.controller.grafoIntersecciones.addEdge(idIn, idFin, nuevo);
				}
			}
			verticesWay = new ArregloDinamico<Long>(10);
			tagHighWay =false;
			isInWay = false;
		}
		

	}

}