package org.wshuai.leetcode;

/**
 * Created by Wei on 4/8/17.
 * #544 https://leetcode.com/problems/output-contest-matches/
 */
public class OutputContestMatches {
	public String findContestMatch(int n) {
		String[] aux = new String[n];
		for (int i = 1; i <= n; i++) {
			aux[i - 1] = Integer.toString(i);
		}
		findContestMatch(aux, n);
		return aux[0];
	}

	private void findContestMatch(String[] strs, int len) {
		if (len == 1) {
			return;
		}
		int h = len / 2;
		int idx = len - 1;
		for (int i = 0; i < h; i++) {
			strs[i] = "(" + strs[i] + "," + strs[idx--] + ")";
		}
		findContestMatch(strs, h);
	}
}
