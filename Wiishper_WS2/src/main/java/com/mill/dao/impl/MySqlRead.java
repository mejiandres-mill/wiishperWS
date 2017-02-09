package com.mill.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mill.dao.DaoFactory;
import com.mill.dao.DaoRead;
import com.mill.dao.QueryType;

public class MySqlRead implements DaoRead{
	
	DaoFactory factory = null;
	
	public MySqlRead(DaoFactory factory) throws SQLException
	{
		this.factory = factory;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> getAll(Connection conn, String tablename) throws SQLException
	{
		List<T> list = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement("SELECT * FROM " + tablename);
			rs = ps.executeQuery();
			while(rs.next())
			{
				list.add((T) MySqlSpecifics.getEntityFromResultSet(tablename, rs, false));
			}
		}
		finally
		{
			DaoFactory.closeAll(ps, rs);
		}
		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> getAllForInput(Connection conn, String tablename, String columnName, String searchValue)
			throws SQLException
	{
		List<T> list = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			ps = conn.prepareStatement("SELECT * FROM " + tablename + " WHERE " + columnName + " LIKE '%" + searchValue + "%' ");
			rs = ps.executeQuery();
			while(rs.next())
				list.add((T) MySqlSpecifics.getEntityFromResultSet(tablename, rs, false));
			
		}
		finally
		{
			DaoFactory.closeAll(ps, rs);
		}
		return list;
 	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> getAllForInputExact(Connection conn, String tablename, String columnName, String searchValue) throws SQLException
	{
		List<T> list = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			ps = conn.prepareStatement("SELECT * FROM " + tablename + " WHERE " + columnName + " = '" + searchValue + "' ");
			rs = ps.executeQuery();
			while(rs.next())
				list.add((T) MySqlSpecifics.getEntityFromResultSet(tablename, rs, false));
			
		}
		finally
		{
			DaoFactory.closeAll(ps, rs);
		}
		return list;
	}

	@Override
	public <T> T get(Connection conn, String tablename, long[] keyValues) throws SQLException
	{
		T currentEntity = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			String queryString = MySqlSpecifics.queryString(tablename, keyValues, QueryType.READ);
			ps = conn.prepareStatement(queryString);
			rs = ps.executeQuery();
			if(rs.next())
				currentEntity = MySqlSpecifics.getEntityFromResultSet(tablename, rs, false);
		}
		finally
		{
			DaoFactory.closeAll(ps, rs);
		}
		return currentEntity;
	}

	@Override
	public <T> boolean exists(Connection conn, String tablename, long[] keyValues) throws SQLException
	{
		return get(conn, tablename, keyValues) != null;
	}

	@Override
	public <T> boolean exists(Connection conn, String tablename, T current) throws SQLException
	{
		long[] keyValues = MySqlSpecifics.<T>getPrimaryKeyValues(tablename, current);
		return get(conn, tablename, keyValues) != null;
	}

}
