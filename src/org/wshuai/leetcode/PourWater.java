package org.wshuai.leetcode;

/**
 * Created by Wei on 09/20/2019.
 * #0755 https://leetcode.com/problems/pour-water/
 */
public class PourWater {
	// time O(n^2)
	public int[] pourWater(int[] heights, int V, int K) {
		for (int i = 0; i < V; i++) {
			int cur = K;
			// go left
			while (cur > 0 && heights[cur] >= heights[cur - 1]) {
				cur--;
			}
			// go right
			while (cur < heights.length - 1 && heights[cur] >= heights[cur + 1]) {
				cur++;
			}
			// stay
			while (cur > K && heights[cur] >= heights[cur - 1]) {
				cur--;
			}
			heights[cur]++;
		}
		return heights;
	}
}
