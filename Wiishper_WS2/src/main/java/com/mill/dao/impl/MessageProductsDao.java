package com.mill.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mill.models.MessageProducts;

public class MessageProductsDao {
	
	public static MessageProducts getEntity(ResultSet rs, boolean eager) throws SQLException
	{
		MessageProducts mp = new MessageProducts();
		mp.setMessage(rs.getLong("messages_idmessages"));
		mp.setProduct(rs.getLong("products_idproducts"));
		
		return mp;
	}
	
	public static <T> PreparedStatement getPreparedInsert(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		MessageProducts mp = (MessageProducts) currentEntity;
		PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tablename + " (messages_idmessages, products_idproducts) VALUES (?, ?)");
		ps.setLong(1, mp.getMessage());
		ps.setLong(2, mp.getProduct());
		
		return ps;
	}
	
	public static <T> PreparedStatement getPreparedUpdate(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		return getPreparedInsert(conn, tablename, currentEntity);
	}

}
