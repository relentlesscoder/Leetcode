package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * Created by Wei on 12/23/2020.
 * #1696 https://leetcode.com/problems/jump-game-vi/
 */
public class JumpGameVI {

    // time O(n), space O(n)
    public int maxResult(int[] nums, int k) {
        ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
        queue.offerLast(0);
        for(int i = 1; i < nums.length; i++){
            if(queue.peekFirst() + k < i){
                queue.pollFirst();
            }
            nums[i] += nums[queue.peekFirst()]; // to get current max ends at i, adding first since it is the max
            // maintain a monotonic decreasing queue to store result from previous steps
            while(!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]){
                queue.pollLast();
            }
            queue.offerLast(i);
        }
        return nums[nums.length - 1];
    }

    // time O(n^2), space O(n)
    public int maxResultDP(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = nums[0];
        for(int i = 0; i < n; i++){
            for(int j = 1; j <= k && i + j < n; j++){
                dp[i + j] = Math.max(dp[i + j], dp[i] + nums[i + j]);
                // break early to get as many non-negative numbers as possible
                if(nums[i + j] >= 0){
                    break;
                }
            }
        }
        return dp[n - 1];
    }
}
