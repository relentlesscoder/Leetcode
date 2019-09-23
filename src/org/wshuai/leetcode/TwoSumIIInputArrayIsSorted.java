package org.wshuai.leetcode;

/**
 * Created by Wei on 10/31/16.
 * #167 https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 */
public class TwoSumIIInputArrayIsSorted {

	//O(n)
	public int[] twoSum(int[] numbers, int target) {
		int[] res = new int[2];
		int len = numbers.length;
		int left = 0;
		int right = len - 1;
		while (left < right) {
			int sum = numbers[left] + numbers[right];
			if (sum == target) {
				res[0] = left + 1;
				res[1] = right + 1;
				break;
			} else if (sum < target) {
				left++;
			} else {
				right--;
			}
		}
		return res;
	}
}
