package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 08/01/2025.
 * #3254 https://leetcode.com/problems/find-the-power-of-k-size-subarrays-i/
 */
public class FindThePowerOfKSizeSubarraysI {

    // time O(n), space O(n)
    public int[] resultsArray(int[] nums, int k) {
        int n = nums.length, m = n - k + 1;
        // aux[i] indicate the length of consecutive subarray end at i
        int[] aux = new int[n], res = new int[m];
        Arrays.fill(aux, 1);
        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                aux[i] += aux[i - 1];
            }
        }
        for (int i = k - 1, j = 0; i < n; i++, j++) {
            if (aux[i] >= k) {
                res[j] = nums[i];
            } else {
                res[j] = -1;
            }
        }
        return res;
    }
}
