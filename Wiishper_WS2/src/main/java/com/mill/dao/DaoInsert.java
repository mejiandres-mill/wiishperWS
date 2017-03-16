package com.mill.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

public interface DaoInsert {
	
	public abstract<T> boolean putInto(Connection conn, String tablename, T object, DaoFactory factory, boolean running) throws SQLException, NamingException;
	
	public abstract<T> long putInto(Connection conn, String tablename, T object, DaoFactory factory) throws SQLException, NamingException;

}
