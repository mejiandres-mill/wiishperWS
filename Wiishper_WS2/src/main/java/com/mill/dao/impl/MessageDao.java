package com.mill.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import com.mill.models.Message;

public class MessageDao {
	
	public static Message getEntity(ResultSet rs, boolean eager) throws SQLException
	{
		Message message = new Message();
		message.setIdmessages(rs.getLong("idmessages"));
		message.setChat(rs.getLong("chats_idchats"));
		message.setMessage(rs.getString("message"));
		message.setSender(rs.getLong("users_idusers"));
		message.setStamp(rs.getTimestamp("time"));
		
		return message;
	}
	
	public static <T> PreparedStatement getPreparedInsert(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		Message message = (Message) currentEntity;
		PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tablename + " (idmessages, chats_idchats, message, users_idusers, time) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		ps.setLong(1, message.getIdmessages());
		ps.setLong(2, message.getChat());
		ps.setString(3, message.getMessage());
		ps.setLong(4, message.getSender());
		ps.setTimestamp(5, message.getStamp() == null ? new Timestamp(System.currentTimeMillis()) : message.getStamp());
		
		return ps;
	}
	
	public static <T> PreparedStatement getPreparedUpdate(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		Message message = (Message) currentEntity;
		PreparedStatement ps = conn.prepareStatement("UPDATE " + tablename + " SET chats_idchats = ?, message = ?, users_idusers = ?, time = ? WHERE idmessages = ?");
		ps.setLong(1, message.getChat());
		ps.setString(2, message.getMessage());
		ps.setLong(3, message.getSender());
		ps.setTimestamp(4, message.getStamp());
		ps.setLong(5, message.getIdmessages());
		
		return ps;
	}

}
