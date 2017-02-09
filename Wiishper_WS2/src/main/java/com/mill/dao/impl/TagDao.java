package com.mill.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mill.models.Tag;

public class TagDao {
	
	public static Tag getEntity(ResultSet rs, boolean eager) throws SQLException
	{
		Tag tag = new Tag();
		tag.setIdtags(rs.getLong("idtags"));
		tag.setName(rs.getString("name"));
		
		return tag;
	}
	
	public static <T> PreparedStatement getPreparedInsert(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		Tag tag = (Tag) currentEntity;
		PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tablename + " (idtags, name) VALUES (?, ?)");
		ps.setLong(1, tag.getIdtags());
		ps.setString(2, tag.getName());
		
		return ps;
	}
	
	public static <T> PreparedStatement getPreparedUpdate(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		Tag tag = (Tag) currentEntity;
		PreparedStatement ps = conn.prepareStatement("UPDATE " + tablename + " SET name = ? WHERE idtags = ?");
		ps.setString(1, tag.getName());
		ps.setLong(2, tag.getIdtags());
		
		return ps;
	}

}
