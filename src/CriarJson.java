import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CriarJson {
    private double valorOriginal;
    private String moedaOrigem;
    private String moedaDestino;
    private double taxa;
    private double valorConvertido;
    private String dataHora;

    public CriarJson(double valorOriginal, String moedaOrigem, String moedaDestino, double taxa, double valorConvertido) {
        this.valorOriginal = valorOriginal;
        this.moedaOrigem = moedaOrigem;
        this.moedaDestino = moedaDestino;
        this.taxa = taxa;
        this.valorConvertido = valorConvertido;
        this.dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String toJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

    // Getters
    public double getValorOriginal() {
        return valorOriginal;
    }
    public String getMoedaOrigem() {
        return moedaOrigem;
    }
    public String getMoedaDestino() {
        return moedaDestino;
    }
    public double getTaxa() {
        return taxa;
    }
    public double getValorConvertido() {
        return valorConvertido;
    }
    public String getDataHora() {
        return dataHora;
    }
}
