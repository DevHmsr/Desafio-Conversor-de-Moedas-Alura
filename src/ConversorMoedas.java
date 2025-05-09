import java.util.OptionalDouble;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class ConversorMoedas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n****************************************");
            System.out.println("Seja bem-vindo(a) ao Conversor de Moeda!\n");
            System.out.println("Selecione uma opção:\n");
            System.out.println("1) Dólar => Peso argentino");
            System.out.println("2) Peso argentino => Dólar");
            System.out.println("3) Dólar => Real brasileiro");
            System.out.println("4) Real brasileiro => Dólar");
            System.out.println("5) Dólar => Peso colombiano");
            System.out.println("6) Peso colombiano => Dólar");
            System.out.println("7) Sair");
            System.out.print("Escolha uma opção válida: ");
            System.out.print("Escolha uma opção válida: ");
            int opcao = scanner.nextInt();

            if (opcao == 7) {
                System.out.println("Saindo...");
                break;
            }

            String de, para;

            switch (opcao) {
                case 1 -> { de = "USD"; para = "ARS"; }
                case 2 -> { de = "ARS"; para = "USD"; }
                case 3 -> { de = "USD"; para = "BRL"; }
                case 4 -> { de = "BRL"; para = "USD"; }
                case 5 -> { de = "USD"; para = "COP"; }
                case 6 -> { de = "COP"; para = "USD"; }
                default -> {
                    System.out.println("Opção inválida.");
                    continue;  // Volta ao menu sem pedir valor
                }
            }

            System.out.print("Digite o valor que deseja converter: ");
            double valor = scanner.nextDouble();
            if (valor <= 0) {
                System.out.println("Valor inválido para conversão.");
                continue;
            }

            OptionalDouble taxaOpt = ApiExchangeRate.buscarTaxa(de, para);
            if (taxaOpt.isEmpty()) {
                System.out.println("Erro ao obter a taxa de conversão.");
            } else {
                double taxa = taxaOpt.getAsDouble();
                double convertido = valor * taxa;
                CriarJson resultado = new CriarJson(valor, de, para, taxa, convertido);
                String json = resultado.toJson();
                System.out.println(json);
                salvarJsonEmArquivo(json);
            }
        }

        scanner.close();
    }
    private static void salvarJsonEmArquivo(String conteudoJson) {
        try (FileWriter writer = new FileWriter("conversoes.jsonl", true)) {
            writer.write(conteudoJson + System.lineSeparator());
            System.out.println("Resultado salvo em " + "conversoes.jsonl");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }
}