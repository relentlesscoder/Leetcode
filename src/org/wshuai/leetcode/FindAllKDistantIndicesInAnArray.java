package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/02/2023.
 * #2200 https://leetcode.com/problems/find-all-k-distant-indices-in-an-array/
 */
public class FindAllKDistantIndicesInAnArray {

	// time O(n), space O(1)
	public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
		// This problem is just like walking on a street with a few streetlamps
		// and you can only see things when you are in the range covered by a
		// lamp. Let's say we are at index i then we can use a variable to record
		// the latest lamp we found (i + k) and keep updating it when we find another
		// one. As long as we are covered by a lamp (last - k <= i && last + k >= i)
		// we can add i to result.
		// e.g. key = 9, k = 2
		//   0 1 2 3 4 5 6 7 8 9 10 11 12
		//   2 3 4 9 2 9 4 3 3 3  1  1  9
		//   x _ _ _ _ _ _ _ x x  _  _  _
		List<Integer> res = new ArrayList<>();
		// initialize last to -k - 1 to ensure no actual index will be covered by
		// this virtual lamp
		int n = nums.length, last = -k - 1;
		// Hande the scenario that there are some (at least one) streetlamps at the
		// beginning of the street, we need to simulate we walk into its range at
		// index -k, so we can find it.
		// e.g. key = 9, k = 2
		//  -3 -2 -1 0 1 2 3 4 5 6 7 8 9 10
		//   n  n  n 9 3 4 0 2 5 4 9 3 3  1
		//   x  _  _ _ _ _ x x _ _ _ _ _  x
		for (int i = -k; i < 0; i++) {
			if (i + k < n && nums[i + k] == key) {
				last = i + k;
			}
		}
		for (int i = 0; i < n; i++) {
			if (i + k < n && nums[i + k] == key) {
				last = i + k;
			}
			if (last - k <= i && last + k >= i) {
				res.add(i);
			}
		}
		return res;
	}

    // time O(n), space O(1)
    public List<Integer> findKDistantIndicesTwoPointers(int[] nums, int key, int k) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        for (int i = 0, j = 0; i < n; i++) {
            if (nums[i] != key) {
                continue;
            }
            j = Math.max(j, i - k);
            while (j <= Math.min(i + k, n - 1)) {
                res.add(j++);
            }
        }
        return res;
    }
}
