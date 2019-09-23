package org.wshuai.leetcode;

/**
 * Created by Wei on 11/1/16.
 * #10 https://leetcode.com/problems/regular-expression-matching/
 */
public class RegularExpressionMatching {
	//DP, draw a matrix to help understanding the question
	public boolean isMatchDP(String s, String p) {
		if (s == null || p == null) {
			return false;
		}
		while (p.contains("**")) {
			p = p.replaceAll("\\*\\*", "*");
		}
		char[] sArr = s.toCharArray();
		char[] pArr = p.toCharArray();
		int sLen = sArr.length;
		int pLen = pArr.length;
		int[][] aux = new int[sLen + 1][pLen + 1];
		aux[0][0] = 1;
		for (int i = 0; i <= sLen; i++) {
			for (int j = 1; j <= pLen; j++) {
				if (i > 0 && (sArr[i - 1] == pArr[j - 1] || pArr[j - 1] == '.')) {
					aux[i][j] = aux[i - 1][j - 1];
				} else if (pArr[j - 1] == '*' && j > 1) {
					aux[i][j] = (aux[i][j - 1] == 1 || aux[i][j - 2] == 1
							|| (i > 0 && (sArr[i - 1] == pArr[j - 2] || pArr[j - 2] == '.') && aux[i - 1][j] == 1)) ? 1 : 0;
				}
			}
		}
		return aux[sLen][pLen] == 1;
	}
}
