package com.mill.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.mill.dao.impl.MySqlFactory;

public abstract class DaoFactory {
	
	public abstract DaoInsert getDaoInsert() throws SQLException;
	
	public abstract DaoRead getDaoRead() throws SQLException;
	
	public abstract DaoDelete getDaoDelete();
	
	public abstract DaoUpdate getDaoUpdate();
	
	public static DaoFactory factoryProducer(String database) throws NamingException
	{
		switch(database)
		{
			case "MySQL":
				return new MySqlFactory();
			default:
				return null;
		}
	}
	
	public static void closeAll(PreparedStatement ps, ResultSet rs) throws SQLException
	{
		if(rs != null)
			rs.close();
		if(ps != null)
			ps.close();		
	}

}
