package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Created by Wei on 11/27/2025.
 * #3430 https://leetcode.com/problems/maximum-and-minimum-sums-of-at-most-size-k-subarrays/
 */
public class MaximumAndMinimumSumsOfAtMostSizeKSubarrays {

    // time O(n), space O(n)
    public long minMaxSubarraySum(int[] nums, int k) {
        long res = maxSum(nums, k);
        // Negate the array and reuse the code to calculate
        // sum of maximum
        for (int i = 0; i < nums.length; i++) {
            nums[i] = -nums[i];
        }
        // Deduct sum of maximum for the negated array
        return res - maxSum(nums, k);
    }

    private long maxSum(int[] nums, int k) {
        long res = 0;
        int n = nums.length;
        int[] left = new int[n], right = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        for (int r = 0; r <= n; r++) {
            int val = r == n ? Integer.MAX_VALUE : nums[r];
            while (stack.size() > 1 && nums[stack.peek()] <= val) {
                int c = stack.pop();
                right[c] = r;
                left[c] = stack.peek();
            }
            stack.push(r);
        }
        for (int i = 0; i < n; i++) {
            int l = left[i], r = right[i], x = nums[i];
            // No constraint if r - l - 1, all subarray in exclusive range
            // (l, r) containing i is valid
            if (r - l - 1 <= k) {
                long cnt = (long) (i - l) * (r - i);
                res += x * cnt;
            } else {
                l = Math.max(l, i - k);
                r = Math.min(r, i + k);
                // Number of subarrays with left end > r - k
                long cnt = (long) (r - i) * (i - (r - k));
                // Number of subarrays with right end <= r - k
                long cnt2 = (long) (l + r + k - i * 2 + 1) * (r - l - k) / 2;
                res += x * (cnt + cnt2);
            }
        }
        return res;
    }

    // time O(n), space O(n)
    public long minMaxSubarraySumMonotonicStackTwoPass(int[] nums, int k) {
        // Same idea as #0907
        long res = 0;
        int n = nums.length;
        int[] left = new int[n], right = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        // Find left and right boundary for all subarrays having
        // nums[i] as maximum
        for (int r = 0; r <= n; r++) {
            int val = r == n ? Integer.MAX_VALUE : nums[r];
            while (stack.size() > 1 && nums[stack.peek()] <= val) {
                int c = stack.pop();
                right[c] = r;
                left[c] = stack.peek();
            }
            stack.push(r);
        }
        for (int i = 0; i < n; i++) {
            // https://leetcode.cn/problems/maximum-and-minimum-sums-of-at-most-size-k-subarrays/solutions/3051552/gong-xian-fa-dan-diao-zhan-pythonjavacgo-9gz3/
            int l = left[i], r = right[i], x = nums[i];
            // No constraint if r - l - 1, all subarray in exclusive range
            // (l, r) containing i is valid
            if (r - l - 1 <= k) {
                long cnt = (long) (i - l) * (r - i);
                res += x * cnt;
            } else {
                l = Math.max(l, i - k);
                r = Math.min(r, i + k);
                // Number of subarrays with left end > r - k
                long cnt = (long) (r - i) * (i - (r - k));
                // Number of subarrays with right end <= r - k
                long cnt2 = (long) (l + r + k - i * 2 + 1) * (r - l - k) / 2;
                res += x * (cnt + cnt2);
            }
        }
        Arrays.fill(left, 0);
        Arrays.fill(right, 0);
        stack.clear();
        stack.push(-1);
        // Find left and right boundary for all subarrays having
        // nums[i] as minimum
        for (int r = 0; r <= n; r++) {
            int val = r == n ? Integer.MIN_VALUE : nums[r];
            while (stack.size() > 1 && nums[stack.peek()] >= val) {
                int c = stack.pop();
                right[c] = r;
                left[c] = stack.peek();
            }
            stack.push(r);
        }
        for (int i = 0; i < n; i++) {
            int l = left[i], r = right[i], x = nums[i];
            if (r - l - 1 <= k) {
                long cnt = (long) (i - l) * (r - i);
                res += x * cnt;
            } else {
                l = Math.max(l, i - k);
                r = Math.min(r, i + k);
                long cnt = (long) (r - i) * (i - (r - k));
                long cnt2 = (long) (l + r + k - i * 2 + 1) * (r - l - k) / 2;
                res += x * (cnt + cnt2);
            }
        }
        return res;
    }
}
