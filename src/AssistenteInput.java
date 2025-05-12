import java.util.Scanner;

public class AssistenteInput {

    public static int lerOpcao(Scanner scanner, int min, int max) {
        while(true){
            if (scanner.hasNextInt()) {
                int opcao = scanner.nextInt();
                if (opcao >= min && opcao <= max) {
                    return opcao;
                }
                System.out.println("\nOpção inválida. Por favor, digite um número correspondente às opções do menu.");
            } else {
                System.out.println("\nOpção inválida. Por favor, digite um número correspondente às opções do menu.");
                scanner.next();
            }
            System.out.println("\nTente novamente: ");
        }
    }

    public static double lerDoublePositivo (Scanner scanner, String mensagem) {
        while (true) {
            System.out.println(mensagem);
            if (scanner.hasNextDouble()) {
                double valor = scanner.nextDouble();
                if (valor > 0) return valor;
                System.out.println("\nValor inválido. O valor a ser convertido precisa ser maior que zero.");
            } else {
                System.out.println("\nValor inválido. O valor a ser convertido precisa ser escrito em caracteres " +
                        "numéricos.");
                scanner.next();
            }
        }
    }
}