import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        SudokuGenerator generator = new SudokuGenerator();
        generator.generate();
        generator.printBoard();
    }


}
