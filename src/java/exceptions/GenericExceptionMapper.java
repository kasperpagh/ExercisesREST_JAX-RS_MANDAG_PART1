/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author kaspe
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>
{

    Gson g = new GsonBuilder().setPrettyPrinting().create();
    @Context
    ServletContext context;

    @Override
    public Response toResponse(Throwable e)
    {
        ErrorMessage err = new ErrorMessage("Internal server Error, we are very sorry for the inconvenience", 500);
        Response out = Response.status(500).entity(g.toJson(err)).type(MediaType.APPLICATION_JSON).build();
        return out;
    }

}
