package com.mill.utils;

public class Constants {
	
	//Result States
	public static final int STATE_OK = 0;
	
	//Error codes
	public static final int DATABASE_ERROR = 100;
	public static final int JDNI_ERROR = 101;
	public static final int JSON_ERROR = 102;
	public static final int SHA256_ERROR = 103;
	public static final int NOT_IMPLEMENTED_ERROR = 200;
	public static final int INVALID_OPERATION = 201;
	public static final int EXISTING_USER = 300;
	public static final int AUTHENTICATION_ERROR = 301;
	
	//Operation codes
	public static final int OPER_SIGNUP = 100;
	public static final int OPER_LOGIN = 101;
	public static final int OPER_REFRESH_SESSION = 102;
	public static final int OPER_UPDATE_USER = 103;
	public static final int OPER_SHOW_FRIENDS = 200;
	public static final int OPER_SHOW_PEOPLE = 201;
	public static final int OPER_ADD_FRIEND = 202;
	public static final int OPER_REM_FRIEND = 203;
	public static final int OPER_IS_FRIEND = 204;
	public static final int OPER_SHOW_RAND_PRODS = 300;
	public static final int OPER_LIKE_PRODUCT = 301;
	public static final int OPER_REJECT_PRODUCT = 302;
	public static final int OPER_RECOMEND_PRODS = 303;
	public static final int OPER_SHOW_LIKED = 304;
	public static final int OPER_SHOW_REJECTED = 305;
	public static final int OPER_SHOW_RECOMENDATIONS = 306;
	
	//Table names
	public static final String TABLE_USERS = "users";
	public static final String TABLE_PRODUCTS = "products";
	public static final String TABLE_IMAGES = "images";
	public static final String TABLE_COUNTRIES = "countries";
	public static final String TABLE_STORES = "stores";
	public static final String TABLE_TAGS = "tags";
	public static final String TABLE_CHATS = "chats";
	public static final String TABLE_CHATUSERS = "chatusers";
	public static final String TABLE_FRIENDS = "friends";
	public static final String TABLE_MESSAGES = "messages";
	public static final String TABLE_PRODUCT_IMAGES = "product_images";
	public static final String TABLE_PRODUCT_TAGS = "product_tags";
	public static final String TABLE_RECOMENDATIONS = "recomendations";
	public static final String TABLE_STORE_TAGS = "store_tags";
	public static final String TABLE_TASTES = "tastes";
	
	//Resources
	public static final String DATASOURCE = "java:jboss/datasources/WiishperDS";
	public static final String DATABASE = "MySQL";
	

}
