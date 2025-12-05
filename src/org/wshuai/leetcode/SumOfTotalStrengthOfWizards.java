package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 09/06/2023.
 * #2281 https://leetcode.com/problems/sum-of-total-strength-of-wizards/
 */
public class SumOfTotalStrengthOfWizards {

    private static final int MOD = (int)1e9 + 7;

    // time O(n), space O(n)
    public int totalStrength(int[] strength) {
        // Same idea as #0907
        long res = 0;
        int n = strength.length;
        // For each index i, find left and right boundary for subarrays
        // that can have strength[i] as the minimum
        int[] right = new int[n], left = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        for (int r = 0; r <= n; r++) {
            int val = r == n ? 0 : strength[r];
            while (stack.size() > 1 && strength[stack.peek()] >= val) {
                int c = stack.pop();
                int l = stack.peek();
                right[c] = r;
                left[c] = l;
            }
            stack.push(r);
        }
        // Calculate the prefix sum of prefix sum
        long sum = 0; // Prefix sum
        int[] prefixSum = new int[n + 2]; // Prefix sum of prefix sum
        for (int i = 1; i <= n; i++) {
            sum += strength[i - 1];
            prefixSum[i + 1] = (int) ((prefixSum[i] + sum) % MOD);
        }
        for (int i = 0; i < n; i++) {
            // Now valid subarrays in inclusive range [l, r]
            // and must contain index i
            int l = left[i] + 1;
            int r = right[i] - 1;
            // https://leetcode.cn/problems/sum-of-total-strength-of-wizards/solutions/1510399/dan-diao-zhan-qian-zhui-he-de-qian-zhui-d9nki/
            long tot = ((long) (i - l + 1) * (prefixSum[r + 2] - prefixSum[i + 1])
                    - (long) (r - i + 1) * (prefixSum[i + 1] - prefixSum[l])) % MOD;
            res = (res + strength[i] * tot % MOD) % MOD;
        }
        return (int) ((res + MOD) % MOD); // res could be negative
    }
}
