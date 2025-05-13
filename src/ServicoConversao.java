import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.OptionalDouble;

public class ServicoConversao {

    public static void realizarConversao(double valor, String de, String para) {
        OptionalDouble taxaOpt = ApiTaxaConversao.buscarTaxa(de, para);
        if (taxaOpt.isEmpty()) {
            System.out.println("\nErro ao obter a taxa de conversão. Tente novamente mais tarde.");
            return;
        }

        double taxa = taxaOpt.getAsDouble();
        double convertido = valor * taxa;

        System.out.printf("\nValor %.2f [%s] convertido para =>>> %.2f [%s]%n", valor, de, convertido, para);

        CriarJson resultado = new CriarJson(valor, de, para, taxa, convertido);
        String json = resultado.toJson();
        salvarJsonEmArquivo(json);
    }

    private static void salvarJsonEmArquivo(String conteudoJson) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String nomeDiretorio = "logs";
        String nomeArquivo = nomeDiretorio + "/conversao_" + timestamp + ".json";

        try {
            new File(nomeDiretorio).mkdirs();
            try (FileWriter writer = new FileWriter(nomeArquivo, true)) {
                writer.write(conteudoJson + System.lineSeparator());
                System.out.println("\nResultado salvo no arquivo: " + nomeArquivo);
            }
        } catch (IOException e) {
            System.out.println("\nErro ao salvar o arquivo: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("\nErro inesperado ao acessar informações do arquivo: " + e.getMessage());
        }
    }
}