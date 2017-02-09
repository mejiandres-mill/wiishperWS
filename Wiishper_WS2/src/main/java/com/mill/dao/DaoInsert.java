package com.mill.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DaoInsert {
	
	public abstract<T> boolean putInto(Connection conn, String tablename, T object, boolean running) throws SQLException;

}
