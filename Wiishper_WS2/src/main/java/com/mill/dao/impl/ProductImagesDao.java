package com.mill.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mill.models.ProductImages;

public class ProductImagesDao {
	
	public static ProductImages getEntity(ResultSet rs, boolean eager) throws SQLException
	{
		ProductImages pi = new ProductImages();
		pi.setIdproduct_images(rs.getLong("idproduct_images"));
		pi.setImage(rs.getLong("images_idimages"));
		pi.setProduct(rs.getLong("products_idproducts"));
		pi.setShow(rs.getBoolean("show"));
		
		return pi;
	}
	
	public static <T> PreparedStatement getPreparedInsert(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		ProductImages pi = (ProductImages) currentEntity;
		PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tablename + " (idproduct_images, images_idimages, products_idproducts, `show`) VALUES (?, ?, ?, ?)");
		ps.setLong(1, pi.getIdproduct_images());
		ps.setLong(2, pi.getImage());
		ps.setLong(3, pi.getProduct());
		ps.setBoolean(4, pi.isShow());
		
		return ps;
	}
	
	public static <T> PreparedStatement getPreparedUpdate(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		ProductImages pi = (ProductImages) currentEntity;
		PreparedStatement ps = conn.prepareStatement("UPDATE " + tablename + " SET images_idimages = ?, products_idproducts = ?, `show` = ? WHERE idproduct_images = ?");
		ps.setLong(1, pi.getImage());
		ps.setLong(2, pi.getProduct());
		ps.setBoolean(3, pi.isShow());
		ps.setLong(4, pi.getIdproduct_images());
		
		return ps;
	}

}
