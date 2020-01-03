package org.wshuai.leetcode;

/**
 * Created by Wei on 11/22/19.
 * #1220 https://leetcode.com/problems/count-vowels-permutation/
 */
public class CountVowelsPermutation {
	private final int mod = 1_000_000_007;
	private int[][] dp;

	public int countVowelPermutation(int n) {
		dp = new int[6][n + 1];
		return dfs(0, n);
	}

	private int dfs(int i, int l){
		if(l == 0){
			return 1;
		}
		if(dp[i][l] > 0){
			return dp[i][l];
		}
		long res = 0;
		// numerical value i is mapping to vowels
		// 0 -> ' ' (special case)
		// 1 -> 'a'
		// 2 -> 'e'
		// 3 -> 'i'
		// 4 -> 'o'
		// 5 -> 'u'
		if(i == 0){
			res += dfs(1, l - 1);
			res += dfs(2, l - 1);
			res += dfs(3, l - 1);
			res += dfs(4, l - 1);
			res += dfs(5, l - 1);
		}else if(i == 1){
			res += dfs(2, l - 1);
		}else if(i == 2){
			res += dfs(1, l - 1);
			res += dfs(3, l - 1);
		}else if(i == 3){
			res += dfs(1, l - 1);
			res += dfs(2, l - 1);
			res += dfs(4, l - 1);
			res += dfs(5, l - 1);
		}else if(i == 4){
			res += dfs(3, l - 1);
			res += dfs(5, l - 1);
		}else{
			res += dfs(1, l - 1);
		}
		dp[i][l] = (int)(res % mod);
		return dp[i][l];
	}
}
