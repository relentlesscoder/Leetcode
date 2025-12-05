package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Created by Wei on 10/04/2016.
 * #0084 https://leetcode.com/problems/largest-rectangle-in-histogram/
 */
public class LargestRectangleInHistogram {

    // time O(n), space O(n)
    public int largestRectangleArea(int[] heights) {
		// https://leetcode.cn/problems/largest-rectangle-in-histogram/solutions/2695467/dan-diao-zhan-fu-ti-dan-pythonjavacgojsr-89s7/
        int res = 0, n = heights.length;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1); // Left sentinel -1
        for (int right = 0; right <= n; right++) {
            int h = right < n ? heights[right] : -1; // Right sentinel n
            // Now we calculate the rectangle for each index i when it popped
            // out from the stack. We know right[i] is h from below one pass
            // solution. Since stack is monotonic increasing, the current stack
            // top after i is popped out is the left.
            while (stack.size() > 1 && heights[stack.peek()] >= h) {
                int curr = stack.pop();
                int left = stack.peek();
                res = Math.max(res, heights[curr] * (right - left - 1));
            }
            stack.push(right);
        }
        return res;
    }

    // time O(n), space O(n)
    public int largestRectangleAreaMonotonicStackOnePass(int[] heights) {
        int res = 0, n = heights.length;
        int[] left = new int[n], right = new int[n];
        Arrays.fill(right, n); // Right sentinel n
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1); // Left sentinel -1
        for (int i = 0; i < n; i++) {
            // Now we find right boundary by heights[stack.peek()] >= heights[i]
            //   e.g. [1,3,4,3,2]
            // For index 1 (3), right[1] = 3 which is wrong since right[1] should
            // be 4 but this will not impact the final result since for index 3 (3)
            // right[3] = 4, the correct rectangle will still be formed by indexes
            // [1,3] when we process index 3.
            while (stack.size() > 1 && heights[stack.peek()] >= heights[i]) {
                right[stack.pop()] = i;
            }
            left[i] = stack.peek();
            stack.push(i);
        }
        for (int i = 0; i < n; i++) {
            res = Math.max(res, heights[i] * (right[i] - left[i] - 1));
        }
        return res;
    }

    // time O(n), space O(n)
    public int largestRectangleAreaMonotonicStackTwoPass(int[] heights) {
        // For each bar heights[i], we need to find the left and right boundary
        // bars (indexes l and r) that can form a rectangle with area:
        //   heights[i] x (r - l + 1)
        // Since only bars with height >= heights[i] on i's both sides can be used
        // to form such a rectangle, we can use monotonic stack to find the first
        // bar at index j on i's left that has heights[j] < heights[i] then l = j + 1.
        // By the same way, we can find the first bar at index k on i's right that
        // has heights[k] < heights[i] then r = k - 1. So now the formula is:
        //   heights[i] x (k - j - 1)
        int res = 0, n = heights.length;
        int[] left = new int[n], right = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1); // Left sentinel
        for (int i = 0; i < n; i++) {
            while (stack.size() > 1 && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            left[i] = stack.peek();
            stack.push(i);
        }
        stack.clear();
        stack.push(n); // Right sentinel
        for (int i = n - 1; i >= 0; i--) {
            while (stack.size() > 1 && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            right[i] = stack.peek();
            stack.push(i);
        }
        for (int i = 0; i < n; i++) {
            res = Math.max(res, heights[i] * (right[i] - left[i] - 1));
        }
        return res;
    }
}
