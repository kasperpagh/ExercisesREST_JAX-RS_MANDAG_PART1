/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restWithJaxRsPart1;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kaspe
 */
public class Facade
{

 public static void createQuote(Quote quote)
 {
     String key = QuoteEndpoint.counter + "";
     QuoteEndpoint.counter++;
     QuoteEndpoint.quotes.put(quote.getKey(), quote);
 }
 
 public static void updateQuote(Quote quote)
 {
     QuoteEndpoint.quotes.put(quote.getKey(), quote);
 }
 
 public static void deleteQuote(String key)
 {
     QuoteEndpoint.quotes.remove(key);
 }

}
