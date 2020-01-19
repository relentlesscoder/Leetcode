package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 01/19/2020.
 * #1326 https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden/
 */
public class MinimumNumberOfTapsToOpenToWaterAGarden {
	// time O(n)
	public int minTaps(int n, int[] ranges) {
		int[][] taps = new int[n + 1][2];
		for(int i = 0; i < ranges.length; i++){
			taps[i] = new int[]{Math.max(0, i - ranges[i]),
				Math.min(n, i + ranges[i])};
		}
		Arrays.sort(taps, (a, b) -> a[0] - b[0]);
		int res = 0;
		for(int i = 0, end = 0, cur = 0; i <= n && end < n; end = cur, res++){
			// greedily extend the current intervals
			while(i <= n && taps[i][0] <= end){
				cur = Math.max(cur, taps[i][1]);
				i++;
			}
			if(cur <= end){
				return -1;
			}
		}
		return res;
	}
}
