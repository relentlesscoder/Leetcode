package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/18/2016.
 * #0455 https://leetcode.com/problems/assign-cookies/
 */
public class AssignCookies {
	// time O(n)
	public int findContentChildren(int[] g, int[] s) {
		int res = 0;
		Arrays.sort(g);
		Arrays.sort(s);
		for(int i = s.length - 1, j = g.length - 1; i >= 0 && j >= 0; j--){
			if(g[j] <= s[i]){
				i--;
				res++;
			}
		}
		return res;
	}
}
