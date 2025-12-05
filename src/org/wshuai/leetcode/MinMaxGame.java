package org.wshuai.leetcode;

/**
 * Created by Wei on 01/10/2024.
 * #2293 https://leetcode.com/problems/min-max-game/
 */
public class MinMaxGame {

    // time O(n * log(n)), space O(1)
    public int minMaxGame(int[] nums) {
        int n = nums.length;
        while (n > 1) {
            boolean min = true;
            for (int i = 0, j = 0; i < n; i += 2) {
                nums[j++] = min ? Math.min(nums[i], nums[i + 1]) : Math.max(nums[i], nums[i + 1]);
                min = !min;
            }
            n /= 2;
        }
        return nums[0];
    }
}
