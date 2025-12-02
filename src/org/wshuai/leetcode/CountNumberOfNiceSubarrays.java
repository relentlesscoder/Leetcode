package org.wshuai.leetcode;

/**
 * Created by Wei on 11/07/2019.
 * #1248 https://leetcode.com/problems/count-number-of-nice-subarrays/
 */
public class CountNumberOfNiceSubarrays {

	// time O(n), space O(1)
	public int numberOfSubarrays3Pointers(int[] nums, int k) {
		int res = 0, n = nums.length;
		for (int i = 0, j1 = 0, j2 = 0, count1 = 0, count2 = 0; i < n; i++) {

			count1 += (nums[i] & 1);
			while (count1 >= k) {
				count1 -= (nums[j1++] & 1);
			}

			count2 += (nums[i] & 1);
			while (count2 >= k + 1) {
				count2 -= (nums[j2++] & 1);
			}
			res += j1 - j2;
		}
		return res;
	}

    // time O(n), space O(1)
    public int numberOfSubarrays(int[] nums, int k) {
        return solve(nums, k) - solve(nums, k + 1);
    }

    private int solve(int[] nums, int k) {
        int res = 0, n = nums.length;
        for (int i = 0, j = 0, count = 0; i < n; i++) {
            count += (nums[i] & 1);
            while (count >= k) {
                count -= (nums[j++] & 1);
            }
            res += j;
        }
        return res;
    }
}
