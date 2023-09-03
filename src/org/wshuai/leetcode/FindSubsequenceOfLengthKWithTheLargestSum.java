package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Wei on 09/03/2023.
 * #2099 https://leetcode.com/problems/find-subsequence-of-length-k-with-the-largest-sum/description/
 */
public class FindSubsequenceOfLengthKWithTheLargestSum {

    // time O(n*log(n)), space O(n)
    public int[] maxSubsequence(int[] nums, int k) {
        int[] res = new int[k];
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < nums.length; i++) {
            queue.offer(new int[] {nums[i], i});
            if (queue.size() > k) {
                queue.poll();
            }
        }
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll()[1];
        }
        Arrays.sort(res);
        for (int i = 0; i < k; i++) {
            res[i] = nums[res[i]];
        }
        return res;
    }
}
