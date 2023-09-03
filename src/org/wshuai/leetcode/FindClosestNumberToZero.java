package org.wshuai.leetcode;

/**
 * Created by Wei on 09/03/2023.
 * #2239 https://leetcode.com/problems/find-closest-number-to-zero/description/
 */
public class FindClosestNumberToZero {

    // time O(n), space O(1)
    public int findClosestNumber(int[] nums) {
        int res = nums[0], minDiff = Integer.MAX_VALUE;
        for (int num : nums) {
            int diff = num < 0 ? -num : num;
            if (diff < minDiff) {
                res = num;
                minDiff = diff;
            } else if (diff == minDiff && num > res) {
                res = num;
            }
        }
        return res;
    }
}
