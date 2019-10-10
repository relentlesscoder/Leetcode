package org.wshuai.leetcode;

/**
 * Created by Wei on 8/24/19.
 * #997 https://leetcode.com/problems/find-the-town-judge/
 */
public class FindTheTownJudge {
	public int findJudge(int N, int[][] trust) {
		if (N == 1 && trust.length == 0) {
			return 1;
		}
		int[] t1 = new int[N + 1];
		int[] t2 = new int[N + 1];
		int res = -1;
		for (int[] t : trust) {
			t1[t[0]]++;
			t2[t[1]]++;
			if (t2[t[1]] == N - 1) {
				res = t[1];
			}
		}
		return res > 0 && t1[res] == 0 ? res : -1;
	}
}
