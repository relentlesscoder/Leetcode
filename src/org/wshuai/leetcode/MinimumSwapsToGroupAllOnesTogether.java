package org.wshuai.leetcode;

/**
 * Created by Wei on 09/30/2019.
 * #1151 https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together/
 */
public class MinimumSwapsToGroupAllOnesTogether {

    // time O(n), space O(1)
    public int minSwaps(int[] data) {
        int n = data.length, res = n, k = 0, count = 0;
        // Convert the problem into finding the minimum number
        // of 0s in a k length subarray where k is the total
        // number of 1s in array. So the minimum swap is just
        // to swap the 0s in such an array with 1s outside of
        // it.
        for (int d : data) {
            k += d;
        }
        // No swaps are needed for all 0s or 1s array
        if (k == n || k == 0) {
            return 0;
        }
        for (int i = 0; i < n; i++) {
            count += data[i];
            if (i - k + 1 < 0) {
                continue;
            }
            res = Math.min(res, k - count);
            count -= data[i - k + 1];
        }
        return res;
    }
}
