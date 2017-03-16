package com.mill.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mill.dao.DaoFactory;
import com.mill.dao.impl.MySqlSpecifics;
import com.mill.exceptions.WSException;
import com.mill.utils.Constants;

public class SqlUtil {
	
	private DataSource myDB;
	
	public SqlUtil() throws NamingException
	{
		if(myDB == null)
		{
			myDB = (DataSource) new InitialContext().lookup(Constants.DATASOURCE);
		}
	}
	
	public Connection getConnection() throws SQLException
	{
		Connection conn = myDB.getConnection();
		conn.setAutoCommit(false);
		return conn;
	}
	
	public static void rollBackAndClose(Connection conn) throws SQLException
	{
		conn.rollback();
		conn.close();
	}
	
	public static void commitAndClose(Connection conn) throws SQLException
	{
		conn.commit();
		conn.close();
	}
	
	public <T> List<T> executeDBOperation(String query, String tablename, Object[] params, DaoFactory factory) throws SQLException, WSException, NamingException
	{
		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement(query);
		List<T> list = new ArrayList<>();
		
		int paramCount = 0;
		int index = -1;
		while((index = query.indexOf('?', index+1)) > 0)
			paramCount++;
		
		if(params.length != paramCount)
			throw new WSException(Constants.DATABASE_ERROR, "Número de parametros no coincide con número de wildcards");
		
		for(int i=0; i<paramCount; i++)
			ps.setObject(i+1, params[i]);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next())
		{
			T object = MySqlSpecifics.<T>getEntityFromResultSet(tablename, rs, true, factory);
			list.add(object);
		}
		
		ps.close();
		rs.close();
		conn.close();
		
		return list;
	}

}
