package com.mill.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mill.dao.QueryType;
import com.mill.models.Chat;
import com.mill.models.ChatUsers;
import com.mill.models.Country;
import com.mill.models.Friendship;
import com.mill.models.Image;
import com.mill.models.Message;
import com.mill.models.Product;
import com.mill.models.ProductImages;
import com.mill.models.ProductTags;
import com.mill.models.Recomendation;
import com.mill.models.Store;
import com.mill.models.StoreTags;
import com.mill.models.Tag;
import com.mill.models.Taste;
import com.mill.models.User;
import com.mill.utils.Constants;

public class MySqlSpecifics {

	public static String queryString(String tablename, long[] keyValues, QueryType type)
	{
		StringBuilder sb = null;

		switch (type)
		{
			case READ:
				sb = new StringBuilder("SELECT * FROM ").append(tablename).append(" WHERE ");
				break;
			case DELETE:
				sb = new StringBuilder("DELETE FROM ").append(tablename).append(" WHERE ");
				break;
			default:
		}
		
		switch(tablename)
		{
			case Constants.TABLE_CHATS:
				sb.append(" idchats = '");
				break;
			case Constants.TABLE_CHATUSERS:
				sb.append(" idchatusers = '");
				break;
			case Constants.TABLE_COUNTRIES:
				sb.append(" idcountries = '");
				break;
			//friender friendee
			case Constants.TABLE_FRIENDS:
				return sb.append(" friender = '").append(keyValues[0]).append("' AND friendee = '")
						.append(keyValues[1]).append("'").toString();
			case Constants.TABLE_IMAGES:
				sb.append(" idimages = '");
				break;
			case Constants.TABLE_MESSAGES:
				sb.append(" idmessages = '");
				break;
			case Constants.TABLE_PRODUCT_IMAGES:
				sb.append(" idproduct_images = '");
				break;
			case Constants.TABLE_PRODUCT_TAGS:
				sb.append(" idproduct_tags = '");
				break;
			case Constants.TABLE_PRODUCTS:
				sb.append(" idproducts = '");
				break;
			// recomender, receipient, product
			case Constants.TABLE_RECOMENDATIONS:
				return sb.append(" recomender = '").append(keyValues[0]).append("' AND receipient = '")
						.append(keyValues[1]).append("' AND products_idproducts = '")
						.append(keyValues[2]).append("'").toString();
			// store tag
			case Constants.TABLE_STORE_TAGS:
				return sb.append(" stores_idstores = '").append(keyValues[0]).append("' AND tags_idtags = '")
						.append(keyValues[1]).append("'").toString();
			case Constants.TABLE_STORES:
				sb.append(" idstores = '");
				break;
			case Constants.TABLE_TAGS:
				sb.append(" idtags = '");
				break;
			//user product
			case Constants.TABLE_TASTES:
				return sb.append(" users_idusers = '").append(keyValues[0]).append("' AND products_idproducts = '")
						.append(keyValues[1]).append("'").toString();
			case Constants.TABLE_USERS:
				sb.append(" idusers = '");
				break;
			default:
				return null;
		}
		
		return sb.append(keyValues[0]).append("'").toString();
	}
	
