package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Wei on 11/23/2025.
 * #3113 https://leetcode.com/problems/find-the-number-of-subarrays-where-boundary-elements-are-maximum/
 */
public class FindTheNumberOfSubarraysWhereBoundaryElementsAreMaximum {

    // time O(n), space O(n)
    public long numberOfSubarrays(int[] nums) {
        long res = nums.length;
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[] {Integer.MAX_VALUE, 0}); // Sentinel
        for (int num : nums) {
            while (stack.peek()[0] < num) {
                stack.pop();
            }
            if (num == stack.peek()[0]) {
                res += stack.peek()[1]++;
            } else {
                stack.push(new int[] {num, 1});
            }
        }
        return res;
    }

    // time O(n * log(n)), space O(n)
    public long numberOfSubarraysBinaryIndexedTree(int[] nums) {
        int n = nums.length;
        long res = n;
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        BIT bit = new BIT(n);
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
            bit.update(i + 1, 1);
        }
        // Iterate all indexes for all values in nums from min to max
        for (List<Integer> idx : map.values()) {
            long curr = 0;
            for (int i = 1; i < idx.size(); i++) {
                int count = bit.query(idx.get(i - 1) + 1, idx.get(i) + 1);
                // If count is 2, meaning all values between two boundary elements
                // are smaller since smaller numbers were deleted in previous processing.
                if (count == 2) {
                    res += ++curr;
                } else {
                    curr = 0;
                }
                // Remove number at current index
                bit.update(idx.get(i - 1) + 1, -1);
            }
            // Remove number at last index
            bit.update(idx.get(idx.size() - 1) + 1, -1);
        }
        return res;
    }

    private static class BIT {

        private int[] tree;

        public BIT(int n) {
            tree = new int[n + 1];
        }

        public void update(int index, int val) {
            while (index < tree.length) {
                tree[index] += val;
                index += (index & -index);
            }
        }

        public int query(int left, int right) {
            return pre(right) - pre(left - 1);
        }

        private int pre(int index) {
            int res = 0;
            while (index > 0) {
                res += tree[index];
                index -= (index & -index);
            }
            return res;
        }
    }

    // time O(n * log(n)), space O(n)
    public long numberOfSubarraysSegmentTree(int[] nums) {
        int n = nums.length;
        long res = n;
        SegmentTree st = new SegmentTree(n);
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
            st.update(i, nums[i]);
        }
        // Iterate all indexes for all values in nums
        for (List<Integer> idx : map.values()) {
            int val = nums[idx.get(0)];
            long curr = 0;
            for (int i = 1; i < idx.size(); i++) {
                int max = st.query(idx.get(i - 1), idx.get(i));
                // max == val means all values between two boundary elements
                // are smaller.
                if (max == val) {
                    res += ++curr;
                } else {
                    curr = 0;
                }
            }
        }
        return res;
    }

    private static class SegmentTree {

        private final int n;
        private final int[] tree;

        public SegmentTree(int n) {
            this.n = n;
            tree = new int[2 << (32 - Integer.numberOfLeadingZeros(n - 1))];
        }

        public void update(int index, int val) {
            update(1, 0, n - 1, index, val);
        }

        public int query(int start, int end) {
            return query(1, 0, n - 1, start, end);
        }

        private void update(int node, int left, int right, int index, int val) {
            if (left == right) {
                tree[node] = val;
                return;
            }
            int mid = left + (right - left) / 2;
            if (index <= mid) {
                update(node * 2, left, mid, index, val);
            } else {
                update(node * 2 + 1, mid + 1, right, index, val);
            }
            maintain(node);
        }

        private int query(int node, int left, int right, int start, int end) {
            if (left >= start && right <= end) {
                return tree[node];
            }
            int mid = left + (right - left) / 2;
            if (end <= mid) {
                return query(node * 2, left, mid, start, end);
            } else if (start > mid) {
                return query(node * 2 + 1, mid + 1, right, start, end);
            }
            int lr = query(node * 2, left, mid, start, end);
            int rr = query(node * 2 + 1, mid + 1, right, start, end);
            return merge(lr, rr);
        }

        private void maintain(int node) {
            tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
        }

        private int merge(int v1, int v2) {
            return Math.max(v1, v2);
        }
    }
}
