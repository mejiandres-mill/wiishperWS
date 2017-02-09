package com.mill.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mill.models.Store;

public class StoreDao {
	
	public static Store getEntity(ResultSet rs, boolean eager) throws SQLException
	{
		Store store = new Store();
		store.setIdstores(rs.getLong("idstores"));
		store.setCountry(rs.getLong("countries_idcountries"));
		store.setSite(rs.getString("site"));
		store.setName(rs.getString("name"));
		
		return store;
	}
	
	public static <T> PreparedStatement getPreparedInsert(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		Store store = (Store) currentEntity;
		PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tablename + " (idstores, countries_idcountries, stie, name) VALUES ( ?, ?, ?, ?)");
		ps.setLong(1, store.getIdstores());
		ps.setLong(2, store.getCountry());
		ps.setString(3, store.getSite());
		ps.setString(4, store.getName());
		
		return ps;
	}
	
	public static <T> PreparedStatement getPreparedUpdate(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		Store store = (Store) currentEntity;
		PreparedStatement ps = conn.prepareStatement("UPDATE " + tablename + " SET countries_idcountries = ?, site = ?, name = ? WHERE idstores = ?");
		ps.setLong(1, store.getCountry());
		ps.setString(2, store.getSite());
		ps.setString(3, store.getName());
		ps.setLong(4, store.getIdstores());
		
		return ps;
	}

}
