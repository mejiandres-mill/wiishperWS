package com.mill.models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Message {

	long idmessages;
	String message;
	long chat;
	long sender;
	Timestamp stamp;
	List<Product> products;
	
	public Message()
	{
		products = new ArrayList<>();
	}

	public List<Product> getProducts()
	{
		return products;
	}

	public void setProducts(List<Product> products)
	{
		this.products = products;
	}

	public Timestamp getStamp()
	{
		return stamp;
	}

	public void setStamp(Timestamp stamp)
	{
		this.stamp = stamp;
	}

	public long getIdmessages()
	{
		return idmessages;
	}

	public void setIdmessages(long idmessages)
	{
		this.idmessages = idmessages;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public long getChat()
	{
		return chat;
	}

	public void setChat(long chat)
	{
		this.chat = chat;
	}

	public long getSender()
	{
		return sender;
	}

	public void setSender(long sender)
	{
		this.sender = sender;
	}
	
	public void addProduct(Product product)
	{
		products.add(product);
	}

}
