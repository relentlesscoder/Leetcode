package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 03/05/2017.
 * #0506 https://leetcode.com/problems/relative-ranks/
 */
public class RelativeRanks {
	// time O(n*log(n)), space O(n)
	public String[] findRelativeRanks(int[] nums) {
		int n = nums.length;
		String[] res = new String[n];
		Integer[] index = new Integer[n];
		for(int i = 0; i < n; i++){
			index[i] = i;
		}
		Arrays.sort(index, (a, b) -> nums[b] - nums[a]);
		for(int i = 0; i < n; i++){
			if(i == 0){
				res[index[i]] = "Gold Medal";
			}else if(i == 1){
				res[index[i]] = "Silver Medal";
			}else if(i == 2){
				res[index[i]] = "Bronze Medal";
			}else{
				res[index[i]] = Integer.toString(i + 1);
			}
		}
		return res;
	}
}