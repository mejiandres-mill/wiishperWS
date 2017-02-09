package com.mill.models;

import java.sql.Date;

public class Taste {

	long user;
	long product;
	boolean like;
	Date inter_date;

	public Date getInter_date()
	{
		return inter_date;
	}

	public void setInter_date(Date inter_date)
	{
		this.inter_date = inter_date;
	}

	public long getUser()
	{
		return user;
	}

	public void setUser(long user)
	{
		this.user = user;
	}

	public long getProduct()
	{
		return product;
	}

	public void setProduct(long product)
	{
		this.product = product;
	}

	public boolean isLike()
	{
		return like;
	}

	public void setLike(boolean like)
	{
		this.like = like;
	}

}
