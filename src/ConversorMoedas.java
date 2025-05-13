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
            System.out.println("1) Dólar => Peso argentino");
            System.out.println("2) Peso argentino => Dólar");
            System.out.println("3) Dólar => Real brasileiro");
            System.out.println("4) Real brasileiro => Dólar");
            System.out.println("5) Dólar => Peso colombiano");
            System.out.println("6) Peso colombiano => Dólar");
            System.out.println("7) Ver histórico de conversões");
            System.out.println("8) Sair");
            System.out.print("\nEscolha uma opção: ");

            int opcao = AssistenteInput.lerOpcao(scanner, 1, 8);
            if (opcao == 8) {
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
                case 7 -> {
                    exibirHistoricoConversoes();
                    continue;}
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

        // Ordena os arquivos do mais recente para o mais antigo
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

                // Exibição em formato textual amigável
                System.out.printf(
                        "%.2f %s => %.2f %s (Taxa: %.4f)%n",
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
