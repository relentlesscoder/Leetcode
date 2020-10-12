package org.wshuai.leetcode;

/**
 * Created by Wei on 10/08/2019.
 * #0556 https://leetcode.com/problems/next-greater-element-iii/
 */
public class NextGreaterElementIII {

	// time O(d)
	// next permutation #0031
	public int nextGreaterElement(int n) {
		char[] nums = Integer.toString(n).toCharArray();
		int len = nums.length, left = len - 2, right = len - 1;
		while(left >= 0 && nums[left] >= nums[left + 1]){
			left--;
		}
		if(left == -1){
			return -1;
		}
		char val = nums[left];
		while(right > left && nums[right] <= val){
			right--;
		}
		nums[left] = nums[right];
		nums[right] = val;
		int start = left + 1, end = len - 1;
		while(start < end){
			char temp = nums[start];
			nums[start++] = nums[end];
			nums[end--] = temp;
		}
		long next = Long.parseLong(String.valueOf(nums));
		return next > Integer.MAX_VALUE ? -1 : (int)next;
	}
}
