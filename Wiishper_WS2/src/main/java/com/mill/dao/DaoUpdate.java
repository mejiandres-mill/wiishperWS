package com.mill.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DaoUpdate {
	
	public abstract<T> boolean merge(Connection conn, String tablename, T object) throws SQLException;

}
