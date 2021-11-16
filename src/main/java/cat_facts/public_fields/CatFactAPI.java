package cat_facts.public_fields;

import kong.unirest.Unirest;

public class CatFactAPI {

    /*
    *  Use the Unirest Library to get a random cat fact
    *  https://catfact.ninja/
    *
    * Convert the response to a CatFact object,
    * which has a fact attribute
    *
    * Unirest uses Gson behind the scenes to convert the JSON response to a Java object.
    * Note the CatFact class
    *
    * */

    public static void main(String[] args) {

        /* Make request, convert response to CatFact object
         The JSON attribute values are used to set the fields in the CatFact object.

         An example response looks like this, with a fact and a length attribute.
         {
            "fact":"A domestic cat can run at speeds of 30 mph.",
            "length":43
         }

         So for this response, a CatFact object will be created. CatFact objects have a
         fact field, and the value of the fact field will be set to the value of the fact
         attribute from the JSON.
         So the CatFact object's fact field will be "A domestic cat can run at speeds of 30 mph."
         and the length field will be set to 43.

         Note that the names and types of the fields in the Java class must match the name and types
         of attributes and values in the JSON.

        */

        String catFactURL = "https://catfact.ninja/fact";
        CatFact catFact = Unirest.get(catFactURL).asObject(CatFact.class).getBody();
        String fact = catFact.fact;
        System.out.println("A cat fact is: " + fact);

    }
}

// More than one class in a Java program can be defined in the same file.  This program is
// small and simple and it's ok to put this class here. If the program was larger, then it would be
// better to move this class to its own file called CatFact.java.

class CatFact {
    public String fact;
    public int length;
}



