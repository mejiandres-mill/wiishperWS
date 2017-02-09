package com.mill.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

}
