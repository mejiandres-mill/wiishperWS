package com.mill.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mill.models.ChatUsers;

public class ChatUsersDao {
	
	public static ChatUsers getEntity(ResultSet rs, boolean eager) throws SQLException
	{
		ChatUsers cu = new ChatUsers();
		cu.setChat(rs.getLong("chats_idchats"));
		cu.setUser(rs.getLong("users_idusers"));
		cu.setJoined(rs.getDate("joined"));
		
		return cu;
	}
	
	public static <T> PreparedStatement getPreparedInsert(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		ChatUsers cu = (ChatUsers) currentEntity;
		PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tablename + " (chats_idchats, users_idusers, joined) "
				+ "VALUES ( ?, ?, ?)");
		ps.setLong(1, cu.getChat());
		ps.setLong(2, cu.getUser());
		ps.setDate(3, cu.getJoined() == null ? new Date(System.currentTimeMillis()) : cu.getJoined());
		
		return ps;
	}
	
	public static <T> PreparedStatement getPreparedUpdate(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		ChatUsers cu = (ChatUsers) currentEntity;
		PreparedStatement ps = conn.prepareStatement("UPDATE " + tablename + " SET joined = ? WHERE chats_idchats = ? AND users_idusers = ?");
		ps.setLong(2, cu.getChat());
		ps.setLong(3, cu.getUser());
		ps.setDate(1, cu.getJoined());
		
		return ps;
	}

}
