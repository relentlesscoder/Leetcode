package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Wei on 11/15/2025.
 * #2770 https://leetcode.com/problems/maximum-number-of-jumps-to-reach-the-last-index/
 */
public class MaximumNumberOfJumpsToReachTheLastIndex {

    // time O(n * log(n)), space O(n)
    public int maximumJumpsSegmentTreeWithDiscretization(int[] nums, int target) {
        int n = nums.length;
        List<Long> vals = new ArrayList<>();
        for (int num : nums) {
            vals.add(1L * num);
            vals.add(1L * num - target);
            vals.add(1L * num + target);
        }
        Collections.sort(vals);
        int m = vals.size();
        SegmentTree st = new SegmentTree(m + 1);
        int index = binarySearch(vals, nums[0]);
        st.update(index, index, 0);
        for (int i = 1; i < n; i++) {
            int lower = binarySearch(vals, 1L * nums[i] - target);
            int upper = binarySearch(vals, 1L * nums[i] + target);
            int max = st.query(lower, upper);
            index = binarySearch(vals, nums[i]);
            st.update(index, index, max + 1);
        }
        index = binarySearch(vals, nums[n - 1]);
        int res = st.query(index, index);
        return res < 0 ? -1 : res;
    }

    private int binarySearch(List<Long> nums, long target) {
        int low = 0, high = nums.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private class SegmentTree {

        private final int n;
        private final Node[] tree;

        public SegmentTree(int n) {
            this.n = n;
            tree = new Node[2 << (32 - Integer.numberOfLeadingZeros(n))];
            Arrays.setAll(tree, i -> new Node());
        }

        public int query(int start, int end) {
            return query(1, 0, n - 1, start, end);
        }

        public void update(int start, int end, int val) {
            update(1, 0, n - 1, start, end, val);
        }

        private int query(int node, int left, int right, int start, int end) {
            if (left >= start && right <= end) {
                return tree[node].val;
            }
            spread(node);
            int mid = left + (right - left) / 2;
            if (end <= mid) {
                return query(node * 2, left, mid, start, end);
            }
            if (start > mid) {
                return query(node * 2 + 1, mid + 1, right, start, end);
            }
            int lr = query(node * 2, left, mid, start, end);
            int rr = query(node * 2 + 1, mid + 1, right, start, end);
            return Math.max(lr, rr);
        }

        private void update(int node, int left, int right, int start, int end, int val) {
            if (left >= start && right <= end) {
                tree[node].val = val;
                tree[node].mark = val;
                return;
            }
            spread(node);
            int mid = left + (right - left) / 2;
            if (start <= mid) {
                update(node * 2, left, mid, start, end, val);
            }
            if (end > mid) {
                update(node * 2 + 1, mid + 1, right, start, end, val);
            }
            maintain(node);
        }

        private void spread(int node) {
            if (tree[node].mark == 0) {
                return;
            }
            tree[node * 2].val = tree[node].mark;
            tree[node * 2 + 1].val = tree[node].mark;
            tree[node * 2].mark = tree[node].mark;
            tree[node * 2 + 1].mark = tree[node].mark;
            tree[node].mark = 0;
        }

        private void maintain(int node) {
            tree[node].val = Math.max(tree[node * 2].val, tree[node * 2 + 1].val);
        }

        private class Node {
            Node left;
            Node right;
            int val;
            int mark;

            public Node() {
                val = Integer.MIN_VALUE;
                mark = 0;
            }
        }
    }

    // time O(n * log(MAX)), space O(n * log(MAX))
    public int maximumJumpsDynamicSegmentTree(int[] nums, int target) {
        int n = nums.length;
        DynamicSegmentTree st = new DynamicSegmentTree();
        st.update(nums[0], nums[0], 0);
        for (int i = 1; i < n; i++) {
            long max = st.query(1L * nums[i] - target, 1L *  nums[i] + target);
            st.update(nums[i], nums[i], max + 1);
        }
        long res = st.query(nums[n - 1], nums[n - 1]);
        return res < 0 ? -1 : (int) res;
    }

    private class DynamicSegmentTree {

        private static final long MAX = (int) 1e9;
        private final Node root;

        public DynamicSegmentTree() {
            root = new Node();
        }

        public long query(long start, long end) {
            return query(root, -MAX, MAX, start, end);
        }

        public void update(long start, long end, long val) {
            update(root, -MAX, MAX, start, end, val);
        }

        private long query(Node node, long left, long right, long start, long end) {
            if (left >= start && right <= end) {
                return node.val;
            }
            spread(node);
            long mid = left + (right - left) / 2;
            if (end <= mid) {
                return query(node.left, left, mid, start, end);
            }
            if (start > mid) {
                return query(node.right, mid + 1, right, start, end);
            }
            long lr = query(node.left, left, mid, start, end);
            long rr = query(node.right, mid + 1, right, start, end);
            return Math.max(lr, rr);
        }

        private void update(Node node, long left, long right, long start, long end, long val) {
            if (left >= start && right <= end) {
                node.val = val;
                node.mark = val;
                return;
            }
            spread(node);
            long mid = left + (right - left) / 2;
            if (start <= mid) {
                update(node.left, left, mid, start, end, val);
            }
            if (end > mid) {
                update(node.right, mid + 1, right, start, end, val);
            }
            maintain(node);
        }

        private void spread(Node node) {
            if (node.left == null) {
                node.left = new Node();
            }
            if (node.right == null) {
                node.right = new Node();
            }
            if (node.mark == 0) {
                return;
            }
            node.left.val = node.mark;
            node.right.val = node.mark;
            node.left.mark = node.mark;
            node.right.mark = node.mark;
            node.mark = 0;
        }

        private void maintain(Node node) {
            node.val = Math.max(node.left.val, node.right.val);
        }

        private class Node {
            Node left;
            Node right;
            long val;
            long mark;

            public Node() {
                val = Integer.MIN_VALUE;
                mark = 0;
            }
        }
    }

    // time O(n^2), space O(n)
    public int maximumJumpsDP(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (Math.abs(nums[i] - nums[j]) <= target) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[n - 1] < 0 ? -1 : dp[n - 1];
    }
}
