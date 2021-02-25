package org.wshuai.leetcode;

/**
 * Created by Wei on 02/24/2021.
 * #1748 https://leetcode.com/problems/sum-of-unique-elements/
 */
public class SumOfUniqueElements {

	// time O(n)
	public int sumOfUnique(int[] nums) {
		int[] count = new int[101];
		int sum = 0;
		for(int num : nums){
			if(count[num] == 0){
				sum += num;
			}else if(count[num] == 1){
				sum -= num;
			}
			count[num]++;
		}
		return sum;
	}
}
