package org.wshuai.leetcode;

/**
 * Created by Wei on 09/20/2019.
 * #0647 https://leetcode.com/problems/palindromic-substrings/
 */
public class PalindromicSubstrings {
	private int count;

	// time O(n^2)
	public int countSubstrings(String s) {
		count = 0;
		for (int i = 0; i < s.length(); i++) {
			extend(s, i, i);
			extend(s, i, i + 1);
		}
		return count;
	}

	private void extend(String s, int left, int right) {
		while (left >= 0 && right < s.length()
			&& s.charAt(left) == s.charAt(right)) {
			count++;
			left--;
			right++;
		}
	}

	// time O(n^2), space O(n^2)
	public int countSubstringsDP(String s) {
		int res = 0, n = s.length();
		boolean[][] dp = new boolean[n][n];
		for(int l = 1; l <= n; l++){
			for(int i = 0; i + l - 1 < n; i++){
				int j = i + l - 1;
				if(l == 1 || (s.charAt(i) == s.charAt(j)
					&& (i + 1 == j || dp[i + 1][j - 1]))){
					dp[i][j] = true;
					res++;
				}
			}
		}
		return res;
	}
}
