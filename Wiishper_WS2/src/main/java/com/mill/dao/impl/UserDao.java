package com.mill.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.mill.DAL.SqlUtil;
import com.mill.dao.DaoFactory;
import com.mill.models.Image;
import com.mill.models.User;
import com.mill.utils.Constants;

public class UserDao {
	
	public static User getEntity(ResultSet rs, boolean eager, DaoFactory factory) throws SQLException, NamingException
	{
		User user = new User();
		user.setIdusers(rs.getLong("idusers"));
		user.setActive(rs.getBoolean("active"));
		user.setApikey(rs.getString("apikey"));
		user.setBirthdate(rs.getDate("birthdate"));
		user.setCountry(rs.getLong("countries_idcountries"));
		user.setEmail(rs.getString("email"));
		user.setEntrydate(rs.getDate("entrydate"));
		user.setGender(rs.getString("gender"));
		if(eager)
		{
			SqlUtil sqlUtil = new SqlUtil();
			Connection conn = sqlUtil.getConnection();
			long image = rs.getLong("images_idimages");
			long[] keyValues = new long[1];
			keyValues[0] = image;
			user.setImage(factory.getDaoRead().<Image> get(conn, Constants.TABLE_IMAGES, keyValues, factory));
			conn.close();
		}
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		
		return user;
	}
	
	public static <T> PreparedStatement getPreparedInsert(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		User user = (User) currentEntity;
		
		PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tablename + " (idusers, active, apikey, birthdate, countries_idcountries, email, entrydate, gender, images_idimages, name, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		ps.setLong(1, user.getIdusers());
		ps.setBoolean(2, user.isActive());
		ps.setString(3, user.getApikey());
		ps.setDate(4, user.getBirthdate() == null ? new Date(System.currentTimeMillis()) : user.getBirthdate());
		ps.setLong(5, user.getCountry());
		ps.setString(6, user.getEmail());
		ps.setDate(7, user.getEntrydate() == null ? new Date(System.currentTimeMillis()) : user.getEntrydate());
		ps.setString(8, user.getGender());
		ps.setLong(9, user.getImage() != null ? user.getImage().getIdimages() : 1);
		ps.setString(10, user.getName());
		ps.setString(11, user.getPassword());
		
		return ps;
	}
	
	public static <T> PreparedStatement getPreparedUpdate(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		User user = (User) currentEntity;
		PreparedStatement ps = conn.prepareStatement("UPDATE " + tablename + " SET active = ?, apiKey = ?, birthdate = ?, countries_idcountries = ?, email = ?, "
				+ " entrydate = ?, gender = ?, images_idimages = ?, name = ?, password = ? WHERE idusers = ?");
		ps.setLong(11, user.getIdusers() != 0 ? user.getIdusers() : null);
		ps.setBoolean(1, user.isActive());
		ps.setString(2, user.getApikey());
		ps.setDate(3, user.getBirthdate() == null ? new Date(System.currentTimeMillis()) : user.getBirthdate());
		ps.setLong(4, user.getCountry());
		ps.setString(5, user.getEmail());
		ps.setDate(6, user.getEntrydate() == null ? new Date(System.currentTimeMillis()) : user.getEntrydate());
		ps.setString(7, user.getGender());
		ps.setLong(8, user.getImage() == null ? 1 : user.getImage().getIdimages());
		ps.setString(9, user.getName());
		ps.setString(10, user.getPassword());
		
		return ps;
	}
}
