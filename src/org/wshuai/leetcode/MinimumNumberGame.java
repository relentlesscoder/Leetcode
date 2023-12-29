package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/29/2023.
 * #2974 https://leetcode.com/problems/minimum-number-game/
 */
public class MinimumNumberGame {

    // time O(n * log(n)), space O(1)
    public int[] numberGame(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i += 2) {
            int temp = nums[i];
            nums[i] = nums[i - 1];
            nums[i - 1] = temp;
        }
        return nums;
    }
}
