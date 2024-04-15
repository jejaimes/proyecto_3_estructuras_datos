package model.vo;

public class Interseccion implements Comparable<Interseccion>
{
	private Long id;
	private double lat;
	private double lon;
	private Long [] adj;
	private Long [] infractions;
	
	private double latitud;
	private double longitud;
	
	public Interseccion(long pId, double pLatitud, double pLongitud)
	{
		id = pId;
		latitud = pLatitud;
		longitud = pLongitud;
	}
	
	public long darId()
	{ return id; }
	
	public double darLatitud()
	{ return latitud; }
	
	public double darLongitud()
	{ return longitud; }


	public Long getId()
	{
		return id;
	}

	public Long[] getInfractions()
	{
		return infractions;
	}

	public double getLat()
	{
		return lat;
	}

	public double getLon()
	{
		return lon;
	}

	public Long[] getAdj()
	{
		return adj;
	}

	public int compareTo(Interseccion o) 
	{
		return (int) (id-o.getId());
	}

	public void agregarInfracciones(Long long1) {
		// TODO Auto-generated method stub
		
	}

}