package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 10/08/2016.
 * #0042 https://leetcode.com/problems/trapping-rain-water/
 */
public class TrappingRainWater {
	// time O(n), space O(1), two pointers
	public int trap(int[] height) {
		int res = 0, left = 0, right = height.length - 1, leftMax = 0, rightMax = 0;
		while(left < right){
			leftMax = Math.max(leftMax, height[left]);
			rightMax = Math.max(rightMax, height[right]);
			if(leftMax < rightMax){
				res += leftMax - height[left];
				left++;
			}else{
				res += rightMax - height[right];
				right--;
			}
		}
		return res;
	}

	// time O(n), space O(n), save max from left and right
	public int trapDP(int[] height) {
		int res = 0, max = 0, n = height.length;
		int[] dp = new int[n];
		for(int i = 0; i < n; i++){
			dp[i] = max;
			max = Math.max(height[i], max);
		}
		max = 0;
		for(int i = n - 1; i >= 0; i--){
			dp[i] = Math.min(dp[i], max);
			max = Math.max(max, height[i]);
			res += dp[i] > height[i] ? dp[i] - height[i] : 0;
		}
		return res;
	}

	// time O(n), space O(n)
	public int trapStack(int[] height) {
		Stack<Integer> stack = new Stack<>();
		int res = 0, i = 0;
		while(i < height.length){
			if(stack.isEmpty() || height[i] <= height[stack.peek()]){
				stack.push(i++);
			}else{
				// note that this will keep popping out index in the stack
				// until hits a higher height or stack is empty
				int low = stack.pop();
				if(stack.size() == 0){
					continue;
				}
				res += (Math.min(height[i], height[stack.peek()]) - height[low]) * (i - stack.peek() - 1);
			}
		}
		return res;
	}
}
