package com.mill.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Chat {

	long idchats;
	String name;
	Date creationdate;
	long user;
	List<User> participants;
	List<Message> messages;
	
	public Chat()
	{
		participants = new ArrayList<>();
		messages = new ArrayList<>();
	}

	public List<User> getParticipants()
	{
		return participants;
	}

	public void setParticipants(List<User> participants)
	{
		this.participants = participants;
	}

	public List<Message> getMessages()
	{
		return messages;
	}

	public void setMessages(List<Message> messages)
	{
		this.messages = messages;
	}

	public long getUser()
	{
		return user;
	}

	public void setUser(long user)
	{
		this.user = user;
	}

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
	
	public void addMessage(Message message)
	{
		messages.add(message);
	}
	
	public void addParticipant(User user)
	{
		participants.add(user);
	}

}
