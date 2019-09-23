package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 9/15/19.
 * #539 https://leetcode.com/problems/minimum-time-difference/
 */
public class MinimumTimeDifference {
	public int findMinDifference(List<String> timePoints) {
		int[] arr = new int[timePoints.size()];
		int i = 0;
		for (String tp : timePoints) {
			String[] vals = tp.split(":");
			arr[i++] = Integer.parseInt(vals[0]) * 60 + Integer.parseInt(vals[1]);
		}
		int min = Integer.MAX_VALUE;
		Arrays.sort(arr);
		for (int j = 0; j < arr.length; j++) {
			// need to handle the 24 hour wrap case
			int diff = j == 0 ? 1440 + arr[j] - arr[arr.length - 1] : arr[j] - arr[j - 1];
			min = Math.min(min, diff);
		}

		return min;
	}
}
