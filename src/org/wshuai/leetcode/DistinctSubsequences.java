package org.wshuai.leetcode;

/**
 * Created by Wei on 2/6/17.
 * #115 https://leetcode.com/problems/distinct-subsequences/
 */
public class DistinctSubsequences {
	//DP
	public int numDistinct(String s, String t) {
		if (s == null || t == null) {
			return 0;
		}
		int sLen = s.length();
		int tLen = t.length();
		if (sLen < tLen) {
			return 0;
		}
		int[][] aux = new int[tLen + 1][sLen + 1];
		for (int i = 0; i <= sLen; i++) {
			aux[0][i] = 1;
		}
		for (int i = 1; i <= tLen; i++) {
			aux[i][0] = 0;
		}
		for (int i = 1; i <= tLen; i++) {
			for (int j = 1; j <= sLen; j++) {
				if (s.charAt(j - 1) == t.charAt(i - 1)) {
					aux[i][j] = aux[i][j - 1] + aux[i - 1][j - 1];
				} else {
					aux[i][j] = aux[i][j - 1];
				}
			}
		}
		return aux[tLen][sLen];
	}
}
