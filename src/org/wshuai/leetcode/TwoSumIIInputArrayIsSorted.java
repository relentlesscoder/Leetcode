package org.wshuai.leetcode;

/**
 * Created by Wei on 10/31/2016.
 * #0167 https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 */
public class TwoSumIIInputArrayIsSorted {

    // time O(n), space O(1)
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        for (int i = 0, j = n - 1, sum = 0; i < j; ) {
            sum = numbers[i] + numbers[j];
            if (sum == target) {
                return new int[]{i + 1, j + 1};
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return new int[0];
    }
}
