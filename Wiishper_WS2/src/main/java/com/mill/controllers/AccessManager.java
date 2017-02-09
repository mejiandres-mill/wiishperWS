package com.mill.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mill.DAL.SqlUtil;
import com.mill.dao.DaoFactory;
import com.mill.exceptions.WSException;
import com.mill.models.User;
import com.mill.utils.Constants;
import com.mill.utils.Message;
import com.mill.utils.Result;
import com.mill.utils.Security;

/**
 * This controller deals with all user signups and updates. Also,
 * this controller is in charge of authenticating users
 *
 */
public class AccessManager {

	private ObjectMapper mapper;
	private DaoFactory factory;
	private SqlUtil sqlUtil;

	public AccessManager(SqlUtil sqlutil, ObjectMapper mapper, DaoFactory factory) throws NamingException
	{
		this.mapper = mapper;
		this.factory = factory;
		this.sqlUtil = sqlutil;
	}

	public Result process(Message message, String username) throws WSException, SQLException, NoSuchAlgorithmException
	{
		try
		{
			switch (message.getOperation())
			{
			case Constants.OPER_SIGNUP:
				return singup(message.getData());
			case Constants.OPER_LOGIN:
				return login(message.getData());
			case Constants.OPER_REFRESH_SESSION:
				throw new WSException(Constants.NOT_IMPLEMENTED_ERROR, "Operación no implementada");
			case Constants.OPER_UPDATE_USER:
				return updateUser(message.getData());
			default:
				throw new WSException(Constants.INVALID_OPERATION, "Operación no válida");
			}
		} catch (JsonParseException e)
		{
			throw new WSException(Constants.JSON_ERROR, "Error transformando JSON");
		} catch (JsonMappingException e)
		{
			throw new WSException(Constants.JSON_ERROR, "Error en mapeo JSON");
		} catch (IOException e)
		{
			throw new WSException(Constants.JSON_ERROR, "Error de flujo de JSON");
		}
	}

	private Result singup(String data) throws JsonParseException, JsonMappingException, IOException, SQLException, NoSuchAlgorithmException
	{
		User user = mapper.readValue(data, User.class);
		Result r = new Result();

		List<User> check = factory.getDaoRead().<User> getAllForInputExact(sqlUtil.getConnection(), Constants.TABLE_USERS,
				"email", user.getEmail());
		if (check.isEmpty())
		{
			user.setPassword(Security.sha256(user.getPassword()));

			boolean success = factory.getDaoInsert().<User> putInto(sqlUtil.getConnection(), Constants.TABLE_USERS,
					user, false);

			r.setState(success ? Constants.STATE_OK : Constants.EXISTING_USER);
			r.setData(success ? mapper.writeValueAsString(user) : "La dirección de correo electrónico ya está en uso");
		} else
		{
			r.setState(Constants.EXISTING_USER);
			r.setData("La dirección de correo electrónico ya está en uso");
		}

		return r;
	}
	
	private Result login(String data) throws JsonParseException, JsonMappingException, IOException, SQLException, NoSuchAlgorithmException
	{
		User user = mapper.readValue(data, User.class);
		Result r = new Result();
		
		List<User> check = factory.getDaoRead().<User> getAllForInputExact(sqlUtil.getConnection(), Constants.TABLE_USERS,
				"email", user.getEmail());
		
		if(!check.isEmpty())
		{
			if(!check.get(0).getPassword().equals(Security.sha256(user.getPassword())))
			{
				user = check.get(0);
				user.setApikey(Security.generateApiKey());
				boolean update = factory.getDaoUpdate().<User>merge(sqlUtil.getConnection(), Constants.TABLE_USERS, user);
				r.setState(update ? Constants.STATE_OK : Constants.DATABASE_ERROR);
				r.setData(update ? mapper.writeValueAsString(user) : "Ocurrió un error de autenticación");
			}
			else
			{
				r.setState(Constants.AUTHENTICATION_ERROR);
				r.setData("La contraseña no es correcta");
			}
			
		}
		else
		{
			r.setState(Constants.AUTHENTICATION_ERROR);
			r.setData("Esta cuenta de correo electrónico no se encuentra registrado");
		}
		return r;
	}
	
	private Result updateUser(String data) throws JsonParseException, JsonMappingException, IOException, SQLException, NoSuchAlgorithmException
	{
		Result r = new Result();
		User user = mapper.readValue(data, User.class);
		
		List<User> check = factory.getDaoRead().<User> getAllForInputExact(sqlUtil.getConnection(), Constants.TABLE_USERS,
				"email", user.getEmail());
		
		if(!check.isEmpty())
		{
			user.setIdusers(check.get(0).getIdusers());
			user.setPassword(Security.sha256(user.getPassword()));
			user.setApikey(Security.generateApiKey());
			boolean update = factory.getDaoUpdate().<User>merge(sqlUtil.getConnection(), Constants.TABLE_USERS, user);
			r.setState(update ? Constants.STATE_OK : Constants.DATABASE_ERROR);
			r.setData(update ? mapper.writeValueAsString(user) : "Ocurrió un error de actualización");
		}
		else
		{
			r.setState(Constants.AUTHENTICATION_ERROR);
			r.setData("Esta cuenta de correo electrónico no se encuentra registrado");
		}
		return r;
	}

}
