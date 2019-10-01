package org.wshuai.leetcode;

/**
 * Created by Wei on 9/30/2019.
 * #583 https://leetcode.com/problems/delete-operation-for-two-strings/
 */
public class DeleteOperationForTwoStrings {
  // same question as #712
  public int minDistance(String word1, String word2) {
    int r = word1.length();
    int c = word2.length();
    int[][] dp = new int[r + 1][c + 1];
    dp[0][0] = 0;
    for(int i = 1; i <= r; i++){
      dp[i][0] = dp[i - 1][0] + 1;
    }
    for(int j = 1; j <= c; j++){
      dp[0][j] = dp[0][j - 1] + 1;
    }
    for(int i = 1; i <= r; i++){
      for(int j = 1; j <= c; j++){
        if(word1.charAt(i - 1) == word2.charAt(j - 1)){
          dp[i][j] = dp[i - 1][j - 1];
        }else{
          dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
        }
      }
    }
    return dp[r][c];
  }
}
