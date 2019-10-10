package org.wshuai.leetcode;

/**
 * Created by Wei on 11/11/16.
 * #132 https://leetcode.com/problems/palindrome-partitioning-ii/
 */
public class PalindromePartitioningII {

	//O(n^2)
	public int minCutDP(String s) {
		if (s == null || s.isEmpty()) {
			return 0;
		}
		int len = s.length();
		int[] mins = new int[len + 1];
		for (int i = 0; i <= len; i++) {
			//Initialize worst case - every single element counts as a palindrome
			mins[i] = len - i;
		}
		boolean[][] aux = new boolean[len][len];
		for (int i = len - 1; i >= 0; i--) {
			for (int j = i; j < len; j++) {
				if (s.charAt(i) == s.charAt(j) && (j - i < 2 || aux[i + 1][j - 1])) {
					aux[i][j] = true;
					mins[i] = Math.min(mins[i], mins[j + 1] + 1);
				}
			}
		}
		return mins[0] - 1;
	}

	//O(n^2), DP & DFS TLE
	public int minCutDFS(String s) {
		if (s == null || s.isEmpty()) {
			return 0;
		}
		int min = 0;
		int len = s.length();
		boolean[][] aux = new boolean[len][len];
		for (int k = 0; k < len; k++) {
			for (int i = 0; i + k < len; i++) {
				int j = i + k;
				aux[i][j] = (i == j) || (s.charAt(i) == s.charAt(j)
						&& (i + 1 == j || aux[i + 1][j - 1]));
			}
		}
		MinCutObj mc = new MinCutObj();
		minCutUtil(aux, len, 0, mc, 0);
		return mc.min;
	}

	private void minCutUtil(boolean[][] aux, int len, int st, MinCutObj mc, int sum) {
		if (st == len) {
			if (sum - 1 < mc.min) {
				mc.min = sum - 1;
			}
			return;
		} else {
			for (int i = len - 1; i >= st; i--) {
				if (aux[st][i]) {
					minCutUtil(aux, len, i + 1, mc, sum + 1);
				}
			}
		}
	}
}

class MinCutObj {
	int min = Integer.MAX_VALUE;
}
