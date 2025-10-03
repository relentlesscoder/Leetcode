package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Wei on 10/02/2025.
 * #3266 https://leetcode.com/problems/final-array-state-after-k-multiplication-operations-ii/
 */
public class FinalArrayStateAfterKMultiplicationOperationsII {

    private static final long MOD = (int)1e9 + 7;

    // time O(O(n * log_m(max)) + O((n + k) * log(n))), space O(n)
    public int[] getFinalStateOptimized(int[] nums, int k, int multiplier) {
        if (multiplier == 1) {
            return nums;
        }
        int n = nums.length, max = 0;
        for (int i = 0; i < nums.length; i++) { // O(n)
            max = Math.max(max, nums[i]);
        }
        long[] vals = new long[n];
        int left = k;
        outer:
        for (int i = 0; i < n; i++) { // O(n * log_m(max))
            long val = nums[i];
            while (val < max) {
                val *= multiplier;
                if (--left < 0) {
                    break outer;
                }
            }
            vals[i] = val;
        }

        if (left < 0) { // if k is not big enough then just simulate k operations
            return simulate(nums, k, multiplier); // O((n + k) * log(n))
        }

        Integer[] indexes = new Integer[n];
        Arrays.setAll(indexes, i -> i);
        Arrays.sort(indexes, (i, j) -> Long.compare(vals[i], vals[j])); // O(n * log(n))

        k = left;
        // pow2 is for the one more operation for remainder of k % n
        // 17 % 5 = 2, then the operations for each index are:
        //  1 1 1 1 1
        //  1 1 1 1 1
        //  1 1 1 1 1
        //  1 1
        // note that index 0 and 1 (i < k % n) got one more operations than other indexes
        long pow1 = pow(multiplier, k / n), pow2 = pow1 * multiplier % MOD; // log(k/n)
        for (int i = 0; i < n; i++) { // O(n)
            int index = indexes[i];
            nums[index] = (int) (vals[index] % MOD * (i < k % n ? pow2 : pow1) % MOD);
        }
        return nums;
    }

    private int[] simulate(int[] nums, int k, int multiplier) {
        PriorityQueue<long[]> minQueue = new PriorityQueue<>((a, b) ->
                a[0] == b[0] ? Long.compare(a[1], b[1]) : Long.compare(a[0], b[0]));
        for (int i = 0; i < nums.length; i++) {
            minQueue.offer(new long[]{nums[i], i});
        }
        while (k-- > 0) {
            long[] curr = minQueue.poll();
            curr[0] = curr[0] % MOD * multiplier % MOD;
            minQueue.offer(curr);
            nums[(int)curr[1]] = (int)curr[0];
        }
        return nums;
    }

    // time O(O(n * log(n) * log_m(max)), space O(n)
    public int[] getFinalState(int[] nums, int k, int multiplier) {
        // https://leetcode.cn/problems/final-array-state-after-k-multiplication-operations-ii/solutions/2892178/zui-xiao-dui-mo-ni-shu-xue-gong-shi-pyth-z4zw/
        if (multiplier == 1) {
            return nums;
        }
        int n = nums.length, max = 0;
        PriorityQueue<long[]> minQueue = new PriorityQueue<>((a, b) ->
                a[0] == b[0] ? Long.compare(a[1], b[1]) : Long.compare(a[0], b[0]));
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            minQueue.offer(new long[]{nums[i], i});
        }
        for (; k > 0 && minQueue.peek()[0] < max; k--) {
            long[] curr = minQueue.poll();
            curr[0] *= multiplier;
            minQueue.offer(curr);
        }
        long pow1 = pow(multiplier, k / n), pow2 = pow1 * multiplier % MOD;
        for (int i = 0; i < n; i++) {
            long[] curr = minQueue.poll();
            nums[(int)curr[1]] = (int) (curr[0] % MOD * (i < k % n ? pow2 : pow1) % MOD);
        }
        return nums;
    }

    private long pow(long x, int n) {
        long res = 1;
        while (n != 0) {
            if (n % 2 > 0) {
                res = res * x % MOD;
            }
            x = x * x % MOD;
            n >>= 1;
        }
        return res;
    }
}
