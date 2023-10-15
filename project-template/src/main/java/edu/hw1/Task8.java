package edu.hw1;

public class Task8 {

    public boolean knightBoardCapture(int[][] board){
        final int[] row = { 2, 1, -1, -2, -2, -1, 1, 2, 2 };
        final int[] col = { 1, 2, 2, 1, -1, -2, -2, -1, 1 };
        boolean flag = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 1){
                    for (int k = 0; k < 8; k++) {
                        if(isValid(i+row[k],j+col[k])){
                            if (board[i][j] == board[i+row[k]][j+col[k]]){
                                flag = false;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }
    private static boolean isValid(int x, int y)
    {
        if (x < 0 || y < 0 || x >= 8 || y >= 8) {
            return false;
        }

        return true;
    }
}
