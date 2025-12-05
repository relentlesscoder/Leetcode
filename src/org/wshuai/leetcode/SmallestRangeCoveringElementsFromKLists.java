package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Wei on 12/16/2019.
 * #0632 https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/
 */
public class SmallestRangeCoveringElementsFromKLists {

    private static final int UPPER = (int) 1e6;

    // time O(n * log(n)), space O(n)
    public int[] smallestRangeSlidingWindow(List<List<Integer>> nums) {
        int k = nums.size(), minRange = UPPER, left = -UPPER, right = UPPER;
        List<int[]> sorted = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            for (int num : nums.get(i)) {
                sorted.add(new int[]{num, i});
            }
        }
        Collections.sort(sorted, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int n = sorted.size();
        int[] freq = new int[k];
        for (int i = 0, j = 0, count = 0; i < n; i++) {
            int[] curr = sorted.get(i);
            if (freq[curr[1]]++ == 0) {
                count++;
            }
            while (count == k) {
                if (curr[0] - sorted.get(j)[0] < minRange) {
                    minRange = curr[0] - sorted.get(j)[0];
                    left = sorted.get(j)[0];
                    right = curr[0];
                }
                if (--freq[sorted.get(j++)[1]] == 0) {
                    count--;
                }
            }
        }
        return new int[]{left, right};
    }

    // O(n * log(k)), space O(k)
    public int[] smallestRangePriorityQueue(List<List<Integer>> nums) {
        int k = nums.size(), upper = -UPPER;
        PriorityQueue<int[]> minQueue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < k; i++) { // O(k * log(k))
            // Push {first number, list id and next index}
            // from each list to the min queue
            minQueue.offer(new int[]{nums.get(i).get(0), i, 0});
            upper = Math.max(upper, nums.get(i).get(0));
        }
        int left = minQueue.peek()[0];
        int right = upper;
        while (minQueue.peek()[2] + 1 < nums.get(minQueue.peek()[1]).size()) { // O(n)
            int[] top = minQueue.poll(); // O(log(k))
            top[0] = nums.get(top[1]).get(++top[2]);
            upper = Math.max(upper, top[0]);
            minQueue.offer(top);
            int lower = minQueue.peek()[0];
            if (upper - lower < right - left) {
                right = upper;
                left = lower;
            }
        }
        return new int[]{left, right};
    }
}
