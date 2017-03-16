package com.mill.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

public interface DaoRead {
	
	public abstract<T> List<T> getAll(Connection conn, String tablename, DaoFactory factory) throws SQLException, NamingException;
	
	public abstract<T> List<T> getAllForInput(Connection conn, String tablename, String columnName, String searchValue, DaoFactory factory) throws SQLException, NamingException;
	
	public abstract<T> List<T> getAllForInputExact(Connection conn, String tablename, String columnName, String searchValue, DaoFactory factory) throws SQLException, NamingException;
	
	public abstract<T> T get(Connection conn, String tablename, long[] keyValues, DaoFactory factory) throws SQLException, NamingException;
	
	public abstract<T> boolean exists(Connection conn, String tablename, long[] keyValues, DaoFactory factory) throws SQLException, NamingException;
	
	public abstract<T> boolean exists(Connection conn, String tablename, T current, DaoFactory factory) throws SQLException, NamingException;

}
