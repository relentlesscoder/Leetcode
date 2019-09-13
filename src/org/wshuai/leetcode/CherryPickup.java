package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 9/12/2019.
 * #741 https://leetcode.com/problems/cherry-pickup/
 */
public class CherryPickup {
  // see https://leetcode.com/problems/cherry-pickup/discuss/165218/Java-O(N3)-DP-solution-w-specific-explanation
  public int cherryPickup(int[][] grid) {
    int n = grid.length;
    int[][][]dp = new int[n + 1][n + 1][n + 1];
    for(int i = 0; i <= n; i++){
      for(int j = 0; j <= n; j++){
        Arrays.fill(dp[i][j], Integer.MIN_VALUE);
      }
    }
    dp[1][1][1] = grid[0][0];
    for(int x1 = 1; x1 <= n; x1++){
      for(int y1 = 1; y1 <= n; y1++){
        for(int x2 = 1; x2 <= n; x2++){
          int y2 = x1 + y1 - x2;
          if(dp[x1][y1][x2] > 0 || y2 < 1 || y2 > n || grid[x1 - 1][y1 - 1] == -1 || grid[x2 - 1][y2 - 1] == -1){
            continue;
            // have already detected || out of boundary || cannot access
          }
          int cur = Math.max(Math.max(dp[x1 - 1][y1][x2], dp[x1 - 1][y1][x2 - 1]),
            Math.max(dp[x1][y1 - 1][x2], dp[x1][y1 - 1][x2 - 1]));
          if(cur < 0){
            continue;
          }
          dp[x1][y1][x2] = cur + grid[x1 - 1][y1 - 1];
          if(x1 != x2){
            dp[x1][y1][x2] += grid[x2 - 1][y2 - 1];
          }
        }
      }
    }
    return dp[n][n][n] < 0 ? 0 : dp[n][n][n];
  }
}
