package com.mill.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.mill.dao.DaoFactory;
import com.mill.dao.DaoInsert;

public class MySqlInsert implements DaoInsert{
	
	DaoFactory factory = null;
	
	public MySqlInsert(MySqlFactory mysqlFactory) throws SQLException
	{
		factory = mysqlFactory;
	}

	@Override
	public <T> boolean putInto(Connection conn, String tablename, T object, DaoFactory factory, boolean running) throws SQLException, NamingException
	{
		PreparedStatement ps = null;
		try
		{
			if(factory.getDaoRead().<T>exists(conn, tablename, object, factory))
				return false;
			ps = MySqlSpecifics.<T>getPreparedInsert(conn, tablename, object);
			ps.executeUpdate();
			return true;
		}
		finally
		{
			DaoFactory.closeAll(ps, null);
			conn.commit();
			conn.close();
		}
	}
	
	@Override 
	public 
	<T> long putInto(Connection conn, String tablename, T object, DaoFactory factory) throws SQLException, NamingException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			if(factory.getDaoRead().<T>exists(conn, tablename, object, factory))
				return -1;
			ps = MySqlSpecifics.<T>getPreparedInsert(conn, tablename, object);
			ps.executeUpdate();
			rs  = ps.getGeneratedKeys();
			return rs.next() ? rs.getLong(1) : -1;
		}
		finally
		{
			DaoFactory.closeAll(ps, rs);
			conn.commit();
			conn.close();
		}
	}

}
