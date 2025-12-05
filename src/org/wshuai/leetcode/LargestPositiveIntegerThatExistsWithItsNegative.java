package org.wshuai.leetcode;

/**
 * Created by Wei on 01/01/2024.
 * #2441 https://leetcode.com/problems/largest-positive-integer-that-exists-with-its-negative/
 */
public class LargestPositiveIntegerThatExistsWithItsNegative {

    // time O(n), space O(n)
    public int findMaxK(int[] nums) {
        int res = 0;
        int[] map = new int[2_001];
        for (int num : nums) {
            if (map[-num + 1_000] > 0) {
                res = Math.max(res, num < 0 ? -num : num);
            }
            map[num + 1_000]++;
        }
        return res == 0 ? -1 : res;
    }
}
