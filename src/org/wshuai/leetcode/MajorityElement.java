package org.wshuai.leetcode;

/**
 * Created by Wei on 08/18/2016.
 * #0169 https://leetcode.com/problems/majority-element/
 */
public class MajorityElement {

	// time O(n), space O(1)
	public int majorityElement(int[] nums) {
		int res = -1, votes = 0;
		for (int num : nums) {
			if (votes == 0) {
				res = num;
				votes = 1;
			} else {
				votes += num == res ? 1 : -1;
			}
		}
		return res;
	}
}
