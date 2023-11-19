package org.wshuai.leetcode;

/**
 * Created by Wei on 11/08/2023.
 * #2237 https://leetcode.com/problems/count-positions-on-street-with-required-brightness/
 */
public class CountPositionsOnStreetWithRequiredBrightness {

	// time O(n), space O(n)
	public int meetRequirement(int n, int[][] lights, int[] requirement) {
		int res = 0, curr = 0;
		int[] brightness = new int[n + 1];
		for (int[] l : lights) {
			brightness[Math.max(0, l[0] - l[1])]++;
			brightness[Math.min(n, l[0] + l[1] + 1)]--;
		}
		for (int i = 0; i < n; i++) {
			curr += brightness[i];
			if (curr >= requirement[i]) {
				res++;
			}
		}
		return res;
	}
}
