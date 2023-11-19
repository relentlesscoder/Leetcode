package org.wshuai.leetcode;

/**
 * Created by Wei on 10/08/2023.
 * #2060 https://leetcode.com/problems/check-if-an-original-string-exists-given-two-encoded-strings/
 */
public class CheckIfAnOriginalStringExistsGivenTwoEncodedStrings {

	// time O(m * n), space O(m * n)
	public boolean possiblyEquals(String s1, String s2) {
		int m = s1.length(), n = s2.length();
		/** dp[i][j][d] denotes if the encoding is feasible starts with i and j for string s1 and s2 with difference d.
		 * d > 0 means we need more characters from s1 to match s2 whereas d < 0 means we need more characters
		 * from s2 to match s1. Since the consecutive number of digits will not be more than 3, d is in range -999 to 999.
		 * We need to map the negative thus we add 1000 to the diff to build memorization array. The range of d become
		 * [1, 1999] so 1000 means no difference.
		 */
		Boolean[][][] dp = new Boolean[m + 1][n + 1][2_000];
		return dfs(0, 0, 0, s1.toCharArray(), s2.toCharArray(), dp);
	}

	private boolean dfs(int i, int j, int diff, char[] s1, char[] s2, Boolean[][][] dp) {
		if (i == s1.length && j == s2.length) { // base case
			return diff == 0;
		}
		if (dp[i][j][diff + 1_000] != null) {
			return dp[i][j][diff + 1_000];
		}
		if (i < s1.length && j < s2.length && diff == 0 && s1[i] == s2[j]) { // exact match one character when diff is 0 and s1[i] equals to s2[j]
			if (dfs(i + 1, j + 1, 0, s1, s2, dp)) {
				return dp[i][j][1_000] = true;
			}
		}
		if (i < s1.length && !Character.isDigit(s1[i]) && diff > 0 && dfs(i + 1, j, diff - 1, s1, s2, dp)) { // pick one character at i from s1 (if it is not digit) to match the positive difference
			return dp[i][j][diff + 1_000] = true;
		}
		if (j < s2.length && !Character.isDigit(s2[j]) && diff < 0 && dfs(i, j + 1, diff + 1, s1, s2, dp)) { // pick one character at j from s2 (if it is not digit) to match the negative difference
			return dp[i][j][diff + 1_000] = true;
		}
		for (int k = i, val = 0; k < s1.length && Character.isDigit(s1[k]); k++) { // for each possible numeric value v in s1, match it to s2 with difference reduced by v
			val = val * 10 + (s1[k] - '0');
			if (dfs(k + 1, j, diff - val, s1, s2, dp)) {
				return dp[i][j][diff + 1_000] = true;
			}
		}
		for (int k = j, val = 0; k < s2.length && Character.isDigit(s2[k]); k++) { // for each possible numeric value v in s2, match it to s1 with difference increased by v
			val = val * 10 + (s2[k] - '0');
			if (dfs(i, k + 1, diff + val, s1, s2, dp)) {
				return dp[i][j][diff + 1_000] = true;
			}
		}
		return dp[i][j][diff + 1_000] = false;
	}
}
