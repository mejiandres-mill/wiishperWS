package com.mill.models;

public class Product {

	long idproducts;
	String name;
	String description;
	String price;
	boolean show;
	long store;

	public long getIdproducts()
	{
		return idproducts;
	}

	public void setIdproducts(long idproducts)
	{
		this.idproducts = idproducts;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getPrice()
	{
		return price;
	}

	public void setPrice(String price)
	{
		this.price = price;
	}

	public boolean isShow()
	{
		return show;
	}

	public void setShow(boolean show)
	{
		this.show = show;
	}

	public long getStore()
	{
		return store;
	}

	public void setStore(long store)
	{
		this.store = store;
	}

}
