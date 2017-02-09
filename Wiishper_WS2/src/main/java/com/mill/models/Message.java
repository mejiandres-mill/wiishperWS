package com.mill.models;

public class Message {

	long idmessages;
	String message;
	long chat;
	long sender;

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

}
