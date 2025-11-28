package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 10/08/2016.
 * #0042 https://leetcode.com/problems/trapping-rain-water/
 */
public class TrappingRainWater {

    // time O(n), space O(1)
    public int trapTwoPointers(int[] height) {
        int res = 0, left = 0, right = height.length - 1, leftMax = 0, rightMax = 0;
        while (left < right) {
            // Move the side with lower max height to guarantee the water
            // can be trapped.
            //   e.g. [0,1,0,2,1,0,1,3,2,1,2,1]
            //   For index 2 (0), the lower max height is 1 at index 0,
            //   meaning it is guaranteed that we can find a higher bar
            //   at right side to trap the water with height[0]
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (leftMax < rightMax) {
                res += leftMax - height[left++];
            } else {
                res += rightMax - height[right--];
            }
        }
        return res;
    }

    // time O(n), space O(n)
    public int trapMonotonicStackWithSentinel(int[] height) {
        int res = 0, n = height.length;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1); // Add left sentinel
        for (int i = 0; i < n; i++) {
            int h = height[i];
            while (stack.size() > 1 && height[stack.peek()] <= h) {
                int b = height[stack.pop()];
                int l = stack.peek();
                int hl = l == -1 ? 0 : height[l];
                res += Math.max(0, (Math.min(hl, h) - b)) * (i - l - 1);
            }
            stack.push(i);
        }
        return res;
    }

    // time O(n), space O(n)
    public int trapMonotonicStack(int[] height) {
        int res = 0, n = height.length;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int right = 0; right < n; right++) {
            int rightHeight = height[right];
            while (!stack.isEmpty() && height[stack.peek()] <= rightHeight) {
                // Since the stack is monotonically decreasing so lower gaps are already
                // filled. The current stack top determines the current bottom height.
                //   e.g. [5,4,3,0,0,1,2,3,7] the gaps are filled from bottom to top
                //   [3(3),x,x,x,x,3]
                //   [2(3),x,x,x,2]
                //   [1(3),0,0,1]
                int bottom = height[stack.pop()];
                // There is no left boundary if stack is empty
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                // Calculate current water height
                int waterHeight = Math.min(height[left], rightHeight) - bottom;
                // Calculate the water area
                res += waterHeight * (right - left - 1);
            }
            stack.push(right);
        }
        return res;
    }

    // time O(n), space O(n), save max from left and right
    public int trapDP(int[] height) {
        int res = 0, max = 0, n = height.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = max;
            max = Math.max(height[i], max);
        }
        max = 0;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Math.min(dp[i], max);
            max = Math.max(max, height[i]);
            res += dp[i] > height[i] ? dp[i] - height[i] : 0;
        }
        return res;
    }
}
