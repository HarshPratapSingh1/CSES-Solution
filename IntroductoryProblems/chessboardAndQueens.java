package cses.IntroductoryProblems;

import java.util.Scanner;

public class chessboardAndQueens {
    static int cnt ;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        char[][] board = new char[8][8];
        
        for(int i = 0; i < 8; i++){
            String s = scan.nextLine();
            board[i] = s.toCharArray();
        }
        cnt = 0;
        solve(0, board);
        System.out.println(cnt);
    }
    public static void solve(int idx, char board[][]){
        if(idx == 8) {
            cnt++;
            return;
        }
        for(int j = 0 ; j < 8 ; j++){
            if(board[idx][j] == '.'){
                if(isSafe(idx,j,board)){
                    board[idx][j] = '#';
                    solve(idx+1,board);
                    board[idx][j] = '.';
                }
            }
        }
    }
    public static boolean isSafe(int row,int col,char board[][]){
        for(int i = 0 ; i < 8 ; i++){
            if(board[row][i] == '#') return false;
            if(board[i][col] == '#') return false;
        }
        int idx1 = row, idx2 = col;
        while(idx1 >= 0 && idx2 >= 0) {
            if(board[idx1][idx2] == '#') return false;
            idx1--;idx2--;
        }
        idx1 = row; idx2 = col;
        while(idx1 >= 0 && idx2 < 8) {
            if(board[idx1][idx2] == '#') return false;
            idx1--;idx2++;
        }

        return true;
    }
}
