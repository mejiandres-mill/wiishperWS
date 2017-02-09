package com.mill.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mill.models.StoreTags;

public class StoreTagsDao {
	
	public static StoreTags getEntity(ResultSet rs, boolean eager) throws SQLException
	{
		StoreTags st = new StoreTags();
		st.setStore(rs.getLong("stores_idstores"));
		st.setTag(rs.getLong("tags_idtags"));
		
		return st;
	}
	
	public static <T> PreparedStatement getPreparedInsert(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		StoreTags st = (StoreTags) currentEntity;
		PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tablename + " (stores_idstores, tags_idtags) VALUES (?, ?)");
		ps.setLong(1, st.getStore());
		ps.setLong(2, st.getTag());
		
		return ps;
	}
	
	public static <T> PreparedStatement getPreparedUpdate(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		return getPreparedInsert(conn, tablename, currentEntity);
	}

}
