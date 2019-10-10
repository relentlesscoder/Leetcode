package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/18/2016.
 * #455 https://leetcode.com/problems/assign-cookies/
 */
public class AssignCookies {
	//O(n)
	public int findContentChildren(int[] g, int[] s) {
		if (g == null || s == null) {
			return 0;
		}
		Arrays.sort(g);
		Arrays.sort(s);
		int gLen = g.length;
		int sLen = s.length;
		int cnt = 0;
		int j = 0;
		for (int i = 0; i < gLen; i++) {
			while (j < sLen && s[j] < g[i]) {
				j++;
			}
			if (j < sLen) {
				cnt++;
				j++;
			} else {
				break;
			}
		}
		return cnt;
	}
}
