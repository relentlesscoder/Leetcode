package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 01/21/2020.
 * #0179 https://leetcode.com/problems/largest-number/
 */
public class LargestNumber {
	// time O(n*log(n))
	public String largestNumber(int[] nums) {
		if(nums == null || nums.length == 0){
			return "";
		}
		int n = nums.length;
		String[] strs = new String[n];
		for(int i = 0; i < n; i++){
			strs[i] = Integer.toString(nums[i]);
		}
		Arrays.sort(strs, (a, b) -> (b + a).compareTo(a + b));
		StringBuilder sb = new StringBuilder();
		for(String i : strs){
			sb.append(i);
		}
		return sb.charAt(0) == '0' ? "0" : sb.toString();
	}
}
