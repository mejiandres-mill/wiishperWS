package com.mill.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageProducts {
	
	private long message;
	private long product;
	
	public long getMessage()
	{
		return message;
	}
	public void setMessage(long message)
	{
		this.message = message;
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
