package com.mill.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mill.models.Product;

public class ProductDao {
	
	public static Product getEntity(ResultSet rs, boolean eager) throws SQLException
	{
		Product product = new Product();
		product.setIdproducts(rs.getLong("idproducts"));
		product.setName(rs.getString("name"));
		product.setDescription(rs.getString("description"));
		product.setPrice(rs.getString("price"));
		product.setShow(rs.getBoolean("show"));
		product.setStore(rs.getLong("stores_idstores"));
		
		return product;
	}
	
	public static <T> PreparedStatement getPreparedInsert(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		Product product = (Product) currentEntity;
		PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tablename + " (idproducts, name, description, price, show, stores_idstores) VALUES (?, ?, ?, ?, ?, ?)");
		ps.setLong(1, product.getIdproducts());
		ps.setString(2, product.getName());
		ps.setString(3, product.getDescription());
		ps.setString(4, product.getPrice());
		ps.setBoolean(5, product.isShow());
		ps.setLong(6, product.getStore());
		
		return ps;
	}
	
	public static <T> PreparedStatement getPreparedUpdate(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		Product product = (Product) currentEntity;
		PreparedStatement ps = conn.prepareStatement("UPDATE " + tablename + " SET name = ?, description = ?, price = ?, show = ?, stores_idstores = ? WHERE idproducts = ?");
		ps.setString(1, product.getName());
		ps.setString(2, product.getDescription());
		ps.setString(3, product.getPrice());
		ps.setBoolean(4, product.isShow());
		ps.setLong(5, product.getStore());
		ps.setLong(6, product.getIdproducts());
		
		return ps;
	}

}
