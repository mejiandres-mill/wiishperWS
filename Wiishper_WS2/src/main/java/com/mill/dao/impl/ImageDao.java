package com.mill.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mill.models.Image;

public class ImageDao {
	
	public static Image getEntity(ResultSet rs, boolean eager) throws SQLException
	{
		Image image = new Image();
		image.setIdimages(rs.getLong("idimages"));
		image.setUrl(rs.getString("url"));
		
		return image;
	}
	
	public static <T> PreparedStatement getPreparedInsert(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		Image image = (Image) currentEntity;
		PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tablename + " (idimages , url) VALUES (?, ?)");
		ps.setLong(1, image.getIdimages());
		ps.setString(2, image.getUrl());
		
		return ps;
	}
	
	public static <T> PreparedStatement getPreparedUpdate(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		Image image = (Image) currentEntity;
		PreparedStatement ps = conn.prepareStatement("UPDATE " + tablename + " SET url = ? WHERE idimages = ?");
		ps.setString(1, image.getUrl());
		ps.setLong(2, image.getIdimages());
		
		return ps;
	}

}
