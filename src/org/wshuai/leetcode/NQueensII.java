package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/9/16.
 * #52 https://leetcode.com/problems/n-queens-ii/
 */
public class NQueensII {
  //DFS
  public int totalNQueens(int n)
  {
    if(n == 1){
      return 1;
    }
    if (n <= 3){
      return 0;
    }
    char[][] board = new char[n][n];
    for (int i = 0; i < n; i++)
    {
      for (int j = 0; j < n; j++)
      {
        board[i][j] = '.';
      }
    }
    RefCount rt = new RefCount();
    List<Integer> pos = new ArrayList<Integer>();
    solveNQueensUtil(board, n, rt, 0, pos);
    return rt.val;
  }

  private void solveNQueensUtil(char[][] board, int n, RefCount rt, int cnt, List<Integer> pos)
  {
    if (cnt == n){
      rt.val++;
    }else{
      for (int i = 0; i < n; i++)
      {
        if (isValidMove(pos, cnt, i, n))
        {
          board[cnt][i] = 'Q';
          pos.add(cnt*n+i);
          solveNQueensUtil(board, n, rt, cnt+1, pos);
          board[cnt][i] = '.';
          pos.remove(pos.size()-1);
        }
      }
    }
  }

  private boolean isValidMove(List<Integer> pos, int r, int c, int n)
  {
    int len = pos.size();
    for(int i = 0; i < len; i++){
      int val = pos.get(i);
      int r1 = val/n;
      int c1 = val%n;
      if(r1 == r || c1 == c || Math.abs(r1-r) == Math.abs(c1-c)){
        return false;
      }
    }
    return true;
  }
}

class RefCount{
  int val = 0;
}
