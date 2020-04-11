package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 08/20/2019.
 * #0697 https://leetcode.com/problems/degree-of-an-array/
 */
public class DegreeOfAnArray {
	// time O(n), space O(n)
	public int findShortestSubArray(int[] nums) {
		Map<Integer, int[]> map = new HashMap<>();
		int n = nums.length, res = n, maxFreq = 0;
		for(int i = 0; i < n; i++){
			map.putIfAbsent(nums[i], new int[]{0, -1, -1});
			int[] cur = map.get(nums[i]);
			cur[0]++;
			if(cur[0] > maxFreq){
				maxFreq = cur[0];
			}
			cur[1] = cur[1] == -1 ? i : cur[1];
			cur[2] = i;
		}
		for(int[] cur : map.values()){
			if(cur[0] != maxFreq){
				continue;
			}
			res = Math.min(res, cur[2] - cur[1] + 1);
		}
		return res;
	}
}
