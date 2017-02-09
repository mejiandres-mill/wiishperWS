package com.mill.models;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Friendship {

	long friender;
	long friendee;
	Date befriendDate;

	public long getFriender()
	{
		return friender;
	}

	public void setFriender(long friender)
	{
		this.friender = friender;
	}

	public long getFriendee()
	{
		return friendee;
	}

	public void setFriendee(long friendee)
	{
		this.friendee = friendee;
	}

	public Date getBefriendDate()
	{
		return befriendDate;
	}

	public void setBefriendDate(Date befriendDate)
	{
		this.befriendDate = befriendDate;
	}

}
