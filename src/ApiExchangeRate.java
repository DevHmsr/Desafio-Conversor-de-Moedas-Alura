import com.google.gson.Gson;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.OptionalDouble;

public class ApiExchangeRate {
    private static final String API_KEY = "6728244f3d6232ef039e8154";

    public static OptionalDouble buscarTaxa(String de, String para) {
        try {
            String urlString = String.format("https://v6.exchangerate-api.com/v6/%s/latest/%s", API_KEY, de);
            URL url = new URL(urlString);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            if (conexao.getResponseCode() == 200) {
                InputStreamReader reader = new InputStreamReader(conexao.getInputStream());
                MoedaResponse resposta = new Gson().fromJson(reader, MoedaResponse.class);
                Double taxa = resposta.getConversion_rates().get(para);
                if (taxa != null) {
                    return OptionalDouble.of(taxa);
                }
            } else {
                System.out.println("Erro na requisição: " + conexao.getResponseCode());
            }
        } catch (Exception e) {
            System.out.println("Erro ao acessar API: " + e.getMessage());
        }
        return OptionalDouble.empty();
    }
}