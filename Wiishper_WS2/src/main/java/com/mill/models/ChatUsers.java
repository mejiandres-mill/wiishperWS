package com.mill.models;

import java.sql.Date;

public class ChatUsers {

	long user;
	long chat;
	Date joined;


	public long getUser()
	{
		return user;
	}

	public void setUser(long user)
	{
		this.user = user;
	}

	public long getChat()
	{
		return chat;
	}

	public void setChat(long chat)
	{
		this.chat = chat;
	}

	public Date getJoined()
	{
		return joined;
	}

	public void setJoined(Date joined)
	{
		this.joined = joined;
	}

}
