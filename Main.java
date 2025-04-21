import java.net.http.*;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import com.google.gson.Gson;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PokeApiClient client = new PokeApiClient();

        try{
            File file = new File("./Resources/Cache.json");
            Scanner pokemon = new Scanner(System.in);
            pokemon = new Scanner(file);
            Gson gson = new Gson();
            FileWriter writer = new FileWriter("./Resources/Cache.json");
            FileReader reader = new FileReader("./Resources/Cache.json");

            writer.write("");

            /*
            Converting obj to json
            String myJson = gson.toJson(object);
            myJson becomes a json string

            read:
            ObjectType newObj = gson.fromJson(reader, ObjectType);
             */

            writer.write(client.getPokemon("ditto"));
            pokemon = new Scanner(file);
            pokemon.findInLine("name");

            writer.close();



        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }


    }
}
