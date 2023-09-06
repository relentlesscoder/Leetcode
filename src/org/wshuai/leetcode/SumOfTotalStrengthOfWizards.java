package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by Wei on 09/06/2023.
 * #2281 https://leetcode.com/problems/sum-of-total-strength-of-wizards/
 */
public class SumOfTotalStrengthOfWizards {

    private static final int MOD = 1_000_000_007;

    // time O(n), space O(n)
    public int totalStrength(int[] strength) {
        long res = 0;
        int n = strength.length;
        int[] rightIndex = new int[n], leftIndex = new int[n];
        Stack<Integer> stack = new Stack<>();
        // use monotonic stack to define the left and right boundary for all sub-arrays
        // that has minimum strength
        Arrays.fill(rightIndex, n);
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && strength[stack.peek()] >= strength[i]) {
                rightIndex[stack.pop()] = i;
            }
            stack.add(i);
        }
        stack.clear();
        Arrays.fill(leftIndex, -1);
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && strength[stack.peek()] > strength[i]) {
                leftIndex[stack.pop()] = i;
            }
            stack.add(i);
        }
        // record the prefix sum of the prefix sum of the input array
        long[] prefixSum = new long[n + 2];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 2] = (prefixSum[i + 1] + strength[i]) % MOD;
        }
        for (int i = 1; i <= n; i++) {
            prefixSum[i + 1] = (prefixSum[i + 1] + prefixSum[i]) % MOD;
        }
        for (int i = 0; i < n; i++) {
            // https://leetcode.com/problems/sum-of-total-strength-of-wizards/editorial/
            int leftBound = leftIndex[i], rightBound = rightIndex[i];
            int leftCount = i - leftBound, rightCount = rightBound - i;
            long negativeSum = (prefixSum[i + 1] - prefixSum[i - leftCount + 1]) % MOD,
                    positiveSum = (prefixSum[i + rightCount + 1] - prefixSum[i + 1]) % MOD;
            res = (res + (positiveSum * leftCount - negativeSum * rightCount) % MOD * strength[i] % MOD) % MOD;
        }
        return (int)(res + MOD) % MOD;
    }
}
