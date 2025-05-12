import java.util.OptionalDouble;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class ConversorMoedas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n****************************************");
            System.out.println("\nSeja bem-vindo(a) ao Conversor de Moedas!\n");
            System.out.println("Selecione uma opção:\n");
            System.out.println("1) Dólar => Peso argentino");
            System.out.println("2) Peso argentino => Dólar");
            System.out.println("3) Dólar => Real brasileiro");
            System.out.println("4) Real brasileiro => Dólar");
            System.out.println("5) Dólar => Euro");
            System.out.println("6) Euro => Dólar");
            System.out.println("7) Sair");
            System.out.print("\nEscolha uma opção válida: ");

            int opcao;
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
            } else {
                System.out.println("\nVocê digitou uma opção inválida. Por favor, digite um número.");
                scanner.next();
                continue;
            }


            if (opcao == 7) {
                System.out.println("\nSaindo...");
                break;
            }

            String de, para;

            switch (opcao) {
                case 1 -> { de = "USD"; para = "ARS"; }
                case 2 -> { de = "ARS"; para = "USD"; }
                case 3 -> { de = "USD"; para = "BRL"; }
                case 4 -> { de = "BRL"; para = "USD"; }
                case 5 -> { de = "USD"; para = "EUR"; }
                case 6 -> { de = "EUR"; para = "USD"; }
                default -> {
                    System.out.println("\nOpção inválida.");
                    continue;
                }
            }

            double valor;
            while (true) {
                System.out.print("\nDigite o valor que deseja converter: ");
                if (scanner.hasNextDouble()) {
                    valor = scanner.nextDouble();
                    if (valor <= 0) {
                        System.out.println("\nValor inválido. Digite um número maior que zero.");
                    } else {
                        break;
                    }
                } else {
                    System.out.println("\nEntrada inválida. Por favor, digite um número.");
                    scanner.next();
                }
            }

            OptionalDouble taxaOpt = ApiTaxaConversao.buscarTaxa(de, para);
            if (taxaOpt.isEmpty()) {
                System.out.println("\nErro ao obter a taxa de conversão.");
            } else {
                double taxa = taxaOpt.getAsDouble();
                double convertido = valor * taxa;

                System.out.printf("\nValor %.2f [%s] correspondente ao valor final de =>>> %.2f [%s]%n", valor, de,
                        convertido, para);

                CriarJson resultado = new CriarJson(valor, de, para, taxa, convertido);
                String json = resultado.toJson();
                salvarJsonEmArquivo(json);
            }
        }

        scanner.close();
    }
    private static void salvarJsonEmArquivo(String conteudoJson) {
        try (FileWriter writer = new FileWriter("conversoes.jsonl", true)) {
            writer.write(conteudoJson + System.lineSeparator());
            System.out.println("\nResultado salvo no arquivo " + "conversoes.jsonl");
        } catch (IOException e) {
            System.out.println("\nErro ao salvar o arquivo: " + e.getMessage());
        }
    }
}