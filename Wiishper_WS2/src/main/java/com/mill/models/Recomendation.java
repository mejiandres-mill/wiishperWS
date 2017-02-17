package com.mill.models;

import java.sql.Date;

public class Recomendation {
	
	long recomender;
	long receipient;
	long product;
	Date rec_date;
	boolean showed;

	public boolean isShowed()
	{
		return showed;
	}

	public void setShowed(boolean showed)
	{
		this.showed = showed;
	}

	public long getRecomender()
	{
		return recomender;
	}

	public void setRecomender(long recomender)
	{
		this.recomender = recomender;
	}

	public long getReceipient()
	{
		return receipient;
	}

	public void setReceipient(long receipient)
	{
		this.receipient = receipient;
	}

	public long getProduct()
	{
		return product;
	}

	public void setProduct(long product)
	{
		this.product = product;
	}

	public Date getRec_date()
	{
		return rec_date;
	}

	public void setRec_date(Date rec_date)
	{
		this.rec_date = rec_date;
	}

}
