package com.mill.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mill.DAL.SqlUtil;
import com.mill.dao.DaoFactory;
import com.mill.exceptions.WSException;
import com.mill.models.Product;
import com.mill.models.User;
import com.mill.utils.Constants;
import com.mill.utils.Message;
import com.mill.utils.Result;

public class ProductManager {

	private ObjectMapper mapper;
	private DaoFactory factory;
	private SqlUtil sqlUtil;

	public ProductManager(SqlUtil sqlUtil, ObjectMapper mapper, DaoFactory factory)
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
				case Constants.OPER_SHOW_RAND_PRODS:
					throw new WSException(Constants.NOT_IMPLEMENTED_ERROR, "operación no implementada");
				case Constants.OPER_LIKE_PRODUCT:
					throw new WSException(Constants.NOT_IMPLEMENTED_ERROR, "operación no implementada");
				case Constants.OPER_REJECT_PRODUCT:
					throw new WSException(Constants.NOT_IMPLEMENTED_ERROR, "operación no implementada");
				case Constants.OPER_RECOMEND_PRODS:
					throw new WSException(Constants.NOT_IMPLEMENTED_ERROR, "operación no implementada");
				case Constants.OPER_SHOW_LIKED:
					throw new WSException(Constants.NOT_IMPLEMENTED_ERROR, "operación no implementada");
				case Constants.OPER_SHOW_REJECTED:
					throw new WSException(Constants.NOT_IMPLEMENTED_ERROR, "operación no implementada");
				case Constants.OPER_SHOW_RECOMENDATIONS:
					throw new WSException(Constants.NOT_IMPLEMENTED_ERROR, "operación no implementada");
				case Constants.OPER_ADD_PRODUCT:
					return addProduct(message.getData(), username);
				case Constants.OPER_SHOW_PRODUCT:
					throw new WSException(Constants.NOT_IMPLEMENTED_ERROR, "operación no implementada");
				case Constants.OPER_HIDE_PRODUCT:
					throw new WSException(Constants.NOT_IMPLEMENTED_ERROR, "operación no implementada");
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
	
	private Result addProduct(String data, String username) throws SQLException, JsonParseException, JsonMappingException, IOException
	{
		Result r = new Result();
		Connection conn = sqlUtil.getConnection();
		List<User> user = factory.getDaoRead().<User>getAllForInputExact(conn, Constants.TABLE_USERS, "email", username);
		conn.close();
		
		Product product = mapper.readValue(data, Product.class);
		
		boolean success = factory.getDaoInsert().<Product>putInto(conn, Constants.TABLE_PRODUCTS, product, false);
		
		r.setState(success ? Constants.STATE_OK : Constants.DATABASE_ERROR);
		r.setData(success ? "Producto agregado" : "Error de base de datos");
		
		return r;
	}
	
	private Result showRandomProducts(String data, String username) throws SQLException
	{
		Result r = new Result();
		Connection conn = sqlUtil.getConnection();
		List<User> user = factory.getDaoRead().<User>getAllForInputExact(conn, Constants.TABLE_USERS, "email", username);
		conn.close();
		
		//List<Product> prods = sqlUtil.executeDBOperation(query, tablename, params)
		return r;
	}

}
