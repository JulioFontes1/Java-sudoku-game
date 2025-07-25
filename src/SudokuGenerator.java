import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SudokuGenerator {
    private boolean startedGame = false;
    private int[][] board = new int[9][9];
    private int[][] hiddenBoard = new int[9][9];
    private int[][] gameBoard = new int[9][9];
    List<Integer> numbers;
    Random random = new Random();

    public int[][] generate(){
        fillBoard();
        return board;
    }

    public boolean hasTable(){
        if(!startedGame){
            startedGame = true;
            return false;
        }

        return true;
    }

    private boolean fillBoard(){
        for (int row = 0; row < 9; row++) {
            for (int coll = 0; coll < 9; coll++) {
                if(board[row][coll] == 0){
                    numbers = getShuffledNumbers();

                    for (int num : numbers) {
                        if (isValid(row, coll, num)) {
                            board[row][coll] = num;
                            if (fillBoard()) return true;
                            board[row][coll] = 0;
                        }
                    }
                    return false;
                }
            }
        }

        return true;
    }

    private List<Integer> getShuffledNumbers() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 9; i++) list.add(i);
        Collections.shuffle(list);
        return list;
    }

    private boolean isValid(int row, int coll, int num){
        for (int c = 0; c < 9; c++) {
            if (board[row][c] == num) return false;
        }

        for (int r = 0; r < 9; r++) {
            if (board[r][coll] == num) return false;
        }

        int blockRow = row - row % 3;
        int blockCol = coll - coll % 3;
        for (int r = blockRow; r < blockRow + 3; r++) {
            for (int c = blockCol; c < blockCol + 3; c++) {
                if (board[r][c] == num) return false;
            }
        }

        return true;
    }

    public void printBoard(){
        if(!hasTable()){
            hidden();
        }
        System.out.println("------------------------------");
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (col== 0) System.out.print(" | ");

                int num = gameBoard[row][col];
                String value;

                if(num == 0){
                    value = "  ";
                } else if (hasDoubled(num, row, col)) {
                    value = num + "*";
                } else if(num == hiddenBoard[row][col]){
                    value = String.valueOf(num) + " ";
                }else {
                    value = num + "_";
                }
                System.out.print(value);
                if ((col + 1)%3 == 0) System.out.print(" | ");
            }
            System.out.println();
            if((row + 1)%3 == 0) System.out.println("------------------------------");
        }
    }
    
    public void hidden(){
        int max = 3;
        int min = 1;
        int hidden = 0;
        int randomNumber = random.nextInt(max - min + 1) + min;
        hiddenBoard = copyBoard(board);

        while (hidden < randomNumber){
            int row = random.nextInt(9);
            int col = random.nextInt(9);

            if (hiddenBoard[row][col] != 0){
                hiddenBoard[row][col] = 0;
                hidden++;
            }
        }
        gameBoard = copyBoard(hiddenBoard);
    }

    public boolean isCompeted(){
        int nCompleted = 0;
        for (int row = 0; row < 9; row++) {
            for (int coll = 0; coll < 9; coll++) {
                if(gameBoard[row][coll] == board[row][coll]){
                    nCompleted ++;
                }
            }
        }

        return nCompleted == 81;
    }

    public boolean hasDoubled(int num, int row, int coll){
        for (int c = 0; c < 9; c++) {
            if(c != coll && gameBoard[row][c] == num) return true;

        }

        for (int r = 0; r < 9; r++) {
            if(r != row && gameBoard[r][coll] == num) return true;
        }

        int startRow = row - row % 3;
        int startColl = coll - coll % 3;

        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startColl; c < startColl + 3; c++) {
                if((r != row || c != coll) && gameBoard[r][c] == num) return true;
            }
        }
        return false;
    }

    public void addNumber(int num, int row, int coll){
        if(hiddenBoard[row][coll] != 0){
            System.out.println("Não é permitido alterar um número colocado pelo jogo");
            return;
        }
        gameBoard[row][coll] = num;
        if(hasDoubled(num, row, coll)){
            System.out.println("*" + num + ", linha:" + row + ", coluna:" + coll);
        };
    }

    public void remNumber(int row, int coll){
        if(hiddenBoard[row][coll] != 0){
            System.out.println("Não é permitido alterar um número colocado pelo jogo");
            return;
        }
        gameBoard[row][coll] = 0;
    }

    private int[][] copyBoard(int[][] source){
        int[][] newBoard = new int[9][9];

        for (int i = 0; i < 9; i++) {
            System.arraycopy(source[i], 0, newBoard[i], 0, 9);
        }

        return newBoard;
    }
}
