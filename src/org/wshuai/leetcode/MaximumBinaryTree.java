package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 08/30/2019.
 * #0654 https://leetcode.com/problems/maximum-binary-tree/
 */
public class MaximumBinaryTree {

    // time O(n), space O(n)
    public TreeNode constructMaximumBinaryTreeMonotonicStack(int[] nums) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        for (int num : nums) {
            TreeNode cur = new TreeNode(num);
            // Find the max from left side of num that is less than num
            // and set it to left child
            while (!stack.isEmpty() && stack.peek().val < num) {
                cur.left = stack.pop();
            }
            // If the top is grater then num, set num as its right child
            if (!stack.isEmpty()) {
                stack.peek().right = cur;
            }
            stack.push(cur);
        }
        return stack.isEmpty() ? null : stack.pollLast();
    }

    // time O(n * log(n)), space O(log(n))
    public TreeNode constructMaximumBinaryTreeDivideAndConquer(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    private TreeNode mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            if (left > right) {
                return null;
            }
            return new TreeNode(nums[left]);
        }
        int idx = left;
        for (int i = left; i <= right; i++) {
            if (nums[i] > nums[idx]) {
                idx = i;
            }
        }
        TreeNode node = new TreeNode(nums[idx]);
        node.left = mergeSort(nums, left, idx - 1);
        node.right = mergeSort(nums, idx + 1, right);
        return node;
    }

    // time O(n * log(n)), space O(n)
    public TreeNode constructMaximumBinaryTreeSegmentTree(int[] nums) {
        int n = nums.length;
        SegmentTree st = new SegmentTree(n);
        for (int i = 0; i < n; i++) {
            st.update(i, nums[i]);
        }
        return dfs(nums, 0, nums.length - 1, st);
    }

    private TreeNode dfs(int[] nums, int left, int right, SegmentTree st) {
        if (left >= right) {
            if (left > right) {
                return null;
            }
            return new TreeNode(nums[left]);
        }
        int[] res = st.query(left, right);
        int val = res[0], idx = res[1];
        TreeNode node = new TreeNode(val);
        node.left = dfs(nums, left, idx - 1, st);
        node.right = dfs(nums, idx + 1, right, st);
        return node;
    }

    private static class SegmentTree {

        private final int n;
        private final int[][] tree;

        public SegmentTree(int n) {
            this.n = n;
            tree = new int[2 << (32 - Integer.numberOfLeadingZeros(n - 1))][2];
        }

        public void update(int index, int val) {
            update(1, 0, n - 1, index, val);
        }

        private void update(int node, int left, int right, int index, int val) {
            if (left == right) {
                tree[node] = new int[]{val, index};
                return;
            }
            int mid = (left + right) / 2;
            if (index <= mid) {
                update(node * 2, left, mid, index, val);
            } else {
                update(node * 2 + 1, mid + 1, right, index, val);
            }
            maintain(node);
        }

        public int[] query(int start, int end) {
            return query(1, 0, n - 1, start, end);
        }

        private int[] query(int node, int left, int right, int start, int end) {
            if (left >= start && right <= end) {
                return tree[node];
            }
            int mid = (left + right) / 2;
            if (end <= mid) {
                return query(node * 2, left, mid, start, end);
            }
            if (start > mid) {
                return query(node * 2 + 1, mid + 1, right, start, end);
            }
            int[] lr = query(node * 2, left, mid, start, end);
            int[] rr = query(node * 2 + 1, mid + 1, right, start, end);
            return merge(lr, rr);
        }

        private int[] merge(int[] a, int[] b) {
            return a[0] > b[0] ? a : b;
        }

        private void maintain(int node) {
            tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
        }
    }
}
