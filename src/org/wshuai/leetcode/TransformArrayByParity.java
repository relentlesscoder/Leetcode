package org.wshuai.leetcode;

/**
 * Created by Wei on 07/06/2025.
 * #3467 https://leetcode.com/problems/transform-array-by-parity/
 */
public class TransformArrayByParity {

    // time O(n), space O(1)
    public int[] transformArray(int[] nums) {
        int odd = 0, even = 0, index = 0;
        for (int num : nums) {
            if (num % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }
        for (; index < even; index++) {
            nums[index] = 0;
        }
        odd += index;
        for (; index < odd; index++) {
            nums[index] = 1;
        }
        return nums;
    }
}
