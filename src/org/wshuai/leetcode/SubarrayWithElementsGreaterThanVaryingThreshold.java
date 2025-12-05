package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 11/27/2025.
 * #2334 https://leetcode.com/problems/subarray-with-elements-greater-than-varying-threshold/
 */
public class SubarrayWithElementsGreaterThanVaryingThreshold {

    // time O(n), space O(n)
    public int validSubarraySize(int[] nums, int threshold) {
        int n = nums.length;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        //   min > threshold / k, k <= n
        // For each index i, find its left and right boundary (exclusive) of
        // all valid subarrays that having it as the minimum. Let's say such
        // boundary is (l, r). To get a better chance to satisfy above
        // requirement, we would like k to be bigger. So we just check if
        //   nums[i] > threshold / (r - l - 1)
        for (int r = 0; r <= n; r++) {
            int val = r == n ? Integer.MIN_VALUE : nums[r];
            while (stack.size() > 1 && nums[stack.peek()] >= val) {
                int c = stack.pop();
                int l = stack.peek();
                int k = r - l - 1;
                if (nums[c] > threshold / k) {
                    return r - l - 1;
                }
            }
            stack.push(r);
        }
        return -1;
    }
}
