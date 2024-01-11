package org.wshuai.leetcode;

/**
 * Created by Wei on 01/11/2024.
 * #2903 https://leetcode.com/problems/find-indices-with-index-and-value-difference-i/
 */
public class FindIndicesWithIndexAndValueDifferenceI {

    // time O(n), space O(n)
    public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
        int n = nums.length;
        int[][] prefix = new int[n + 1][2]; // use prefix array to store min and max index
        for (int i = 0; i < n; i++) {
            prefix[i + 1][0] = i == 0 || nums[i] < nums[prefix[i][0]] ? i : prefix[i][0];
            prefix[i + 1][1] = i == 0 || nums[i] > nums[prefix[i][1]] ? i : prefix[i][1];
            if (i - indexDifference >= 0) {
                if (Math.abs(nums[i] - nums[prefix[i - indexDifference + 1][0]]) >= valueDifference) {
                    return new int[]{i, prefix[i - indexDifference + 1][0]};
                } else if (Math.abs(nums[i] - nums[prefix[i - indexDifference + 1][1]]) >= valueDifference) {
                    return new int[]{i, prefix[i - indexDifference + 1][1]};
                }
            }
        }
        return new int[]{-1, -1};
    }
}
