package com.mill.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mill.models.Chat;

public class ChatDao {
	
	public static Chat getEntity(ResultSet rs, boolean eager) throws SQLException
	{
		Chat chat = new Chat();
		chat.setIdchats(rs.getLong("idchats"));
		chat.setName(rs.getString("name"));
		chat.setCreationdate(rs.getDate("creationdate"));
		chat.setUser(rs.getLong("users_idusers"));
		return chat;
	}
	
	public static <T> PreparedStatement getPreparedInsert(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		Chat chat = (Chat) currentEntity;
		PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tablename + " (idchats, name, creationdate, users_idusers)"
				+ " VALUES ( ?, ?, ?, ?)");
		ps.setLong(1, chat.getIdchats());
		ps.setString(2, chat.getName());
		ps.setDate(3, chat.getCreationdate() == null ? new Date(System.currentTimeMillis()) : chat.getCreationdate());
		ps.setLong(4, chat.getUser());
		
		return ps;
	}
	
	public static <T> PreparedStatement getPreparedUpdate(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		Chat chat = (Chat) currentEntity;
		PreparedStatement ps = conn.prepareStatement("UPDATE " + tablename + " SET name = ?, creationdate = ?, users_idusers = ? WHERE idchats = ?");
		ps.setString(1, chat.getName());
		ps.setDate(2, chat.getCreationdate());
		ps.setLong(3, chat.getUser());
		ps.setLong(4, chat.getIdchats());
		
		return ps;
	}

}
