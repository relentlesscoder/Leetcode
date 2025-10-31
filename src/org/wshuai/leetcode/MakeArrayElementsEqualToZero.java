package org.wshuai.leetcode;

/**
 * Created by Wei on 10/30/2025.
 * #3354 https://leetcode.com/problems/make-array-elements-equal-to-zero/
 */
public class MakeArrayElementsEqualToZero {

    // time O(n), space O(1)
    public int countValidSelections(int[] nums) {
        int res = 0, sum = 0;
        for (int num : nums) {
            sum += num;
        }
        for (int i = 0, curr = 0; i < nums.length; i++) {
            // 1. If sum on both sides of nums[i] are equal then starting direction
            // does not matter:
            //   [3, 2, 0, 2, 2, 1]
            // 2. If the difference of sum on both sides of nums[i] is 1 then we need
            // start towards the side with larger sum:
            //   [3, 2, 0, 2, 2]
            if (nums[i] == 0) {
                if (curr == sum - curr) {
                    res += 2;
                } else if (2 * curr - sum == 1 || sum - 2 * curr == 1) {
                    res += 1;
                }
            }
            curr += nums[i];
        }
        return res;
    }
}
