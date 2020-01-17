package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 08/21/2016.
 * #0139 https://leetcode.com/problems/word-break/
 */
public class WordBreak {
	// time O(m*n^2)
	public boolean wordBreak(String s, List<String> wordDict) {
		int slen = s.length();
		int[] arr = new int[slen + 1];
		Arrays.fill(arr, -1);
		arr[0] = 0;
		for(int i = 0; i < slen; i++){
			if(arr[i] != -1){
				for(int j = i + 1; j <= slen; j++){
					if(wordDict.contains(s.substring(i, j))){
						arr[j] = i;
					}
				}
			}
		}
		return (arr[slen] != -1);
	}

	// time O(m*n), space O(m*n)
	public boolean wordBreakKnapsack(String s, List<String> wordDict) {
		int n = s.length();
		int m = wordDict.size();
		boolean[][] dp = new boolean[n + 1][m + 1];
		boolean[] memo = new boolean[n + 1];
		Arrays.fill(dp[0], true);
		memo[0] = true;
		for(int i = 1; i <= n; i++){
			for(int j = 0; j <= m; j++){
				if(j == 0){
					dp[i][j] = false;
					continue;
				}
				String cur = wordDict.get(j - 1);
				int len = cur.length();
				dp[i][j] = i >= len && s.substring(i - len, i).equals(cur) && memo[i - len];
				if(dp[i][j] && !memo[i]){
					memo[i] = true;
				}
			}
		}
		return memo[n];
	}
}
