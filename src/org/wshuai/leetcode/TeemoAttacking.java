package org.wshuai.leetcode;

/**
 * Created by Wei on 03/05/2017.
 * #0495 https://leetcode.com/problems/teemo-attacking/
 */
public class TeemoAttacking {
	// time O(n)
	public int findPoisonedDuration(int[] timeSeries, int duration) {
		if(timeSeries == null || timeSeries.length == 0){
			return 0;
		}
		int res = duration, lastEnd = timeSeries[0] + duration;
		for(int i = 1; i < timeSeries.length; i++){
			res += duration;
			if(timeSeries[i] < lastEnd){
				res -= lastEnd - timeSeries[i];
			}
			lastEnd = timeSeries[i] + duration;
		}
		return res;
	}
}
