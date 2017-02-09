package com.mill.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DaoDelete {
	
	public abstract<T> boolean deleteFrom(Connection conn, String tablename, T object) throws SQLException; 

}
