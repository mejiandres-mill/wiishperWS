package com.mill.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

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
	public <T> List<T> getAll(Connection conn, String tablename, DaoFactory factory) throws SQLException, NamingException
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
				list.add((T) MySqlSpecifics.getEntityFromResultSet(tablename, rs, false, factory));
			}
		}
		catch(NamingException e)
		{
			throw e;
		}
		finally
		{
			DaoFactory.closeAll(ps, rs);
		}
		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> getAllForInput(Connection conn, String tablename, String columnName, String searchValue, DaoFactory factory)
			throws SQLException, NamingException
	{
		List<T> list = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			ps = conn.prepareStatement("SELECT * FROM " + tablename + " WHERE " + columnName + " LIKE '%" + searchValue + "%' ");
			rs = ps.executeQuery();
			while(rs.next())
				list.add((T) MySqlSpecifics.getEntityFromResultSet(tablename, rs, true, factory));
			
		}
		catch(NamingException e)
		{
			throw e;
		}
		finally
		{
			DaoFactory.closeAll(ps, rs);
		}
		return list;
 	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> getAllForInputExact(Connection conn, String tablename, String columnName, String searchValue, DaoFactory factory) throws SQLException, NamingException
	{
		List<T> list = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			ps = conn.prepareStatement("SELECT * FROM " + tablename + " WHERE " + columnName + " = '" + searchValue + "' ");
			rs = ps.executeQuery();
			while(rs.next())
				list.add((T) MySqlSpecifics.getEntityFromResultSet(tablename, rs, false, factory));
			
		}
		finally
		{
			DaoFactory.closeAll(ps, rs);
		}
		return list;
	}

	@Override
	public <T> T get(Connection conn, String tablename, long[] keyValues, DaoFactory factory) throws SQLException, NamingException
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
				currentEntity = MySqlSpecifics.getEntityFromResultSet(tablename, rs, false, factory);
		}
		catch(NamingException e)
		{
			throw e;
		}
		finally
		{
			DaoFactory.closeAll(ps, rs);
		}
		return currentEntity;
	}

	@Override
	public <T> boolean exists(Connection conn, String tablename, long[] keyValues, DaoFactory factory) throws SQLException, NamingException
	{
		return get(conn, tablename, keyValues, factory) != null;
	}

	@Override
	public <T> boolean exists(Connection conn, String tablename, T current, DaoFactory factory) throws SQLException, NamingException
	{
		long[] keyValues = MySqlSpecifics.<T>getPrimaryKeyValues(tablename, current);
		try
		{
		return get(conn, tablename, keyValues, factory) != null;
		}
		catch(NamingException e)
		{
			throw e;
		}
	}

}
