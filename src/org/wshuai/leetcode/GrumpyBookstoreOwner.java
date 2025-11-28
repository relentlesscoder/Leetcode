package org.wshuai.leetcode;

/**
 * Created by Wei on 10/19/2019.
 * #1052 https://leetcode.com/problems/grumpy-bookstore-owner/
 */
public class GrumpyBookstoreOwner {

    // time O(n), space O(1)
    public int maxSatisfiedSlidingWindow(int[] customers, int[] grumpy, int minutes) {
        int res = 0, n = customers.length, max = 0, idx = -1, count = 0;
        // Calculate the maximum gain (to keep customers satisfied on a grumpy minute)
        // for a fixed sliding window of length minutes
        for (int i = 0; i < n; i++) {
            count += grumpy[i] == 0 ? 0 : customers[i]; // Can only gain if the minute is grumpy
            if (i - minutes + 1 < 0) {
                continue;
            }
            if (count > max) {
                max = count;
                idx = i;
            }
            count -= grumpy[i - minutes + 1] == 0 ? 0 : customers[i - minutes + 1];
        }
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0 || (i >= idx - minutes + 1 && i <= idx)) {
                res += customers[i];
            }
        }
        return res;
    }
}