	public static <T> long[] getPrimaryKeyValues(String tablename, T currentEntity)
	{
		long[] values = null;
		
		switch(tablename)
		{
			case Constants.TABLE_CHATS:
				values = new long[1];
				values[0] = ((Chat) currentEntity).getIdchats();
				break;
			case Constants.TABLE_CHATUSERS:
				values = new long[1];
				values[0] = ((ChatUsers) currentEntity).getIdchatusers();
				break;
			case Constants.TABLE_COUNTRIES:
				values = new long[1];
				values[0] = ((Country) currentEntity).getIdcountries();
				break;
			case Constants.TABLE_FRIENDS:
				values = new long[2];
				values[0] = ((Friendship) currentEntity).getFriender();
				values[1] = ((Friendship) currentEntity).getFriendee();
				break;
			case Constants.TABLE_IMAGES:
				values = new long[1];
				values[0] = ((Image) currentEntity).getIdimages();
				break;
			case Constants.TABLE_MESSAGES:
				values = new long[1];
				values[0] = ((Message) currentEntity).getIdmessages();
				break;
			case Constants.TABLE_PRODUCT_IMAGES:
				values = new long[1];
				values[0] = ((ProductImages) currentEntity).getIdproduct_images();
				break;
			case Constants.TABLE_PRODUCT_TAGS:
				values = new long[1];
				values[0] = ((ProductTags) currentEntity).getIdproduct_tags();
				break;
			case Constants.TABLE_PRODUCTS:
				values = new long[1];
				values[0] = ((Product) currentEntity).getIdproducts();
				break;
			case Constants.TABLE_RECOMENDATIONS:
				values = new long[3];
				values[0] = ((Recomendation) currentEntity).getRecomender();
				values[1] = ((Recomendation) currentEntity).getReceipient();
				values[2] = ((Recomendation) currentEntity).getProduct();
				break;
			case Constants.TABLE_STORE_TAGS:
				values = new long[2];
				values[0] = ((StoreTags) currentEntity).getStore();
				values[1] = ((StoreTags) currentEntity).getTag();
				break;
			case Constants.TABLE_STORES:
				values = new long[1];
				values[0] = ((Store) currentEntity).getIdstores();
				break;
			case Constants.TABLE_TAGS:
				values = new long[1];
				values[0] = ((Tag) currentEntity).getIdtags();
				break;
			case Constants.TABLE_TASTES:
				values = new long[2];
				values[0] = ((Taste) currentEntity).getUser();
				values[1] = ((Taste) currentEntity).getProduct();
				break;
			case Constants.TABLE_USERS:
				values = new long[1];
				values[0] = ((User) currentEntity).getIdusers();
				break;
		}
		return values;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getEntityFromResultSet(String tablename, ResultSet rs, boolean eager) throws SQLException
	{
		switch(tablename)
		{
			case Constants.TABLE_CHATS:
				return (T) ChatDao.getEntity(rs, eager);
			case Constants.TABLE_CHATUSERS:
				return (T) ChatUsersDao.getEntity(rs, eager);
			case Constants.TABLE_COUNTRIES:
				return (T) CountryDao.getEntity(rs, eager);
			case Constants.TABLE_FRIENDS:
				return (T) FriendshipDao.getEntity(rs, eager);
			case Constants.TABLE_IMAGES:
				return (T) ImageDao.getEntity(rs, eager);
			case Constants.TABLE_MESSAGES:
				return (T) MessageDao.getEntity(rs, eager);
			case Constants.TABLE_PRODUCT_IMAGES:
				return (T) ProductImagesDao.getEntity(rs, eager);
			case Constants.TABLE_PRODUCT_TAGS:
				return (T) ProductTagsDao.getEntity(rs, eager);
			case Constants.TABLE_PRODUCTS:
				return (T) ProductDao.getEntity(rs, eager);
			case Constants.TABLE_RECOMENDATIONS:
				return (T) RecomendationDao.getEntity(rs, eager);
			case Constants.TABLE_STORE_TAGS:
				return (T) StoreTagsDao.getEntity(rs, eager);
			case Constants.TABLE_STORES:
				return (T) StoreDao.getEntity(rs, eager);
			case Constants.TABLE_TAGS:
				return (T) TagDao.getEntity(rs, eager);
			case Constants.TABLE_TASTES:
				return (T) TasteDao.getEntity(rs, eager);
			case Constants.TABLE_USERS:
				return (T) UserDao.getEntity(rs, eager);				
			default:
				return null;
		}
	}
	
	public static <T> PreparedStatement getPreparedInsert(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		switch(tablename)
		{
			case Constants.TABLE_CHATS:
				return ChatDao.getPreparedInsert(conn, tablename, currentEntity);
			case Constants.TABLE_CHATUSERS:
				return ChatUsersDao.getPreparedInsert(conn, tablename, currentEntity);
			case Constants.TABLE_COUNTRIES:
				return CountryDao.getPreparedInsert(conn, tablename, currentEntity);
			case Constants.TABLE_FRIENDS:
				return FriendshipDao.getPreparedInsert(conn, tablename, currentEntity);
			case Constants.TABLE_IMAGES:
				return ImageDao.getPreparedInsert(conn, tablename, currentEntity);
			case Constants.TABLE_MESSAGES:
				return MessageDao.getPreparedInsert(conn, tablename, currentEntity);
			case Constants.TABLE_PRODUCT_IMAGES:
				return ProductImagesDao.getPreparedInsert(conn, tablename, currentEntity);
			case Constants.TABLE_PRODUCT_TAGS:
				return ProductTagsDao.getPreparedInsert(conn, tablename, currentEntity);
			case Constants.TABLE_PRODUCTS:
				return ProductDao.getPreparedInsert(conn, tablename, currentEntity);
			case Constants.TABLE_RECOMENDATIONS:
				return RecomendationDao.getPreparedInsert(conn, tablename, currentEntity);
			case Constants.TABLE_STORE_TAGS:
				return StoreTagsDao.getPreparedInsert(conn, tablename, currentEntity);
			case Constants.TABLE_STORES:
				return StoreDao.getPreparedInsert(conn, tablename, currentEntity);
			case Constants.TABLE_TAGS:
				return TagDao.getPreparedInsert(conn, tablename, currentEntity);
			case Constants.TABLE_TASTES:
				return TasteDao.getPreparedInsert(conn, tablename, currentEntity);
			case Constants.TABLE_USERS:
				return UserDao.getPreparedInsert(conn, tablename, currentEntity);
			default:
				return null;
		}
	}
	
	public static <T> PreparedStatement getPreparedUpdate(Connection conn, String tablename, T currentEntity) throws SQLException
	{
		switch(tablename)
		{
			case Constants.TABLE_CHATS:
				return ChatDao.getPreparedUpdate(conn, tablename, currentEntity);
			case Constants.TABLE_CHATUSERS:
				return ChatUsersDao.getPreparedUpdate(conn, tablename, currentEntity);
			case Constants.TABLE_COUNTRIES:
				return CountryDao.getPreparedUpdate(conn, tablename, currentEntity);
			case Constants.TABLE_FRIENDS:
				return FriendshipDao.getPreparedUpdate(conn, tablename, currentEntity);
			case Constants.TABLE_IMAGES:
				return ImageDao.getPreparedUpdate(conn, tablename, currentEntity);
			case Constants.TABLE_MESSAGES:
				return MessageDao.getPreparedUpdate(conn, tablename, currentEntity);
			case Constants.TABLE_PRODUCT_IMAGES:
				return ProductImagesDao.getPreparedUpdate(conn, tablename, currentEntity);
			case Constants.TABLE_PRODUCT_TAGS:
				return ProductTagsDao.getPreparedUpdate(conn, tablename, currentEntity);
			case Constants.TABLE_PRODUCTS:
				return ProductDao.getPreparedUpdate(conn, tablename, currentEntity);
			case Constants.TABLE_RECOMENDATIONS:
				return RecomendationDao.getPreparedUpdate(conn, tablename, currentEntity);
			case Constants.TABLE_STORE_TAGS:
				return StoreTagsDao.getPreparedUpdate(conn, tablename, currentEntity);
			case Constants.TABLE_STORES:
				return StoreDao.getPreparedUpdate(conn, tablename, currentEntity);
			case Constants.TABLE_TAGS:
				return TagDao.getPreparedUpdate(conn, tablename, currentEntity);
			case Constants.TABLE_TASTES:
				return TasteDao.getPreparedUpdate(conn, tablename, currentEntity);
			case Constants.TABLE_USERS:
				return UserDao.getPreparedUpdate(conn, tablename, currentEntity);
			default:
				return null;
		}
	}

}
