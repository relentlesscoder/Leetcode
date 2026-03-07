package org.wshuai.leetcode.tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 08/30/2019.
 * #0654 https://leetcode.com/problems/maximum-binary-tree/
 */
public class MaximumBinaryTree {

    // time O(n), space O(n)
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        int n = nums.length;
        Deque<TreeNode> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            TreeNode curr = new TreeNode(nums[i]);
            while (!stack.isEmpty() && stack.peek().val < nums[i]) {
                curr.left = stack.pop();
            }
            if (!stack.isEmpty()) {
                stack.peek().right = curr;
            }
            stack.push(curr);
        }
        return stack.isEmpty() ? null : stack.pollLast();
    }

    // time O(n * log(n)), space O(log(n))
    public TreeNode constructMaximumBinaryTreeDivideAndConquer(int[] nums) {
        // 类似合并排序，对每个区间找最大值。
        return mergeSort(nums, 0, nums.length - 1);
    }

    private TreeNode mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            if (left > right) {
                return null;
            }
            return new TreeNode(nums[left]);
        }
        // 找到最大值的索引
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
        // 用一个线段树来维护区间最大值及他的索引，每次挑选当前区间的最大值为根结点
        // 并用他的索引将区间分成左右两边继续递归。
        int n = nums.length;
        SegmentTree st = new SegmentTree(nums);
        return dfs(0, n - 1, st);
    }

    private TreeNode dfs(int start, int end, SegmentTree st) {
        if (start > end) { // 区间不存在返回空
            return null;
        }
        int[] arr = st.query(start, end); // 找到当前区间的最大值及其索引
        TreeNode root = new TreeNode(arr[0]);
        if (start == end) { // 唯一元素直接返回
            return root;
        }
        root.left = dfs(start, arr[1] - 1, st); // 递归左区间
        root.right = dfs(arr[1] + 1, end, st); // 递归右区间
        return root;
    }

    private static class SegmentTree {

        private final int n;
        private int[][] tree;

        public SegmentTree(int[] nums) {
            this.n = nums.length;
            int size = 2 << (32 - Integer.numberOfLeadingZeros(n - 1));
            this.tree = new int[size][2];
            build(1, 0, n - 1, nums);
        }

        public int[] query(int start, int end) {
            return query(1, 0, n - 1, start, end);
        }

        private int[] query(int node, int left, int right, int start, int end) {
            if (left >= start && right <= end) {
                return tree[node];
            }
            int mid = left + (right - left) / 2;
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

        private void build(int node, int left, int right, int[] nums) {
            if (left == right) {
                tree[node] = new int[]{nums[left], left};
                return;
            }
            int mid = left + (right - left) / 2;
            build(node * 2, left, mid, nums);
            build(node * 2 + 1, mid + 1, right, nums);
            maintain(node);
        }

        private void maintain(int node) {
            tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
        }

        private int[] merge(int[] v1, int[] v2) {
            if (v1[0] > v2[0]) {
                return v1;
            }
            return v2;
        }
    }

    /**
     * Definition for a binary tree node.
     */
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
