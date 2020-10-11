package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #0005 https://leetcode.com/problems/longest-palindromic-substring/
 */
public class LongestPalindromicSubstring {

	// time O(n^2), space O(1), 6ms
	public String longestPalindrome(String s) {
		int[] max = new int[]{0, -1, -1};
		char[] arr = s.toCharArray();
		for(int i = 0; i < arr.length; i++){
			expand(arr, i, i, max);
			if(i > 0){
				expand(arr, i - 1, i, max);
			}
		}
		return max[0] > 0 ? s.substring(max[1], max[2]) : "";
	}

	private void expand(char[] arr, int l, int r, int[] max){
		int len = 0;
		while(l >= 0 && r < arr.length && arr[l] == arr[r]){
			len += l-- == r++ ? 1 : 2;
		}
		if(len > max[0]){
			max[0] = len;
			max[1] = l + 1;
			max[2] = r;
		}
	}

	// time O(n^2), space O(n^2), 80ms
	// https://leetcode.com/articles/longest-palindromic-substring/
	public String longestPalindromeDP(String s) {
		int max = 0, n = s.length(), start = -1, end = -1;
		int[][] dp = new int[n][n];
		for(int l = 1; l <= n; l++){
			for(int i = 0; i + l - 1 < n; i++){
				int j = i + l - 1;
				if(i == j){
					dp[i][j] = 1;
				}else if(s.charAt(i) == s.charAt(j) && (i + 1 == j || dp[i + 1][j - 1] > 0)){
					dp[i][j] = dp[i + 1][j - 1] + 2;
				}
				if(dp[i][j] > max){
					max = dp[i][j];
					start = i;
					end = j + 1;
				}
			}
		}
		return s.substring(start, end);
	}
}
