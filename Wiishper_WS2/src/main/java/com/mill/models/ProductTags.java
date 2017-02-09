package com.mill.models;

public class ProductTags {
	
	long idproduct_tags;
	long tag;
	long product;

	public long getIdproduct_tags()
	{
		return idproduct_tags;
	}

	public void setIdproduct_tags(long idproduct_tags)
	{
		this.idproduct_tags = idproduct_tags;
	}

	public long getTag()
	{
		return tag;
	}

	public void setTag(long tag)
	{
		this.tag = tag;
	}

	public long getProduct()
	{
		return product;
	}

	public void setProduct(long product)
	{
		this.product = product;
	}

}
