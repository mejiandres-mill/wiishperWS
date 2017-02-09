package com.mill.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mill.models.Taste;

public class TasteDao {
	
	public static Taste getEntity(ResultSet rs, boolean eager)  throws SQLException
	{
		Taste taste = new Taste();
		taste.setUser(rs.getLong("users_idusers"));
		taste.setProduct(rs.getLong("products_idproducts"));
		taste.setLike(rs.getBoolean("liked"));
		taste.setInter_date(rs.getDate("inter_date"));
		
		return taste;
	}
	
	public static <T> PreparedStatement getPreparedInsert(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		Taste taste = (Taste) currentEntity;
		PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tablename + " (users_idusers, products_idproducts, liked, inter_date) VALUES (?, ?, ?, ?)");
		ps.setLong(1, taste.getUser());
		ps.setLong(2, taste.getProduct());
		ps.setBoolean(3, taste.isLike());
		ps.setDate(4, taste.getInter_date() == null ? new Date(System.currentTimeMillis()) : taste.getInter_date());
		
		return ps;
	}
	
	public static <T> PreparedStatement getPreparedUpdate(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		Taste taste = (Taste) currentEntity;
		PreparedStatement ps = conn.prepareStatement("UPDATE " + tablename + " SET liked = ?, inter_date = ? WHERE users_idusers = ? AND products_idproducts = ?");
		ps.setBoolean(1, taste.isLike());
		ps.setDate(2, taste.getInter_date() == null ? new Date(System.currentTimeMillis()) : taste.getInter_date());
		
		return ps;
	}

}
