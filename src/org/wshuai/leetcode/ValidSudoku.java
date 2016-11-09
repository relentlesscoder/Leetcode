package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 11/8/16.
 * #36 https://leetcode.com/problems/valid-sudoku/
 */
public class ValidSudoku {
  public boolean isValidSudoku(char[][] board) {
    if(board == null){
      return false;
    }

    int rLen = board.length;
    if(rLen != 9){
      return false;
    }
    int cLen = board[0].length;
    if(cLen != 9){
      return false;
    }

    Set<Character> set = new HashSet<Character>();
    for(int i = 0; i < rLen; i++){
      set.clear();
      for(int j = 0; j < cLen; j++){
        char x = board[i][j];
        if(x == '.'){
          continue;
        }
        if(x >= '1' && x <= '9' && !set.contains(x)){
          set.add(x);
        }else{
          return false;
        }
      }
    }

    for(int i = 0; i < cLen; i++){
      set.clear();
      for(int j = 0; j < rLen; j++){
        char x = board[j][i];
        if(x == '.'){
          continue;
        }
        if(x >= '1' && x <= '9' && !set.contains(x)){
          set.add(x);
        }else{
          return false;
        }
      }
    }

    for(int b = 0; b < 9; b++){
      set.clear();
      for(int i = b/3*3; i < b/3*3+3; i++){
        for(int j = b%3*3; j < b%3*3+3; j++){
          char x = board[i][j];
          if(x == '.'){
            continue;
          }
          if(x >= '1' && x <= '9' && !set.contains(x)){
            set.add(x);
          }else{
            return false;
          }
        }
      }
    }

    return true;
  }
}
