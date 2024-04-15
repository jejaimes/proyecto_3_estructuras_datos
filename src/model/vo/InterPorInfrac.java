package model.vo;

public class InterPorInfrac implements Comparable<InterPorInfrac>
{
	private Long id;
	private double lat;
	private double lon;
	private Long [] adj;
	private Long [] infractions;
	private int numeroid;

	public InterPorInfrac(Long pid,double plat,double plon,Long [] padj,Long [] pinfractions, int pnumeroid)
	{
		id = pid;
		lat = plat;
		lon = plon;
		adj = padj;
		infractions = pinfractions;
		numeroid = pnumeroid;
	}
	
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
	
	public int getTotalInfractions()
	{
		return infractions.length;
	}
	
	public int getNumeroId()
	{
		return numeroid;
	}

	public int compareTo(InterPorInfrac o) 
	{
		return infractions.length - o.infractions.length;
	}

}