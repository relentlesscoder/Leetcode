package org.wshuai.leetcode;

/**
 * Created by Wei on 9/19/19.
 * #1143 https://leetcode.com/problems/longest-common-subsequence/
 */
public class LongestCommonSubsequence {
  public int longestCommonSubsequence(String text1, String text2) {
    int r = text1.length();
    int c = text2.length();
    int[][] dp = new int[r + 1][c + 1];
    for(int i = 0; i <= r; i++){
      dp[i][0] = 0;
    }
    for(int j = 0; j <= c; j++){
      dp[0][j] = 0;
    }
    for(int i = 1; i <= r; i++){
      for(int j = 1; j <= c; j++){
        if(text1.charAt(i - 1) == text2.charAt(j - 1)){
          dp[i][j] = dp[i-1][j-1] + 1;
        }else{
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }
    return dp[r][c];
  }
}
