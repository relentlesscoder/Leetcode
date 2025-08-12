package org.wshuai.leetcode;

/**
 * Created by Wei on 08/08/2025.
 * #2772 https://leetcode.com/problems/successful-pairs-of-spells-and-potions/
 */
public class ApplyOperationsToMakeAllArrayElementsEqualToZero {

    // time O(n), space O(n)
    public boolean checkArray(int[] nums, int k) {
        int n = nums.length;
        int[] arr = new int[n + 1];
        arr[0] = nums[0];
        for (int i = 1; i < n; i++) {
            arr[i] = nums[i] - nums[i - 1];
        }
        for (int i = 0; i + k <= n; i++) {
            if (arr[i] > 0) {
                arr[i + k] += arr[i];
                arr[i] = 0;
            } else if (arr[i] < 0) {
                return false;
            }
        }
        for (int i = n - k + 1; i < n; i++) {
            if (arr[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
