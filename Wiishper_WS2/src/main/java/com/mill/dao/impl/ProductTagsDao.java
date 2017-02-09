package com.mill.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mill.models.ProductTags;

public class ProductTagsDao {
	
	public static ProductTags getEntity(ResultSet rs, boolean eager) throws SQLException
	{
		ProductTags pt = new ProductTags();
		pt.setIdproduct_tags(rs.getLong("idproduct_tags"));
		pt.setProduct(rs.getLong("products_idproducts"));
		pt.setTag(rs.getLong("tags_idtags"));
		
		return pt;
	}
	
	public static <T> PreparedStatement getPreparedInsert(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		ProductTags pt = (ProductTags) currentEntity;
		PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tablename + " (idproduct_tags, products_idproducts, tags_idtags) VALUES (?, ?, ?)");
		ps.setLong(1, pt.getIdproduct_tags());
		ps.setLong(2, pt.getProduct());
		ps.setLong(3, pt.getTag());
		
		return ps;
	}
	
	public static <T> PreparedStatement getPreparedUpdate(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		ProductTags pt = (ProductTags) currentEntity;
		PreparedStatement ps = conn.prepareStatement("UPDATE " + tablename + " SET products_idproducts = ?, tags_idtags = ? WHERE idproduct_tags = ?");
		ps.setLong(1, pt.getProduct());
		ps.setLong(2, pt.getTag());
		ps.setLong(3, pt.getIdproduct_tags());
		
		return ps;
	}

}
