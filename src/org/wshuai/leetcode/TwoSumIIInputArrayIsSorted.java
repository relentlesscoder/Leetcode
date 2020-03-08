package org.wshuai.leetcode;

/**
 * Created by Wei on 10/31/2016.
 * #0167 https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 */
public class TwoSumIIInputArrayIsSorted {
	// time O(n)
	public int[] twoSum(int[] numbers, int target) {
		int left = 0, right = numbers.length - 1;
		while(left < right){
			int sum = numbers[left] + numbers[right];
			if(sum == target){
				return new int[]{left + 1, right + 1};
			}
			if(sum < target){
				left++;
			}else{
				right--;
			}
		}
		return new int[0];
	}
}
