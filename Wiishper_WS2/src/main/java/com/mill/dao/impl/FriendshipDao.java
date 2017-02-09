package com.mill.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mill.models.Friendship;

public class FriendshipDao {
	
	public static Friendship getEntity(ResultSet rs, boolean eager) throws SQLException
	{
		Friendship friendship = new Friendship();
		friendship.setFriender(rs.getLong("friender"));
		friendship.setFriendee(rs.getLong("friendee"));
		friendship.setBefriendDate(rs.getDate("befriend_date"));
		
		return friendship;
	}
	
	public static <T> PreparedStatement getPreparedInsert(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		Friendship friendsihp = (Friendship) currentEntity;
		PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tablename + " (friender, friendee, befriend_date) VALUES (?, ?, ?)");
		ps.setLong(1, friendsihp.getFriender());
		ps.setLong(2, friendsihp.getFriendee());
		ps.setDate(3, friendsihp.getBefriendDate() == null ? new Date(System.currentTimeMillis()) : friendsihp.getBefriendDate());
		
		return ps;
	}
	
	public static <T> PreparedStatement getPreparedUpdate(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		Friendship friendship = (Friendship) currentEntity;
		PreparedStatement ps = conn.prepareStatement("UPDATE " + tablename + " SET befriend_date = ? WHERE friender = ? AND friendee = ?");
		ps.setDate(1, friendship.getBefriendDate());
		ps.setLong(2, friendship.getFriender());
		ps.setLong(3, friendship.getFriendee());
		
		return ps;		
	}

}
