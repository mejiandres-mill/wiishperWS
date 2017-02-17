package com.mill.models;

import java.util.ArrayList;
import java.util.List;

public class Product {

	long idproducts;
	String name;
	String description;
	String price;
	boolean show;
	long store;
	List<Image> images;
	List<Tag> tags;
	
	public Product()
	{
		images = new ArrayList<>();
		tags = new ArrayList<>();
	}

	public List<Image> getImages()
	{
		return images;
	}

	public void setImages(List<Image> images)
	{
		this.images = images;
	}

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
	
	public List<Tag> getTags()
	{
		return tags;
	}

	public void setTags(List<Tag> tags)
	{
		this.tags = tags;
	}

	public void addImage(Image image)
	{
		images.add(image);
	}
	
	public void addTag(Tag tag)
	{
		tags.add(tag);
	}

}
