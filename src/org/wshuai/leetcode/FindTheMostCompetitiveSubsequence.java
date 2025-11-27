package org.wshuai.leetcode;

/**
 * Created by Wei on 05/16/2021.
 * #1673 https://leetcode.com/problems/find-the-most-competitive-subsequence/
 */
public class FindTheMostCompetitiveSubsequence {

    // time O(n), space O(k)
    public int[] mostCompetitive(int[] nums, int k) {
        // Same idea as #0402
        int n = nums.length, count = n - k;
        int[] stack = new int[n]; // Use an array as stack
        for (int i = 0, j = -1; i < n && count > 0; i++) {
            while (count > 0 && j >= 0 && nums[stack[j]] > nums[i]) {
                nums[stack[j--]] = -1;
                count--;
            }
            stack[++j] = i;
        }
        // If count still > 0, remove rest digits from the end
        for (int i = n - 1; i >= 0 && count > 0; i--) {
            if (nums[i] != -1) {
                nums[i] = -1;
                count--;
            }
        }
        int[] res = new int[k];
        for (int i = 0, j = 0; i < n; i++) {
            if (nums[i] != -1) {
                res[j++] = nums[i];
            }
        }
        return res;
    }
}
