import java.net.URI;
import java.net.http.*;

public class PokeApi {

    private static final String BASE = "https://pokeapi.co/api/v2/";
    private final HttpClient client;

    public PokeApi(){
        this.client = HttpClient.newHttpClient();
    }

    public String getMon(String name){
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE + "pokemon/" + name)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200){
                return response.body();
            }
            else{
                System.err.println("Failed to get Mon");
            }
        }
        catch (Exception e){
            System.err.println(e);
        }

        return "NON";
    }
}
