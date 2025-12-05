package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 09/06/2023.
 * #2104 https://leetcode.com/problems/sum-of-subarray-ranges/
 */
public class SumOfSubarrayRanges {

    // time O(n), space O(n)
    public long subArrayRanges(int[] nums) {
        // Same idea as #0907
        long res = 0;
        int n = nums.length;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        // For each index c, find the first index r on right that satisfies
        // arr[r] <= arr[c] and find the first index l on left that satisfies
        // arr[l] > arr[c] (= for subarray deduplicate) and the subarray that
        // contains index c as largest number is in exclusive range (l,r).
        // Each index c will contribute (c - l) * (r - c) * nums[c] to the final
        // result.
        for (int r = 0; r <= n; r++) {
            int val = r == n ? Integer.MAX_VALUE : nums[r];
            while (stack.size() > 1 && nums[stack.peek()] <= val) {
                int c = stack.pop();
                int l = stack.peek();
                res += 1L * (c - l) * (r - c) * nums[c];
            }
            stack.push(r);
        }
        stack.clear();
        stack.push(-1);
        // By using the same approach, we can find the number of subarray that contains
        // index c as smallest index and each of such index c will contribute
        // - (c - l) * (r - c) * nums[c] to the final result.
        for (int r = 0; r <= n; r++) {
            int val = r == n ? Integer.MIN_VALUE : nums[r];
            while (stack.size() > 1 && nums[stack.peek()] >= val) {
                int c = stack.pop();
                int l = stack.peek();
                res -= 1L * (c - l) * (r - c) * nums[c];
            }
            stack.push(r);
        }
        return res;
    }
}
