package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by Wei on 11/29/2025.
 * #3672 https://leetcode.com/problems/sum-of-weighted-modes-in-subarrays/
 */
public class SumOfWeightedModesInSubarrays {

    // time O(n * log(k)), space O(n)
    public long modeWeightPriorityQueue(int[] nums, int k) {
        long res = 0;
        int n = nums.length;
        PriorityQueue<int[]> maxQueue = new PriorityQueue<>((a, b) ->
                a[1] == b[1] ? a[0] - b[0] : b[1] - a[1]);
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int count = freq.merge(nums[i], 1, Integer::sum);
            maxQueue.offer(new int[] {nums[i], count});

            int left = i - k + 1;
            if (left < 0) {
                continue;
            }

            int[] ans = maxQueue.peek();
            res += (long) ans[0] * ans[1];

            count = freq.merge(nums[left], -1, Integer::sum);
            if (count == 0) {
                freq.remove(nums[left]);
            }

            // Prune the queue
            while (!maxQueue.isEmpty()
                    && maxQueue.peek()[1] != freq.getOrDefault(maxQueue.peek()[0], 0)) {
                maxQueue.poll();
            }
        }
        return res;
    }

    // time O(n * log(m)), space O(m)
    public long modeWeightSegmentTree(int[] nums, int k) {
        long res = 0;
        int[] sorted = Arrays.stream(nums).distinct().sorted().toArray(); // O(m * log(m))
        int n = nums.length, m = sorted.length;
        Map<Integer, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < m; i++) { // O(m)
            idxMap.put(sorted[i], i);
        }
        SegmentTree st = new SegmentTree(m);
        for (int i = 0; i < n; i++) { // O(n)
            int idx = idxMap.get(nums[i]);
            st.update(idx, nums[i], 1); // O(log(m))

            int left = i - k + 1;
            if (left < 0) {
                continue;
            }

            int[] ans = st.getMax();
            res += (long) ans[0] * ans[1];

            idx = idxMap.get(nums[left]);
            st.update(idx, nums[left], -1); // O(log(m))
        }
        return res;
    }

    private static class SegmentTree {

        private final int n;
        private final int[][] tree;

        public SegmentTree(int n) {
            this.n = n;
            tree = new int[2 << (32 - Integer.numberOfLeadingZeros(n - 1))][2];
        }

        public int[] getMax() {
            return tree[1];
        }

        public void update(int index, int val, int count) {
            update(1, 0, n - 1, index, val, count);
        }

        private void update(int node, int left, int right, int index, int val, int count) {
            if (left == right) {
                tree[node][0] = val;
                tree[node][1] += count;
                return;
            }
            int mid = left + (right - left) / 2;
            if (index <= mid) {
                update(node * 2, left, mid, index, val, count);
            } else {
                update(node * 2 + 1, mid + 1, right, index, val, count);
            }
            maintain(node);
        }

        private void maintain(int node) {
            if (tree[node * 2][1] > tree[node * 2 + 1][1]) {
                tree[node][0] = tree[node * 2][0];
                tree[node][1] = tree[node * 2][1];
            } else if (tree[node * 2][1] < tree[node * 2 + 1][1]) {
                tree[node][0] = tree[node * 2 + 1][0];
                tree[node][1] = tree[node * 2 + 1][1];
            } else {
                tree[node][0] = Math.min(tree[node * 2][0], tree[node * 2 + 1][0]);
                tree[node][1] = tree[node * 2][1];
            }
        }
    }
}
