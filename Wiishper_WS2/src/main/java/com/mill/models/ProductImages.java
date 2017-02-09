package com.mill.models;

public class ProductImages {
	
	long idproduct_images;
	long image;
	long product;
	boolean show;

	public long getIdproduct_images()
	{
		return idproduct_images;
	}

	public void setIdproduct_images(long idproduct_images)
	{
		this.idproduct_images = idproduct_images;
	}

	public long getImage()
	{
		return image;
	}

	public void setImage(long image)
	{
		this.image = image;
	}

	public long getProduct()
	{
		return product;
	}

	public void setProduct(long product)
	{
		this.product = product;
	}

	public boolean isShow()
	{
		return show;
	}

	public void setShow(boolean show)
	{
		this.show = show;
	}

}
