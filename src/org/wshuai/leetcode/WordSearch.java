package org.wshuai.leetcode;

/**
 * Created by Wei on 2/14/17.
 * #79 https://leetcode.com/problems/word-search/
 */
public class WordSearch {
  //DFS
  public boolean exist(char[][] board, String word) {
    if(word == null || word.isEmpty()){
      return false;
    }
    if(board == null || board.length == 0 || board[0].length == 0){
      return false;
    }
    int rows = board.length;
    int cols = board[0].length;
    boolean[][] used = new boolean[rows][cols];
    for(int i = 0; i < rows; i++){
      for(int j = 0; j < cols; j++){
        if(existUtil(i, j, 0, board, used, word, rows, cols)){
          return true;
        }
      }
    }
    return false;
  }

  private boolean existUtil(int i, int j, int index, char[][] board, boolean[][] used, String word, int rows, int cols){
    if(index == word.length()){
      return true;
    }
    if(i < 0 || j < 0 || i >= rows || j >= cols || used[i][j]){
      return false;
    }
    char curr = word.charAt(index);
    if(curr == board[i][j]){
      used[i][j] = true;
      if(existUtil(i-1, j, index+1, board, used, word, rows, cols)){
        return true;
      }
      if(existUtil(i+1, j, index+1, board, used, word, rows, cols)){
        return true;
      }
      if(existUtil(i, j-1, index+1, board, used, word, rows, cols)){
        return true;
      }
      if(existUtil(i, j+1, index+1, board, used, word, rows, cols)){
        return true;
      }
      used[i][j] = false;
    }
    return false;
  }
}
