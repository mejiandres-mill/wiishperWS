package com.mill.models;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable{

	private static final long serialVersionUID = -8352821909603116965L;
	long idusers;
	String name;
	String password;
	String email;
	String gender;
	Date birthdate;
	Date entrydate;
	boolean active;
	long country;
	long image;
	String apikey;

	public long getIdusers()
	{
		return idusers;
	}

	public void setIdusers(long idusers)
	{
		this.idusers = idusers;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public Date getBirthdate()
	{
		return birthdate;
	}

	public void setBirthdate(Date birthdate)
	{
		this.birthdate = birthdate;
	}

	public Date getEntrydate()
	{
		return entrydate;
	}

	public void setEntrydate(Date entrydate)
	{
		this.entrydate = entrydate;
	}

	public boolean isActive()
	{
		return active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}

	public long getCountry()
	{
		return country;
	}

	public void setCountry(long country)
	{
		this.country = country;
	}

	public long getImage()
	{
		return image;
	}

	public void setImage(long image)
	{
		this.image = image;
	}

	public String getApikey()
	{
		return apikey;
	}

	public void setApikey(String apikey)
	{
		this.apikey = apikey;
	}

}
