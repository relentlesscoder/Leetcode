package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 08/29/2016.
 * #0140 https://leetcode.com/problems/word-break-ii/
 */
public class WordBreakII {

	// time O(m*n), space O(m*n)
	public List<String> wordBreak(String s, List<String> wordDict) {
		List<String> res = new ArrayList<>();
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
		dfs(n, m, dp, wordDict, "", res);
		return res;
	}

	private void dfs(int i, int m, boolean[][] dp, List<String> wordDict, String cur, List<String> res){
		if(i == 0){
			res.add(cur);
			return;
		}
		for(int j = 1; j <= m; j++){
			if(!dp[i][j]){
				continue;
			}
			String s = wordDict.get(j - 1);
			int len = s.length();
			String next = s + (cur.isEmpty() ? "" : " ") + cur;
			dfs(i - len, m, dp, wordDict, next, res);
		}
	}
}
