package quotes_async;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.util.concurrent.CompletableFuture;

public class Quotes {
    
    public static void main(String[] args) {
        
        /*
        * Random quote API.
        * https://github.com/lukePeavey/quotable
        *
        * */
        
        String quoteURL = "https://api.quotable.io/random";

        CompletableFuture<HttpResponse<Quote>> future = Unirest
                .get(quoteURL)
                .asObjectAsync(Quote.class, response -> {

                    Quote quoteResponse = response.getBody();
                    String quoteText = quoteResponse.getContent();
                    String quoteAuthor = quoteResponse.getAuthor();
                    System.out.println("Here is your quote says: " + quoteText);
                    System.out.println("By " + quoteAuthor);
            Unirest.shutDown();
        });

        // Notice that this line prints before the quote.
        System.out.println("Request made, waiting for quote...");
        
    }
}

class Quote {
    String content;
    String author;
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "quote='" + content + '\'' +
                '}';
    }
}
