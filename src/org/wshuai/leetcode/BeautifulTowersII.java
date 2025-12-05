package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 09/21/2025.
 * #2866 https://leetcode.com/problems/beautiful-towers-ii/
 */
public class BeautifulTowersII {

    // time O(n), space O(n)
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        long res = 0, curr = 0;
        int n = maxHeights.size();
        long[] left = new long[n];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        // Construct a non-decreasing monotonic stack from left, left[i] stores sum from
        // left (inclusive) using i as the peak
        for (int i = 0; i < n; i++) {
            while (stack.size() > 1 && maxHeights.get(stack.peek()) > maxHeights.get(i)) {
                int j = stack.pop();
                // deduct the sum from all greater numbers
                curr -= 1L * (j - stack.peek()) * maxHeights.get(j);
            }
            // replace with sum from number i
            curr += 1L * (i - stack.peek()) * maxHeights.get(i);
            stack.push(i);
            left[i] = curr;
        }
        stack.clear();
        stack.push(n);
        curr = 0;
        // Construct a non-decreasing monotonic stack from left, curr is the sum from
        // right (inclusive) using i as the peak
        for (int i = n - 1; i >= 0; i--) {
            while (stack.size() > 1 && maxHeights.get(stack.peek()) > maxHeights.get(i)) {
                int j = stack.pop();
                // deduct the sum from all greater numbers
                curr -= 1L * (stack.peek() - j) * maxHeights.get(j);
            }
            // replace with sum from number i
            curr += 1L * (stack.peek() - i) * maxHeights.get(i);
            stack.push(i);
            res = Math.max(res, left[i] + curr - maxHeights.get(i));
        }
        return res;
    }
}
