package org.wshuai.leetcode;

/**
 * Created by Wei on 2/25/17.
 * #474 https://leetcode.com/problems/ones-and-zeroes/
 */
public class OnesAndZeroes {
	public int findMaxForm(String[] strs, int m, int n) {
		int[][] aux = new int[m + 1][n + 1];
		for (String str : strs) {
			int zeros = 0;
			int ones = 0;
			int len = str.length();
			for (int i = 0; i < len; i++) {
				if (str.charAt(i) == '0') {
					zeros++;
				} else {
					ones++;
				}
			}

			for (int i = m; i >= zeros; i--) {
				for (int j = n; j >= ones; j--) {
					aux[i][j] = Math.max(aux[i][j], aux[i - zeros][j - ones] + 1);
				}
			}
		}
		return aux[m][n];
	}
}
