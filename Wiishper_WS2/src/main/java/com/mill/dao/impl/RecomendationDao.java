package com.mill.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mill.models.Recomendation;

public class RecomendationDao {
	
	public static Recomendation getEntity(ResultSet rs, boolean eager) throws SQLException
	{
		Recomendation recomendation = new Recomendation();
		recomendation.setRecomender(rs.getLong("recomender"));
		recomendation.setReceipient(rs.getLong("receipient"));
		recomendation.setProduct(rs.getLong("products_idproducts"));
		recomendation.setRec_date(rs.getDate("rec_date"));
		recomendation.setShowed(rs.getBoolean("showed"));
		
		return recomendation;
	}
	
	public static <T> PreparedStatement getPreparedInsert(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		Recomendation recomendation = (Recomendation) currentEntity;
		PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tablename + " (recomender, receipient, products_idproducts, rec_date, showed) VALUES (?, ?, ?, ?, ?)");
		ps.setLong(1,  recomendation.getRecomender());
		ps.setLong(2, recomendation.getReceipient());
		ps.setLong(3, recomendation.getProduct());
		ps.setDate(4, recomendation.getRec_date() == null ? new Date(System.currentTimeMillis()) : recomendation.getRec_date());
		ps.setBoolean(5, recomendation.isShowed());
		
		return ps;
	}
	
	public static <T> PreparedStatement getPreparedUpdate(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		Recomendation recomendation = (Recomendation) currentEntity;
		PreparedStatement ps = conn.prepareStatement("UPDATE " + tablename + " SET rec_date = ?, showed = ? WHERE recomender = ?, receipient = ?, products_idproducts = ?");
		ps.setDate(1, recomendation.getRec_date());
		ps.setBoolean(2, recomendation.isShowed());
		ps.setLong(3, recomendation.getRecomender());
		ps.setLong(4, recomendation.getReceipient());
		ps.setLong(5, recomendation.getProduct());
		
		return ps;
	}

}
