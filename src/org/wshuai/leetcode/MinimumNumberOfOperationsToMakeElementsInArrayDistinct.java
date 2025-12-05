package org.wshuai.leetcode;

/**
 * Created by Wei on 04/19/2025.
 * #3396 https://leetcode.com/problems/minimum-number-of-operations-to-make-elements-in-array-distinct/
 */
public class MinimumNumberOfOperationsToMakeElementsInArrayDistinct {

    // time O(n), space O(1)
    public int minimumOperations(int[] nums) {
        int n = nums.length;
        int[] map = new int[101];
        for (int i = n - 1; i >= 0; i--) {
            if (map[nums[i]] == 1) {
                return (i + 1) / 3 + ((i + 1) % 3 == 0 ? 0 : 1);
            }
            map[nums[i]] = 1;
        }
        return 0;
    }
}
