package com.mill.models;

public class Store {

	long idstores;
	String name;
	String site;
	long country;

	public long getIdstores()
	{
		return idstores;
	}

	public void setIdstores(long idstores)
	{
		this.idstores = idstores;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getSite()
	{
		return site;
	}

	public void setSite(String site)
	{
		this.site = site;
	}

	public long getCountry()
	{
		return country;
	}

	public void setCountry(long country)
	{
		this.country = country;
	}

}
