import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SudokuGenerator {
    private int[][] board = new int[9][9];
    private int[][] hiddenBoard = new int[9][9];
    Random random = new Random();

    public int[][] generate(){
        fillBoard();
        return board;
    }

    private boolean fillBoard(){
        for (int row = 0; row < 9; row++) {
            for (int coll = 0; coll < 9; coll++) {
                if(board[row][coll] == 0){
                    List<Integer> numbers = getShuffledNumbers();

                    for (int num : numbers) {
                        if (isValid(row, coll, num)) {
                            board[row][coll] = num;
                            if (fillBoard()) return true;
                            board[row][coll] = 0;
                        }
                    }
                }
            }
        }

        return true;
    }

    private List<Integer> getShuffledNumbers() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= 9; i++) list.add(i);
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
        hidden();
        System.out.println("------------------------------");
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (col== 0) System.out.print(" | ");
                System.out.print(hiddenBoard[row][col]+ " ");
                if ((col + 1)%3 == 0) System.out.print(" | ");
            }
            System.out.println();
            if((row + 1)%3 == 0) System.out.println("------------------------------");
        }
    }
    
    public void hidden(){
        int max = 78;
        int min = 70;
        int hidden = 0;
        int randomNumber = random.nextInt(max - min + 1) + min;
        hiddenBoard = board;

        while (hidden < randomNumber){
            int row = random.nextInt(9);
            int col = random.nextInt(9);

            hiddenBoard[row][col] = 0;
            hidden++;
        }
    }
}
