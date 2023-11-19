package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 05/16/2021.
 * #1673 https://leetcode.com/problems/find-the-most-competitive-subsequence/
 */
public class FindTheMostCompetitiveSubsequence {

    // time O(n), space O(k)
    public int[] mostCompetitive(int[] nums, int k) {
        int[] res = new int[k];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < nums.length; i++){
            // maintain a monotonic increasing queue to push the small element as left as possible
            while(!stack.isEmpty() && stack.peek() > nums[i] && nums.length - i + stack.size() > k){
                stack.pop();
            }
            if(stack.size() < k){
                stack.push(nums[i]);
            }
        }
        for(int i = k - 1; i >= 0; i--){
            res[i] = stack.pop();
        }
        return res;
    }
}
