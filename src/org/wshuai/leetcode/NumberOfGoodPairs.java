package org.wshuai.leetcode;

/**
 * Created by Wei on 07/12/2020.
 * #1512 https://leetcode.com/problems/number-of-good-pairs/
 */
public class NumberOfGoodPairs {

	public int numIdenticalPairs(int[] nums) {
		int res = 0;
		int[] count = new int[101];
		for(int num : nums){
			count[num]++;
		}
		for(int c : count){
			while(--c > 0){
				res += c;
			}
		}
		return res;
	}
}
