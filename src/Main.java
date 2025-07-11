import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final SudokuGenerator generator = new SudokuGenerator();
    public static void main(String[] args) {

        generator.generate();


        while (true){
            generator.printBoard();
            System.out.println("Selecione a opção que deseja");
            System.out.println("1 - Adcionar número");
            System.out.println("2 - Remover número");
            System.out.println("3 - Sair");
            int option = scanner.nextInt();

            switch (option){
                case 1 -> AddNumber();
                case 2 -> remNumber();
                case 3 -> System.exit(0);
            }
        }
    }

    private static void AddNumber() {
        while (true){
            System.out.println("Selecione a opção que deseja");
            System.out.println("Digite o número:");
            int number = scanner.nextInt();
            System.out.println("Digite a linha:");
            int row = scanner.nextInt();
            System.out.println("Digite a coluna:");
            int coll = scanner.nextInt();

            generator.addNumber(number, row, coll);
            boolean verification = generator.isCompeted();
            if (verification) {
                gameCompleted();
            }

            return;
        }
    }

    private static void remNumber(){
        while (true){
            System.out.println("Selecione a opção que deseja");
            System.out.println("Digite a linha:");
            int row = scanner.nextInt();
            System.out.println("Digite a coluna:");
            int coll = scanner.nextInt();

            generator.remNumber(row, coll);
            return;
        }
    }

    private static void gameCompleted(){
        System.out.println("Você completou o jogo");
        generator.printBoard();
        System.exit(0);
    }


}
