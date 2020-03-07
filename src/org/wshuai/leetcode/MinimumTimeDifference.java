package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 09/15/2019.
 * #0539 https://leetcode.com/problems/minimum-time-difference/
 */
public class MinimumTimeDifference {
	// time O(n*log(n))
	public int findMinDifference(List<String> timePoints) {
		int n = timePoints.size(), min = Integer.MAX_VALUE;
		int[] times = new int[n];
		for(int i = 0; i < timePoints.size(); i++){
			String[] strs = timePoints.get(i).split(":");
			times[i] = Integer.parseInt(strs[0]) * 60 + Integer.parseInt(strs[1]);
		}
		Arrays.sort(times);
		for(int i = 1; i < n; i++){
			min = Math.min(times[i] - times[i - 1], min);
			// handles 24 hours wrap up
			if(i == n - 1){
				min = Math.min(times[0] + 24 * 60 - times[i], min);
			}
		}
		return min;
	}
}
