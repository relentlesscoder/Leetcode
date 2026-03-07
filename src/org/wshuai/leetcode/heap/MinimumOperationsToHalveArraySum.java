package org.wshuai.leetcode.heap;

/**
 * Created by Wei on 01/09/2026.
 * #2208 https://leetcode.com/problems/minimum-operations-to-halve-array-sum/
 */
public class MinimumOperationsToHalveArraySum {

    // time O(n * log(n)), space O(n)
    public int halveArray(int[] nums) {
        int res = 0, n = nums.length;
        double target = 0;
        double[] arr = new double[n];
        for (int i = 0; i < n; i++) { // O(n)
            target += nums[i];
            arr[i] = nums[i];
        }
        for (int i = n / 2 - 1; i >= 0; i--) { // O(n)
            sink(arr, i);
        }
        target /= 2;
        while (target > 0) {
            res++;
            arr[0] /= 2;
            target -= arr[0];
            sink(arr, 0);
        }
        return res;
    }

    private void sink(double[] nums, int i) {
        int n = nums.length;
        while (2L * i + 1 < n) {
            int j = 2 * i + 1;
            if (j + 1 < n && nums[j + 1] > nums[j]) {
                j++;
            }
            if (nums[j] <= nums[i]) {
                break;
            }
            double temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i = j;
        }
    }
}
