package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 01/23/2024.
 * #2519 https://leetcode.com/problems/count-the-number-of-k-big-indices/
 */
public class CountTheNumberOfKBigIndices {

    // time O(n * log(k)), space O(n)
    public int kBigIndices(int[] nums, int k) {
        int res = 0, n = nums.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        boolean[] prefix = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (pq.size() == k && pq.peek() < nums[i]) {
                prefix[i] = true;
            }
            pq.offer(nums[i]); // Use min queue to save K smallest numbers we've seen so far
            if (pq.size() > k) {
                pq.poll();
            }
        }
        pq = new PriorityQueue<>((a, b) -> b - a);
        for (int i = n - 1; i >= 0; i--) {
            if (pq.size() == k && pq.peek() < nums[i] && prefix[i]) {
                res++;
            }
            pq.offer(nums[i]);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return res;
    }
}
