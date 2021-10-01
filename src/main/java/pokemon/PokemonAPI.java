package pokemon;

import com.google.gson.Gson;
import kong.unirest.ObjectMapper;
import kong.unirest.Unirest;

public class PokemonAPI {
    public static void main(String[] args) {

        // Uses the PokeAPI to collect data on a Pokemon's abilities
        // This API has an enormous amount of other data too! https://pokeapi.co/

        String pokemonName = "charizard";  // replace with name of your choice, or ask user

        String baseUrl = "https://pokeapi.co/api/v2/pokemon/";

        String pokemonDetailsUrl = baseUrl + pokemonName;

        Pokemon pokemon = Unirest.get(pokemonDetailsUrl)
                .asObject(Pokemon.class)
                .getBody();

        if (pokemon != null && pokemon.abilities != null) {
            System.out.println(pokemonName + "'s abilities are: ");
            for (Ability a : pokemon.abilities) {
                System.out.println(a.ability.name);
            }
        }
        else {
            System.out.println("No data returned.");
        }
    }
}
