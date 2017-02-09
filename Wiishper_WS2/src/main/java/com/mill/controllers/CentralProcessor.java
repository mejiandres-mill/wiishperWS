package com.mill.controllers;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mill.DAL.SqlUtil;
import com.mill.dao.DaoFactory;
import com.mill.exceptions.WSException;
import com.mill.utils.Constants;
import com.mill.utils.Message;
import com.mill.utils.Result;

public class CentralProcessor {
	
	private AccessManager accessManager;
	private PeopleManager peopleManager;
	private ObjectMapper mapper;
	private DaoFactory factory;
	private SqlUtil sqlUtil;
	
	public CentralProcessor() throws NamingException
	{
		sqlUtil = new SqlUtil();
		mapper = new ObjectMapper();
		factory = DaoFactory.factoryProducer(Constants.DATABASE);
		accessManager = new AccessManager(sqlUtil, mapper, factory);
		peopleManager = new PeopleManager(sqlUtil, mapper, factory);
	}
	
	public Result process(Message message, String username) throws WSException, SQLException, NoSuchAlgorithmException
	{
		int operation = message.getOperation() / 100;
		switch(operation)
		{
			case 1:
				return accessManager.process(message, username);
			case 2:
				return peopleManager.process(message, username);
			default:
				throw new WSException(Constants.INVALID_OPERATION, "Operación no válida");
		}
	}
	

}
