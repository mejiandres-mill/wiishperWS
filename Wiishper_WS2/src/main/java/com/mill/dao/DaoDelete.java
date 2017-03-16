package com.mill.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

public interface DaoDelete {
	
	public abstract<T> boolean deleteFrom(Connection conn, String tablename, T object, DaoFactory factory) throws SQLException, NamingException; 

}
