/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restWithJaxRsPart1;

/**
 *
 * @author pagh
 */
public class Quote
{
    private String quote;
    private String key;

    public Quote()
    {
    }

    public Quote(String quote, String key)
    {
        this.quote = quote;
        this.key = key;
    }

    public String getQuote()
    {
        return quote;
    }

    public void setQuote(String quote)
    {
        this.quote = quote;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }
        
    
}
