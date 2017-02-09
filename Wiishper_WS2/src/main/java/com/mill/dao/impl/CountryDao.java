package com.mill.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mill.models.Country;

public class CountryDao {
	
	public static Country getEntity(ResultSet rs, boolean eager) throws SQLException
	{
		Country country = new Country();
		country.setIdcountries(rs.getLong("idcountries"));
		country.setName(rs.getString("name"));
		
		return country;
	}
	
	public static <T> PreparedStatement getPreparedInsert(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		Country country = (Country) currentEntity;
		PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tablename + " (idcountries, name) VALUES (?,?)");
		ps.setLong(1, country.getIdcountries());
		ps.setString(2, country.getName());
		
		return ps;
	}
	
	public static <T> PreparedStatement getPreparedUpdate(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		Country country = (Country) currentEntity;
		PreparedStatement ps = conn.prepareStatement("UPDATE " + tablename + "SET name = ? WHERE idcountries = ?");
		ps.setString(1, country.getName());
		ps.setLong(2, country.getIdcountries());
		
		return ps;
	}

}
