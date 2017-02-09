package com.mill.security;

import java.io.IOException;
import java.security.Principal;
import java.sql.Connection;
import java.util.List;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import com.mill.DAL.SqlUtil;
import com.mill.dao.DaoFactory;
import com.mill.dao.impl.MySqlFactory;
import com.mill.models.User;
import com.mill.utils.Constants;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter{
	
	@Override
	public void filter(ContainerRequestContext requestcontext) throws IOException
	{
		
		String authorizationHeader = requestcontext.getHeaderString(HttpHeaders.AUTHORIZATION);
		
		if(authorizationHeader == null || !authorizationHeader.startsWith("Basic "))
			throw new NotAuthorizedException("El mensaje debe tener clave de autorización");
		
		String token = authorizationHeader.substring("Basic ".length()).trim();
		try
		{
			final User user = validateToken(token);
			requestcontext.setSecurityContext(new SecurityContext() {
				
				@Override
				public boolean isUserInRole(String arg0)
				{
					// TODO Auto-generated method stub
					return true;
				}
				
				@Override
				public boolean isSecure()
				{
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public Principal getUserPrincipal()
				{
					return new Principal() {
						
						@Override
						public String getName()
						{
							return user.getEmail();
						}
					};
				}
				
				@Override
				public String getAuthenticationScheme()
				{
					// TODO Auto-generated method stub
					return null;
				}
			});
		}
		catch(Exception e)
		{
			requestcontext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		}
	}
	
	private User validateToken(String token) throws Exception
	{
		DaoFactory factory = new MySqlFactory();
		SqlUtil sqlUtil = new SqlUtil();
		Connection conn = sqlUtil.getConnection();
		List<User> user = factory.getDaoRead().<User>getAllForInputExact(conn, Constants.TABLE_USERS, "apikey", token);
		conn.close();
		if(user.isEmpty())
			throw new Exception();
		else
			return user.get(0);
	}

}
