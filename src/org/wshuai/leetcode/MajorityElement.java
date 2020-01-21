package org.wshuai.leetcode;

/**
 * Created by Wei on 08/18/2016.
 * #0169 https://leetcode.com/problems/majority-element/
 */
public class MajorityElement {
	// time O(n)
	// http://www.cs.utexas.edu/~moore/best-ideas/mjrty/
	public int majorityElement(int[] nums) {
		int cur = nums[0], count = 1, n = nums.length;
		for(int i = 1; i < n; i++){
			if(count == 0){
				cur = nums[i];
				count = 1;
			}else{
				count += nums[i] == cur ? 1 : -1;
			}
		}
		return cur;
	}
}
