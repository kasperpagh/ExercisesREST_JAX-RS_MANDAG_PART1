/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author pagh
 */
@Path("quote")
public class QuoteEndpoint
{

    private static Map<String, Quote> quotes = new HashMap()
    {
        {
            put("1", new Quote("Friends are kisses blown to us by angels"));
            put("2", new Quote("Do not take life too seriously. You will never get out of it alive"));
            put("3", new Quote("Behind every great man, is a woman rolling her eyes"));
        }
    };

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    Gson gson;

    public QuoteEndpoint()
    {
        gson = new GsonBuilder().setPrettyPrinting().create();

    }
    
       @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON) //"application/json"
    public String getQuote(@PathParam("id") String id) throws NotFoundException {
        Integer ID = Integer.parseInt(id);
        Quote quote = quotes.get(id);
        if (quote ==null)
            throw new NotFoundException("No book with that ISBN");
        return gson.toJson(quote);
    }

}
