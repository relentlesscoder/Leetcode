package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 10/08/2023.
 * #1944 https://leetcode.com/problems/number-of-visible-people-in-a-queue/
 */
public class NumberOfVisiblePeopleInAQueue {

    // time O(n), space O(n)
    public int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        int[] res = new int[n];
		// Maintain a decreasing monotonic queue
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0); // sentinel
        for (int i = 0; i < n; i++) {
			// Since monotonic queue is decreasing, every stack top with heights[stack.peek()]
			// < heights[i] can see ith people since all people in the middle are lower than both
			// of them
            while (stack.size() > 1 && heights[stack.peek()] < heights[i]) {
                res[stack.pop()]++;
            }
			// Now heights[stack.peek()] > heights[i] and all people in the middle lower than heights[i]
			// are popped out so people at index stack.peek() can also see ith people
            if (stack.size() > 1) {
                res[stack.peek()]++;
            }
            stack.push(i);
        }
        return res;
    }
}
