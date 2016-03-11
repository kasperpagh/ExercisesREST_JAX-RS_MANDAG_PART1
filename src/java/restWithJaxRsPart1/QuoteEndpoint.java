/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restWithJaxRsPart1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.QuoteNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.ws.rs.core.Context;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.DELETE;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
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

    Gson gson;

    private Random rand;
    static int counter;
    static Map<String, Quote> quotes = new HashMap()
    {
        {
            put("1", new Quote("Friends are kisses blown to us by angels", "1"));
            put("2", new Quote("Do not take life too seriously. You will never get out of it alive", "2"));
            put("3", new Quote("Behind every great man, is a woman rolling her eyes", "3"));
        }
    };

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public QuoteEndpoint()
    {
        gson = new GsonBuilder().setPrettyPrinting().create();
        rand = new Random();
        counter = 4;

    }

    //Brug id = 0 til at fremprovokere en test af genericExceptionMapper
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON) //"application/json"
    public String getQuote(@PathParam("id") String id) throws QuoteNotFoundException
    {
        Quote quote = quotes.get(id);
        //til test af genericExceptionMapper////
        if (id.equals("0"))
        {
            throw new NullPointerException();
        }
        ////////////////////////////////////////
        if (quote == null)
        {
            System.out.println("here comes ex");
            throw new QuoteNotFoundException("Quote with requested id not found");
        }

        return gson.toJson(quote);
    }

    @GET
    @Path("/random")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandomQuote() throws QuoteNotFoundException
    {
        if (quotes.isEmpty())
        {
            throw new QuoteNotFoundException("Quote with requested id not found");
        }
        return gson.toJson(quotes.get((rand.nextInt(3) + 1) + ""));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createQuote(String JsonQuote)
    {
        Quote quote = gson.fromJson(JsonQuote, Quote.class);
        Facade.createQuote(quote);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public void updateQuote(String JsonQuote)
    {
        Quote quote = gson.fromJson(JsonQuote, Quote.class);
        Facade.updateQuote(quote);
    }

    @DELETE
    @Path("/{id}")
    public void deleteQuote(@PathParam("id") String id)
    {
        Facade.deleteQuote(id);
    }

}
