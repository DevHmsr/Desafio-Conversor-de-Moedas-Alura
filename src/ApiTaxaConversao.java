import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.OptionalDouble;

public class ApiTaxaConversao {

    private static final String API_KEY = "6728244f3d6232ef039e8154";
    private static final Map<String, Double> cacheTaxas = new HashMap<>();
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final Gson gson = new Gson();

    public static OptionalDouble buscarTaxa(String de, String para) {
        String chave = de + "->" + para;
        if (cacheTaxas.containsKey(chave)) {
            return OptionalDouble.of(cacheTaxas.get(chave));
        }

        String urlString = String.format("https://v6.exchangerate-api.com/v6/%s/latest/%s", API_KEY, de);

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlString))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                MoedaResponse resposta = gson.fromJson(response.body(), MoedaResponse.class);
                Double taxa = resposta.getConversion_rates().get(para);
                if (taxa != null) {
                    cacheTaxas.put(chave, taxa);
                    return OptionalDouble.of(taxa);
                }
            } else {
                System.out.println("Erro na requisição: código " + response.statusCode());
            }

        } catch (Exception e) {
            System.out.println("Erro ao acessar API: " + e.getMessage());
        }

        return OptionalDouble.empty();
    }
}
