import com.google.gson.Gson;
import kong.unirest.ObjectMapper;
import kong.unirest.Unirest;

public class CatFactAPI {
    
    public static void main(String[] args) {
    
        Unirest.config().setObjectMapper(new ObjectMapper() {
            private Gson gson = new Gson();
            @Override
            public <T> T readValue(String s, Class<T> aClass) {
                return gson.fromJson(s, aClass);
            }
        
            @Override
            public String writeValue(Object o) {
                return gson.toJson(o);
            }
        });
        
        String url = "https://catfact.ninja/fact";
        
        CatFact catFact = Unirest.get(url).asObject(CatFact.class).getBody();
        System.out.println("A random cat fact is: " + catFact.fact);
    }
}

class CatFact {
    public String fact;
    public int length;
}


