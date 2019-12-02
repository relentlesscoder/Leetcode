package org.wshuai.leetcode;

/**
 * Created by Wei on 12/2/19.
 * #727 https://leetcode.com/problems/minimum-window-subsequence/
 */
public class MinimumWindowSubsequence {
	public String minWindow(String S, String T) {
		char[] s = S.toCharArray(), t = T.toCharArray();
		String res = "";
		int i = 0, tindex = 0, slen = s.length, tlen = t.length, len = Integer.MAX_VALUE;
		while(i < slen) {
			if(s[i] == t[tindex]) {
				tindex++;
				if(tindex == tlen) { // all chars in T has been matched
					int end = i+1; //i is the last match index in S
					tindex--; // now tindex is the last index in T
					while(tindex >= 0) {  //both i and tindex move back
						if(s[i] == t[tindex]){
							tindex--;
						}
						i--;
					}
					i++;  //we found the first match index in S
					tindex++;  //now tindex == 0, the first match index in T
					if(end - i < len) { //optimization ops
						len = end - i;
						res = S.substring(i, end); // [i, end) is the matching subsequence
					}
				}
			}
			i++;
		}
		return len == Integer.MAX_VALUE ? "" : res;
	}

	// https://leetcode.com/problems/minimum-window-subsequence/discuss/109362/Java-Super-Easy-DP-Solution-(O(mn))
	public String minWindowDP(String S, String T) {
		int m = T.length(), n = S.length();
		int[][] dp = new int[m + 1][n + 1];
		for (int j = 0; j <= n; j++) {
			dp[0][j] = j + 1;
		}
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (T.charAt(i - 1) == S.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = dp[i][j - 1];
				}
			}
		}

		int start = 0, len = n + 1;
		for (int j = 1; j <= n; j++) {
			if (dp[m][j] != 0) {
				if (j - dp[m][j] + 1 < len) {
					start = dp[m][j] - 1;
					len = j - dp[m][j] + 1;
				}
			}
		}
		return len == n + 1 ? "" : S.substring(start, start + len);
	}
}
