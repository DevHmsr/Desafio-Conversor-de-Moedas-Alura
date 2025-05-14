import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class ConversorMoedas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n********** Conversor de Moedas **********");
            System.out.println("1) Dólar => Peso Argentino");
            System.out.println("2) Peso Argentino => Dólar");
            System.out.println("3) Dólar => Real Brasileiro");
            System.out.println("4) Real Brasileiro => Dólar");
            System.out.println("5) Dólar => Peso Colombiano");
            System.out.println("6) Peso Colombiano => Dólar");
            System.out.println("7) Dólar => Boliviano");
            System.out.println("8) Boliviano => Dólar");
            System.out.println("9) Dólar => Peso Chileno");
            System.out.println("10) Peso Chileno => Dólar");
            System.out.println("11) Ver histórico de conversões");
            System.out.println("12) Sair");
            System.out.print("\nEscolha uma opção: ");

            int opcao = AssistenteInput.lerOpcao(scanner, 1, 12);
            if (opcao == 12) {
                System.out.println("Saindo...");
                break;
            }

            String de = "", para = "";
            switch (opcao) {
                case 1 -> { de = "USD"; para = "ARS"; }
                case 2 -> { de = "ARS"; para = "USD"; }
                case 3 -> { de = "USD"; para = "BRL"; }
                case 4 -> { de = "BRL"; para = "USD"; }
                case 5 -> { de = "USD"; para = "COP"; }
                case 6 -> { de = "COP"; para = "USD"; }
                case 7 -> { de = "USD"; para = "BOB"; }
                case 8 -> { de = "BOB"; para = "USD"; }
                case 9 -> { de = "USD"; para = "CLP"; }
                case 10 -> { de = "CLP"; para = "USD"; }
                case 11 -> {
                    exibirHistoricoConversoes();
                    continue;
                }
            }

            double valor = AssistenteInput.lerDoublePositivo(scanner, "\nDigite o valor que deseja converter: ");
            ServicoConversao.realizarConversao(valor, de, para);
        }

        scanner.close();
    }

    private static void exibirHistoricoConversoes() {
        System.out.println("\n--- Histórico de Conversões ---");

        File pastaLogs = new File("logs");
        File[] arquivos = pastaLogs.listFiles((dir, name) -> name.endsWith(".json"));

        if (arquivos == null || arquivos.length == 0) {
            System.out.println("Nenhuma conversão registrada ainda.");
            return;
        }

        Arrays.sort(arquivos, Comparator.comparingLong(File::lastModified).reversed());

        Gson gson = new Gson();
        int count = 0;

        for (File arquivo : arquivos) {
            try (Scanner leitor = new Scanner(arquivo)) {
                StringBuilder conteudo = new StringBuilder();
                while (leitor.hasNextLine()) {
                    conteudo.append(leitor.nextLine());
                }

                CriarJson conversao = gson.fromJson(conteudo.toString(), CriarJson.class);

                System.out.printf(
                        "[%s] %8.2f %-4s => %10.2f %-4s (Taxa: %.4f)%n",
                        conversao.getDataHora(),
                        conversao.getValorOriginal(),
                        conversao.getMoedaOrigem(),
                        conversao.getValorConvertido(),
                        conversao.getMoedaDestino(),
                        conversao.getTaxa()
                );

                count++;
            } catch (IOException e) {
                System.out.println("Erro ao ler o arquivo: " + arquivo.getName());
            }

            if (count >= 5) break;
        }

        if (count == 0) {
            System.out.println("Nenhuma conversão registrada nos arquivos.");
        }
    }
}
