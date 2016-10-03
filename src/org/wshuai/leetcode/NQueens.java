package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/1/2016.
 */
public class NQueens {
  public List<List<String>> solveNQueens(int n) {
    List<List<String>> lst = new ArrayList<List<String>>();
    if(n == 1){
      List<String> s = new ArrayList<String>();
      s.add("Q");
      lst.add(s);
      return lst;
    }
    if(n <= 3){
      return lst;
    }
    for(int i = 0; i < n; i++){
      char[][] board = new char[n][n];
      solveNQueensUtil(0, board, 0, i, lst, n);
    }

    return lst;
  }

  private void printBoard(char[][] board, List<List<String>> lst, int n){
    List<String> ls = new ArrayList<String>();
    for(int i = 0; i < n; i++){
      StringBuilder str = new StringBuilder();
      for(int j = 0; j < n; j++){
        if(board[i][j] == 'Q'){
          str.append("Q");
        }else{
          str.append(".");
        }
      }
      ls.add(str.toString());
    }
    lst.add(ls);
  }

  private void solveNQueensUtil(int count, char[][] board, int row, int column, List<List<String>> lst, int n){
    if(count == n){
      return;
    }

    if(isValidMove(board, row, column, n)){
      board[row][column] = 'Q';
      if(count == n - 1){
        printBoard(board, lst, n);
      }
      for(int i = 0; i < n; i++){
        solveNQueensUtil(count + 1, board, row + 1, i, lst, n);
      }
      board[row][column] = 0;
    }else{
      return;
    }
  }

  private boolean isValidMove(char[][] board, int row, int column, int n){
    for(int i = 0; i < n; i++){
      if(i == column){
        continue;
      }
      if(board[row][i] == 'Q'){
        return false;
      }
    }
    for(int i = 0; i < n; i++){
      if(i == row){
        continue;
      }
      if(board[i][column] == 'Q'){
        return false;
      }
    }
    for(int i = row+1, j = column+1; i < n && j < n; i++, j++){
      if(board[i][j] == 'Q'){
        return false;
      }
    }
    for(int i = row-1, j = column-1; i >= 0 && j >= 0; i--, j--){
      if(board[i][j] == 'Q'){
        return false;
      }
    }
    for(int i = row-1, j = column+1; i >= 0 && j < n; i--, j++){
      if(board[i][j] == 'Q'){
        return false;
      }
    }
    for(int i = row+1, j = column-1; i < n && j >= 0; i++, j--){
      if(board[i][j] == 'Q'){
        return false;
      }
    }
    return true;
  }
}
