package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 08/08/2019.
 * #0961 https://leetcode.com/problems/n-repeated-element-in-size-2n-array/
 */
public class NRepeatedElementInSize2NArray {

	// time O(n), space O(1)
	public int repeatedNTimes(int[] nums) {
		int n = nums.length;
		for (int i = 2; i < n; i++) {
			// If n >= 3, this will always work like
			// [2,2,3,4,2,2]
			if (nums[i] == nums[i - 1] || nums[i] == nums[i - 2]) {
				return nums[i];
			}
		}
		// Only 2 exceptions when n = 2
		// [2,2,3,4]
		// [2,3,4,2]
		return nums[0];
	}

	// time O(n), space O(n)
    public int repeatedNTimesSet(int[] A) {
        Set<Integer> set = new HashSet<Integer>();
        for (int a : A) {
            if (!set.add(a)) {
                return a;
            }
        }
        return -1;
    }
}
