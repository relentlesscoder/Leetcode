package org.wshuai.leetcode;

/**
 * Created by Wei on 9/20/19.
 * #647 https://leetcode.com/problems/palindromic-substrings/
 */
public class PalindromicSubstrings {
	private int count;

	public int countSubstrings(String s) {
		count = 0;
		for(int i = 0; i < s.length(); i++){
			extend(s, i, i);
			extend(s, i, i + 1);
		}
		return count;
	}

	private void extend(String s, int left, int right){
		while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
			count++;
			left--;
			right++;
		}
	}

	public int countSubstringsDP(String s) {
		int res = 0;
		int len = s.length();
		boolean[][] dp = new boolean[len][len];

		for(int i = len - 1; i >= 0; i--){
			for(int j = i; j < len; j++){
				if(s.charAt(i) == s.charAt(j)){
					dp[i][j] = j - i < 2 ? true : dp[i+1][j-1];
					if(dp[i][j]){
						count++;
					}
				}
			}
		}

		return res;
	}
}
