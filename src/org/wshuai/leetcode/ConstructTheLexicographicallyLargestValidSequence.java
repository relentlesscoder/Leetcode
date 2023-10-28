package org.wshuai.leetcode;

/**
 * Created by Wei on 10/28/2023.
 * #1718 https://leetcode.com/problems/construct-the-lexicographically-largest-valid-sequence/
 */
public class ConstructTheLexicographicallyLargestValidSequence {

	// time O(n!), space O(n)
	public int[] constructDistancedSequence(int n) {
		int m = (n - 1) * 2 + 1;
		int[] res = new int[m];
		boolean[] used = new boolean[n + 1];
		dfs(0, n, m, used, res);
		return res;
	}

	private boolean dfs(int index, int n, int m, boolean[] used, int[] curr) {
		if (index == m) {
			return true;
		}
		if (curr[index] != 0) {
			return dfs(index + 1, n, m, used, curr);
		}
		for (int i = n; i >= 1; i--) {
			if (used[i]) {
				continue;
			}
			used[i] = true;
			curr[index] = i;
			if (i == 1) {
				if (dfs(index + 1, n, m, used, curr)) {
					return true;
				}
			} else if (index + i < m && curr[index + i] == 0) {
				curr[index + i] = i;
				if (dfs(index + 1, n, m, used, curr)) {
					return true;
				}
				curr[index + i] = 0;
			}
			used[i] = false;
			curr[index] = 0;
		}
		return false;
	}
}
