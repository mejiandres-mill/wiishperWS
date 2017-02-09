package com.mill.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mill.models.Message;

public class MessageDao {
	
	public static Message getEntity(ResultSet rs, boolean eager) throws SQLException
	{
		Message message = new Message();
		message.setIdmessages(rs.getLong("idmessages"));
		message.setChat(rs.getLong("chats_idchats"));
		message.setMessage(rs.getString("message"));
		message.setSender(rs.getLong("users_idusers"));
		
		return message;
	}
	
	public static <T> PreparedStatement getPreparedInsert(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		Message message = (Message) currentEntity;
		PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tablename + " (idmessages, chats_idchats, message, users_idusers) VALUES (?, ?, ?, ?)");
		ps.setLong(1, message.getIdmessages());
		ps.setLong(2, message.getChat());
		ps.setString(3, message.getMessage());
		ps.setLong(4, message.getSender());
		
		return ps;
	}
	
	public static <T> PreparedStatement getPreparedUpdate(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		Message message = (Message) currentEntity;
		PreparedStatement ps = conn.prepareStatement("UPDATE " + tablename + " SET chats_idchats = ?, message = ?, users_idusers = ? WHERE idmessages = ?");
		ps.setLong(1, message.getChat());
		ps.setString(2, message.getMessage());
		ps.setLong(3, message.getSender());
		ps.setLong(4, message.getIdmessages());
		
		return ps;
	}

}
