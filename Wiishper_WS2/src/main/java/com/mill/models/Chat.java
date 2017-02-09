package com.mill.models;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Chat implements Serializable{

	private static final long serialVersionUID = 4621196315675278186L;
	long idchats;
	String name;
	Date creationdate;

	public long getIdchats()
	{
		return idchats;
	}

	public void setIdchats(long idchats)
	{
		this.idchats = idchats;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Date getCreationdate()
	{
		return creationdate;
	}

	public void setCreationdate(Date creationdate)
	{
		this.creationdate = creationdate;
	}

}
