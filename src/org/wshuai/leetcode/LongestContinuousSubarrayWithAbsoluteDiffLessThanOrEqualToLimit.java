package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 05/03/2020.
 * #1438 https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/
 */
public class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {
    // time O(n), space O(n)
    // sliding window max/min using monotonic queue
    // https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/discuss/609771/JavaC%2B%2BPython-Deques-O(N)
    public int longestSubarray(int[] nums, int limit) {
        int res = 1, n = nums.length;
        LinkedList<Integer> ascQueue = new LinkedList<>();
        LinkedList<Integer> descQueue = new LinkedList<>();
        for(int i = 0, j = 0; j < n; j++){
            while(!ascQueue.isEmpty() && nums[ascQueue.peekLast()] > nums[j]){
                ascQueue.pollLast();
            }
            ascQueue.offerLast(j);
            while(!descQueue.isEmpty() && nums[descQueue.peekLast()] < nums[j]){
                descQueue.pollLast();
            }
            descQueue.offerLast(j);
            while(nums[descQueue.peekFirst()] - nums[ascQueue.peekFirst()] > limit){
                if(ascQueue.peekFirst() == i){
                    ascQueue.pollFirst();
                }
                if(descQueue.peekFirst() == i){
                    descQueue.pollFirst();
                }
                i++;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }
}
