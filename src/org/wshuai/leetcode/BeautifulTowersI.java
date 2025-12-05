package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 09/21/2025.
 * #2865 https://leetcode.com/problems/beautiful-towers-i/
 */
public class BeautifulTowersI {

    // time O(n), space O(n)
    public long maximumSumOfHeights(int[] heights) {
        long res = 0, curr = 0;
        int n = heights.length;
        long[] left = new long[n];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        // Construct a non-decreasing monotonic stack from left, left[i] stores sum from
        // left (inclusive) using i as the peak
        for (int i = 0; i < n; i++) {
            while (stack.size() > 1 && heights[stack.peek()] > heights[i]) {
                int j = stack.pop();
                // deduct the sum from all greater numbers
                curr -= 1L * (j - stack.peek()) * heights[j];
            }
            // replace with sum from number i
            curr += 1L * (i - stack.peek()) * heights[i];
            stack.push(i);
            left[i] = curr;
        }
        stack.clear();
        stack.push(n);
        curr = 0;
        // Construct a non-decreasing monotonic stack from left, curr is the sum from
        // right (inclusive) using i as the peak
        for (int i = n - 1; i >= 0; i--) {
            while (stack.size() > 1 && heights[stack.peek()] > heights[i]) {
                int j = stack.pop();
                // deduct the sum from all greater numbers
                curr -= 1L * (stack.peek() - j) * heights[j];
            }
            // replace with sum from number i
            curr += 1L * (stack.peek() - i) * heights[i];
            stack.push(i);
            res = Math.max(res, left[i] + curr - heights[i]);
        }
        return res;
    }

    // time O(n^2), space O(1)
    public long maximumSumOfHeightsBrutalForce(int[] heights) {
        int n = heights.length;
        long res = 0;
        for (int index = 0; index < n; index++) {
            // pruned unnecessary calculation
            if (res >= 1L * heights[index] * n) {
                continue;
            }
            long sum = heights[index];
            int curr = heights[index], left = index - 1, right = index + 1;
            while (left >= 0) {
                curr = Math.min(heights[left], curr);
                sum += curr;
                left--;
            }
            curr = heights[index];
            while (right < n) {
                curr = Math.min(heights[right], curr);
                sum += curr;
                right++;
            }
            res = Math.max(res, sum);
        }
        return res;
    }
}
