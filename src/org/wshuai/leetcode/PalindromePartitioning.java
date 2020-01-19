package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/11/2016.
 * #0131 https://leetcode.com/problems/palindrome-partitioning/
 */
public class PalindromePartitioning {
	// time O(n^2), space O(n^2)
	public List<List<String>> partition(String s) {
		List<List<String>> res = new ArrayList<>();
		if(s == null || s.length() == 0){
			return res;
		}
		int n = s.length();
		boolean[][] dp = new boolean[n][n];
		for(int l = 1; l <= n; l++){
			for(int i = 0; i + l - 1 < n; i++){
				int j = i + l - 1;
				dp[i][j] = (s.charAt(i) == s.charAt(j))
						&& (l <= 2 || dp[i + 1][j - 1]);
			}
		}
		dfs(0, n, s, dp, res, new ArrayList<String>());
		return res;
	}

	private void dfs(int i, int n, String s, boolean[][] dp, List<List<String>> res, List<String> cur){
		if(i == n){
			res.add(new ArrayList<>(cur));
			return;
		}
		for(int j = 0; j < n; j++){
			if(!dp[i][j]){
				continue;
			}
			cur.add(s.substring(i, j + 1));
			dfs(j + 1, n, s, dp, res, cur);
			cur.remove(cur.size() - 1);
		}
	}
}
