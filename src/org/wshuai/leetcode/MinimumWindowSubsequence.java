package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/02/2019.
 * #0727 https://leetcode.com/problems/minimum-window-subsequence/
 */
public class MinimumWindowSubsequence {
	// time O(m*n) worst case
	public String minWindow(String S, String T) {
		char[] s = S.toCharArray(), t = T.toCharArray();
		String res = "";
		int min = Integer.MAX_VALUE, m = s.length, n = t.length;
		for(int i = 0, j = 0; i < s.length; i++){
			if(s[i] == t[j]){
				j++;
				if(j == n){ // all chars in T has been matched
					int end = i + 1; //i is the last match index in S
					j--; // now j is the last index in T
					while(j >= 0){ // both i and j moves back
						if(s[i] == t[j]){
							j--;
						}
						i--;
					}
					i++; //we found the first match index in S
					j++; //now j == 0, the first index in T
					if(end - i < min){
						min = end - i;
						res = S.substring(i, end); // [i, end) is the matching subsequence
					}
				}
			}
		}
		return res;
	}

	// time O(m*n), space O(m*n)
	// https://leetcode.com/problems/minimum-window-subsequence/discuss/140193/Logical-Thinking-with-Clean-Java-Code
	public String minWindowDP(String S, String T) {
		String res = "";
		int m = S.length(), n = T.length(), min = Integer.MAX_VALUE;
		int[][] dp = new int[m][n];
		for(int[] temp : dp){
			Arrays.fill(temp, -1);
		}
		for(int i = 0; i < m; i++){
			if(S.charAt(i) == T.charAt(0)){
				dp[i][0] = i;
			}
		}
		for(int j = 1; j < n; j++){
			int temp = -1;
			for(int i = 0; i < m; i++){
				if(S.charAt(i) == T.charAt(j)){
					dp[i][j] = temp;
				}
				temp = Math.max(temp, dp[i][j - 1]);
			}
		}
		for(int i = 0; i < m; i++){
			if(dp[i][n - 1] != -1 && i - dp[i][n - 1] + 1 < min){
				min = i - dp[i][n - 1] + 1;
				res = S.substring(dp[i][n - 1], i + 1);
			}
		}
		return res;
	}
}
