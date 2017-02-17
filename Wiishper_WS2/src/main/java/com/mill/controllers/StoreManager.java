package com.mill.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mill.DAL.SqlUtil;
import com.mill.dao.DaoFactory;
import com.mill.exceptions.WSException;
import com.mill.models.Store;
import com.mill.models.StoreTags;
import com.mill.models.Tag;
import com.mill.utils.Constants;
import com.mill.utils.Message;
import com.mill.utils.Result;

public class StoreManager {
	
	private ObjectMapper mapper;
	private DaoFactory factory;
	private SqlUtil sqlUtil;
	
	public StoreManager(SqlUtil sqlUtil, ObjectMapper mapper, DaoFactory factory)
	{
		this.factory = factory;
		this.mapper = mapper;
		this.sqlUtil = sqlUtil;
	}
	
	public Result process(Message message, String username) throws SQLException, WSException
	{
		try
		{
			switch(message.getOperation())
			{
				case Constants.OPER_ADD_STORE:
					return addStore(message.getData(), username);
				case Constants.OPER_UPDATE_STORE:
					return updateStore(message.getData(), username);
				case Constants.OPER_ADD_STORE_TAG:
					return addTag(message.getData(), username);
				default:
					throw new WSException(Constants.INVALID_OPERATION, "Operación no válida");
			}
		}catch (JsonParseException e)
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
	
	private Result addStore(String data, String username) throws JsonParseException, JsonMappingException, IOException, SQLException
	{
		Result r = new Result();
		Store store = mapper.readValue(data, Store.class);
		Connection conn = sqlUtil.getConnection();
		boolean success = factory.getDaoInsert().<Store>putInto(conn, Constants.TABLE_STORES, store, false);
		r.setState(success ? Constants.STATE_OK : Constants.DATABASE_ERROR);
		r.setData(success ? "Tienda agregada" : "Error de base de datos");
		return r;
	}
	
	private Result updateStore(String data, String username) throws JsonParseException, JsonMappingException, IOException, SQLException
	{
		Result r = new Result();
		Store store = mapper.readValue(data, Store.class);
		Connection conn = sqlUtil.getConnection();
		List<Store> stores = factory.getDaoRead().<Store>getAllForInputExact(conn, Constants.TABLE_STORES, "name", store.getName());
		conn.close();
		boolean success;
		conn = sqlUtil.getConnection();
		if(stores.isEmpty())
		{
			success = factory.getDaoInsert().<Store>putInto(conn, Constants.TABLE_STORES, store, false);
		}
		else
		{
			store.setIdstores(stores.get(0).getIdstores());
			success = factory.getDaoUpdate().<Store>merge(conn, Constants.TABLE_STORES, store);
		}
		r.setState(success ? Constants.STATE_OK : Constants.DATABASE_ERROR);
		r.setData(success ? "Tienda actualizada" : "Error de base de datos");
		return r;
	}
	
	@SuppressWarnings("unchecked")
	private Result addTag(String data, String username) throws SQLException, JsonParseException, JsonMappingException, IOException
	{
		Result r = new Result();
		Connection conn = sqlUtil.getConnection();
		Map<String, Object> map = mapper.readValue(data, Map.class);
		List<Tag> tags = factory.getDaoRead().<Tag> getAllForInputExact(conn, Constants.TABLE_TAGS, "name", (String) map.get("name"));
		conn.close();
		Tag tag = null;
		if(tags.isEmpty())
		{
			conn = sqlUtil.getConnection();
			tag = new Tag();
			tag.setName((String) map.get("name")); 
			factory.getDaoInsert().<Tag>putInto(conn, Constants.TABLE_TAGS, tag, false);
			conn = sqlUtil.getConnection();
			tags = factory.getDaoRead().<Tag> getAllForInputExact(conn, Constants.TABLE_TAGS, "name", tag.getName());
			conn.close();
		}
		tag = tags.get(0);
		conn = sqlUtil.getConnection();
		StoreTags st = new StoreTags();
		int idstores =  (int) map.get("idstores");
		st.setStore((long) idstores);
		st.setTag(tag.getIdtags());
		boolean success = factory.getDaoInsert().<StoreTags>putInto(conn, Constants.TABLE_STORE_TAGS, st, false);
		
		r.setState(success ? Constants.STATE_OK : Constants.DATABASE_ERROR);
		r.setData(success ? "Tag agregado" : "Error de base de datos");
		return r;
	}

}
