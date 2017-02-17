package com.mill;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.mill.controllers.CentralProcessor;
import com.mill.exceptions.WSException;
import com.mill.security.Secured;
import com.mill.utils.Constants;
import com.mill.utils.Message;
import com.mill.utils.Result;

@ApplicationPath("/wiishper")
@Path("/ws")
public class Wiishper_WS extends Application {
	
	private CentralProcessor processor;

	public Wiishper_WS()
	{
		System.out.println("******* COnstructor ******");
		try
		{
			processor = new CentralProcessor();
		} catch (NamingException e)
		{
			e.printStackTrace();
		}
	}

	@GET
	@Path("/{param}")
	public Response printMessage(@PathParam("param") String msg)
	{
		String result = "Result example : " + msg;

		return Response.status(200).entity(result).build();
	}

	@POST
	@Secured
	@Path("/process")
	@Consumes("application/json")
	@Produces("application/json")
	public Response process(Message message, @Context SecurityContext securityContext)
	{
		Principal principal = securityContext.getUserPrincipal();
		String username = principal.getName();
		try
		{
			return Response.status(200).entity(processor.process(message, username)).build();
		} catch (Exception e)
		{
			e.printStackTrace();
			Result result = new Result();
			if (e instanceof SQLException)
			{
				result.setState(Constants.DATABASE_ERROR);
				result.setData(e.getMessage());
			} else if (e instanceof NamingException)
			{
				result.setState(Constants.JDNI_ERROR);
				result.setData(e.getMessage());

			} else if (e instanceof WSException)
			{
				WSException wse = (WSException) e;
				result.setState(wse.getErrorCode());
				result.setData(wse.getMessage());
			}else if(e instanceof NoSuchAlgorithmException)
			{
				result.setState(Constants.SHA256_ERROR);
				result.setData("Error de seguridad, no se encontró el algoritmo");
			}else if(e instanceof NotAuthorizedException)
			{
				result.setState(Constants.AUTHENTICATION_ERROR);
				result.setData(e.getMessage());
				return Response.status(Response.Status.UNAUTHORIZED).entity(result).build();
			}
			else
			{
				result.setState(0);
				result.setData("Ocurrió un error inesperado");
			}
			System.out.println("Finish processing...");
			return Response.status(500).entity(result).build();
		}

	}

}
