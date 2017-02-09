package com.mill.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mill.dao.DaoFactory;
import com.mill.dao.DaoUpdate;

public class MySqlUpdate implements DaoUpdate{
	
	DaoFactory factory = null;
	
	public MySqlUpdate(DaoFactory factory)
	{
		this.factory = factory;
	}

	@Override
	public <T> boolean merge(Connection conn, String tablename, T object) throws SQLException
	{
		PreparedStatement ps = null;
		try
		{
			ps = MySqlSpecifics.<T>getPreparedUpdate(conn, tablename, object);
			ps.execute();
		}
		finally
		{
			DaoFactory.closeAll(ps, null);
			conn.commit();
			conn.close();
		}
		return true;
	}

}
