package com.mill.dao.impl;

import java.sql.SQLException;

import com.mill.dao.DaoDelete;
import com.mill.dao.DaoFactory;
import com.mill.dao.DaoInsert;
import com.mill.dao.DaoRead;
import com.mill.dao.DaoUpdate;

public class MySqlFactory extends DaoFactory {

	@Override
	public DaoInsert getDaoInsert() throws SQLException
	{
		return new MySqlInsert(this);
	}

	@Override
	public DaoRead getDaoRead() throws SQLException
	{
		return new MySqlRead(this);
	}

	@Override
	public DaoDelete getDaoDelete()
	{
		return new MySqlDelete(this);
	}

	@Override
	public DaoUpdate getDaoUpdate()
	{
		return new MySqlUpdate(this);
	}
	
	

}
