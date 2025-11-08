package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Wei on 01/23/2024.
 * #2519 https://leetcode.com/problems/count-the-number-of-k-big-indices/
 */
public class CountTheNumberOfKBigIndices {

    // time O(n * log(k)), space O(n + k)
    public int kBigIndicesPriorityQueue(int[] nums, int k) {
        int res = 0, n = nums.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        boolean[] prefix = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (pq.size() == k && pq.peek() < nums[i]) {
                prefix[i] = true;
            }
            pq.offer(nums[i]); // Use min queue to save K the smallest numbers we've seen so far
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

    // time O(n * log(m)), space O(n + m)
    public int kBigIndices(int[] nums, int k) {
        int[] sorted = Arrays.stream(nums).distinct().sorted().toArray();
        int res = 0, n = nums.length, m = sorted.length;
        boolean[] left = new boolean[n];
        BIT bit1 = new BIT(m);
        for (int i = 0; i < n; i++) {
            // Index (+1) in BIT with count of numbers >= nums[i]
            int index = binarySearch(sorted, nums[i] + 1);
            // Search index - 1 to find count of numbers < nums[i]
            if (bit1.sum(index - 1) >= k) {
                left[i] = true;
            }
            // Update BIT
            bit1.update(index);
        }
        BIT bit2 = new BIT(m);
        for (int i = 0; i < n; i++) {
            int index = binarySearch(sorted, nums[n - 1 - i] + 1);
            if (bit2.sum(index - 1) >= k && left[n - 1 - i]) {
                res++;
            }
            bit2.update(index);
        }
        return res;
    }

    private int binarySearch(int[] nums, int target) {
        int low = 0, high = nums.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private static class BIT {

        private final int[] tree;

        public BIT(int n) {
            tree = new int[n + 1];
        }

        public void update(int index) {
            while (index < tree.length) {
                tree[index]++;
                index += index & -index;
            }
        }

        public int sum(int index) {
            int res = 0;
            while (index > 0) {
                res += tree[index];
                index -= index & -index;
            }
            return res;
        }
    }
}
