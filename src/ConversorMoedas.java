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
            System.out.println("7) Sair");
            System.out.print("\nEscolha uma opção: ");

            int opcao = AssistenteInput.lerOpcao(scanner, 1, 7);
            if (opcao == 7) {
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
            }

            double valor = AssistenteInput.lerDoublePositivo(scanner, "\nDigite o valor que deseja converter: ");
            ServicoConversao.realizarConversao(valor, de, para);
        }

        scanner.close();
    }
}