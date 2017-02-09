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
		cu.setIdchatusers(rs.getLong("idchatusers"));
		cu.setChat(rs.getLong("chats_idchats"));
		cu.setUser(rs.getLong("users_idusers"));
		cu.setJoined(rs.getDate("joined"));
		
		return cu;
	}
	
	public static <T> PreparedStatement getPreparedInsert(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		ChatUsers cu = (ChatUsers) currentEntity;
		PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tablename + " (idchatusers, chats_idchats, users_idusers, joined) "
				+ "VALUES (?, ?, ?, ?)");
		ps.setLong(1, cu.getIdchatusers());
		ps.setLong(2, cu.getChat());
		ps.setLong(3, cu.getUser());
		ps.setDate(4, cu.getJoined() == null ? new Date(System.currentTimeMillis()) : cu.getJoined());
		
		return ps;
	}
	
	public static <T> PreparedStatement getPreparedUpdate(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		ChatUsers cu = (ChatUsers) currentEntity;
		PreparedStatement ps = conn.prepareStatement("UPDATE " + tablename + " SET chats_idchats = ?, users_idusers = ?, joined = ? WHERE idchatusers = ?");
		ps.setLong(1, cu.getChat());
		ps.setLong(2, cu.getUser());
		ps.setDate(3, cu.getJoined());
		ps.setLong(4, cu.getIdchatusers());
		
		return ps;
	}

}
