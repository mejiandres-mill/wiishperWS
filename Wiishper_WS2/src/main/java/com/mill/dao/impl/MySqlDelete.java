package com.mill.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mill.dao.DaoDelete;
import com.mill.dao.DaoFactory;
import com.mill.dao.QueryType;

public class MySqlDelete implements DaoDelete {
	
	DaoFactory factory = null;
	
	public MySqlDelete(MySqlFactory mysqlFactory)
	{
		factory = mysqlFactory;
	}

	@Override
	public <T> boolean deleteFrom(Connection conn, String tablename, T object) throws SQLException
	{
		PreparedStatement ps = null;
		
		try
		{
			if(!factory.getDaoRead().<T>exists(conn, tablename, object))
				return false;
			long[] primaryKeyValues = MySqlSpecifics.<T>getPrimaryKeyValues(tablename, object);
			ps = conn.prepareStatement(MySqlSpecifics.queryString(tablename, primaryKeyValues, QueryType.DELETE));
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
