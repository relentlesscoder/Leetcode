package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 02/20/2017.
 * #0475 https://leetcode.com/problems/heaters/
 */
public class Heaters {
	// time O(n*log(n))
	public int findRadius(int[] houses, int[] heaters) {
		int res = 0, n = heaters.length;
		Arrays.sort(heaters);
		for(int h : houses){
			int idx = binarySearch(heaters, h);
			int dist = idx == n ? h - heaters[n - 1] : heaters[idx] - h;
			if(idx > 0){
				dist = Math.min(dist, h - heaters[idx - 1]);
			}
			res = Math.max(res, dist);
		}
		return res;
	}

	private int binarySearch(int[] nums, int target){
		int left = 0, right = nums.length;
		while(left < right){
			int mid = left + (right - left) / 2;
			if(nums[mid] < target){
				left = mid + 1;
			}else{
				right = mid;
			}
		}
		return left;
	}
}
