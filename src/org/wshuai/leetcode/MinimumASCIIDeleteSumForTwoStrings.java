package org.wshuai.leetcode;

/**
 * Created by Wei on 9/30/2019.
 * #712 https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/
 */
public class MinimumASCIIDeleteSumForTwoStrings {
  public int minimumDeleteSum(String s1, String s2) {
    int r = s1.length();
    int c = s2.length();
    int[][] dp = new int[r + 1][c + 1];
    dp[0][0] = 0;
    for(int i = 1; i <= r; i++){
      dp[i][0] = dp[i - 1][0] + s1.codePointAt(i - 1);
    }
    for(int j = 1; j <= c; j++){
      dp[0][j] = dp[0][j - 1] + s2.codePointAt(j - 1);
    }
    for(int i = 1; i <= r; i++){
      for(int j = 1; j <= c; j++){
        if(s1.charAt(i - 1) == s2.charAt(j - 1)){
          dp[i][j] = dp[i - 1][j - 1];
        }else{
          dp[i][j] = Math.min(dp[i][j - 1] + s2.codePointAt(j - 1),
            dp[i - 1][j] + s1.codePointAt(i - 1));
        }
      }
    }
    return dp[r][c];
  }
}
