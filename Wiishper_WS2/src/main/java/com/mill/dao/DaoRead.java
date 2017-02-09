package com.mill.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DaoRead {
	
	public abstract<T> List<T> getAll(Connection conn, String tablename) throws SQLException;
	
	public abstract<T> List<T> getAllForInput(Connection conn, String tablename, String columnName, String searchValue) throws SQLException;
	
	public abstract<T> List<T> getAllForInputExact(Connection conn, String tablename, String columnName, String searchValue) throws SQLException;
	
	public abstract<T> T get(Connection conn, String tablename, long[] keyValues) throws SQLException;
	
	public abstract<T> boolean exists(Connection conn, String tablename, long[] keyValues) throws SQLException;
	
	public abstract<T> boolean exists(Connection conn, String tablename, T current) throws SQLException;

}
