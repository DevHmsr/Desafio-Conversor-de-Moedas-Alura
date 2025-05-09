import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CriarJson {
    private double valorOriginal;
    private String moedaOrigem;
    private String moedaDestino;
    private double taxa;
    private double valorConvertido;

    public CriarJson(double valorOriginal, String moedaOrigem, String moedaDestino, double taxa, double valorConvertido) {
        this.valorOriginal = valorOriginal;
        this.moedaOrigem = moedaOrigem;
        this.moedaDestino = moedaDestino;
        this.taxa = taxa;
        this.valorConvertido = valorConvertido;
    }

    public String toJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}