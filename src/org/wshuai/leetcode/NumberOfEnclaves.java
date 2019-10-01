package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 10/1/19.
 * #1020 https://leetcode.com/problems/number-of-enclaves/
 */
public class NumberOfEnclaves {
  private int[][] move;

  // BFS to find all the lands that are connected to bound
  public int numEnclaves(int[][] A) {
    move = new int[2][4];
    move[0] = new int[]{1, -1, 0, 0};
    move[1] = new int[]{0, 0, 1, -1};
    int r = A.length;
    int c = A[0].length;
    LinkedList<int[]> queue = new LinkedList<>();

    int res = 0;
    for(int i = 0; i < r; i++){
      for(int j = 0; j < c; j++){
        if(A[i][j] == 0){
          continue;
        }
        res++;
        if(i * j == 0 || (i == r - 1 || j == c - 1)){
          queue.offerLast(new int[]{i, j});
        }
      }
    }

    while(!queue.isEmpty()){
      int[] curr = queue.pollLast();
      if(A[curr[0]][curr[1]] == 0){
        continue;
      }
      A[curr[0]][curr[1]] = 0;
      res--;
      for(int k = 0; k < 4; k++){
        int x = curr[0] + move[0][k];
        int y = curr[1] + move[1][k];
        if(x >= 0 && y >= 0 && x < r && y < c && A[x][y] == 1){
          queue.offerLast(new int[]{x, y});
        }
      }
    }
    return res;
  }
}
