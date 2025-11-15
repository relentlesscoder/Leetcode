package org.wshuai.leetcode;

/**
 * Created by Wei on 11/15/2025.
 * #2905 https://leetcode.com/problems/find-indices-with-index-and-value-difference-ii/
 */
public class FindIndicesWithIndexAndValueDifferenceII {

    // time O(n), space O(n)
    public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
        int n = nums.length;
        int[][] minMax = new int[n][2];
        for (int i = 0; i < n; i++) {
            minMax[i][0] = i == 0 || nums[i] < nums[minMax[i - 1][0]] ? i : minMax[i - 1][0];
            minMax[i][1] = i == 0 || nums[i] > nums[minMax[i - 1][1]] ? i : minMax[i - 1][1];
            if (i < indexDifference) {
                continue;
            }
            if (nums[i] - nums[minMax[i - indexDifference][0]] >= valueDifference) {
                return new int[] {i, minMax[i - indexDifference][0]};
            }
            if (nums[minMax[i - indexDifference][1]] - nums[i] >= valueDifference) {
                return new int[] {i, minMax[i - indexDifference][1]};
            }
        }
        return new int[] {-1, -1};
    }
}
