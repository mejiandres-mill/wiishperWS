package com.mill.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mill.dao.DaoFactory;
import com.mill.dao.DaoInsert;

public class MySqlInsert implements DaoInsert{
	
	DaoFactory factory = null;
	
	public MySqlInsert(MySqlFactory mysqlFactory) throws SQLException
	{
		factory = mysqlFactory;
	}

	@Override
	public <T> boolean putInto(Connection conn, String tablename, T object, boolean running) throws SQLException
	{
		PreparedStatement ps = null;
		try
		{
			if(factory.getDaoRead().<T>exists(conn, tablename, object))
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
	<T> long putInto(Connection conn, String tablename, T object) throws SQLException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			if(factory.getDaoRead().<T>exists(conn, tablename, object))
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
