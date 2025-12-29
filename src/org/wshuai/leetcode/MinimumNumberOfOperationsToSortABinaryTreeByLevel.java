package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Wei on 01/10/2024.
 * #2471 https://leetcode.com/problems/minimum-number-of-operations-to-sort-a-binary-tree-by-level/
 */
public class MinimumNumberOfOperationsToSortABinaryTreeByLevel {

    // time O(n * log(n)), space O(n)
    public int minimumOperations(TreeNode root) {
        // #3551的变形题
        int level = 0, last = 0;
        List<int[]> nums = new ArrayList<>();
        List<TreeNode> curr = new ArrayList<>();
        curr.add(root);
        while (!curr.isEmpty()) {
            List<TreeNode> next = new ArrayList<>();
            for (int i = 0; i < curr.size(); i++) {
                TreeNode node = curr.get(i);
                nums.add(new int[]{node.val, level, last + i});
                if (node.left != null) {
                    next.add(node.left);
                }
                if (node.right != null) {
                    next.add(node.right);
                }
            }
            last += curr.size();
            curr = next;
            level++;
        }
        int n = nums.size();
        Collections.sort(nums, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            uf.union(i, nums.get(i)[2]);
        }
        return n - uf.countComponents();
    }

    private class UnionFind {

        private int count;
        private int[] root;
        private int[] rank;

        public UnionFind(int n) {
            this.count = n;
            root = new int[n];
            rank = new int[n];
            Arrays.fill(rank, 1);
            Arrays.setAll(root, i -> i);
        }

        public int find(int x) {
            if (x != root[x]) {
                root[x] = find(root[x]);
            }
            return root[x];
        }

        public void union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            if (rank[rootX] > rank[rootY]) {
                rank[rootX] += rank[rootY];
                root[rootY] = rootX;
            } else {
                rank[rootY] += rank[rootX];
                root[rootX] = rootY;
            }
            this.count--;
        }

        public int countComponents() {
            return this.count;
        }
    }

    // time O(n * log(n)), space O(n)
    public int minimumOperationsCycleSort(TreeNode root) {
        int res = 0, level = 0, last = 0;
        List<int[]> nums = new ArrayList<>();
        List<TreeNode> curr = new ArrayList<>();
        curr.add(root);
        while (!curr.isEmpty()) {
            List<TreeNode> next = new ArrayList<>();
            for (int i = 0; i < curr.size(); i++) {
                TreeNode node = curr.get(i);
                nums.add(new int[]{node.val, level, last + i});
                if (node.left != null) {
                    next.add(node.left);
                }
                if (node.right != null) {
                    next.add(node.right);
                }
            }
            last += curr.size();
            curr = next;
            level++;
        }
        int n = nums.size();
        Collections.sort(nums, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        // 循环排序
        for (int i = 0; i < n; i++) {
            while (i != nums.get(i)[2]) {
                int idx = nums.get(i)[2];
                int[] temp = nums.get(i);
                nums.set(i, nums.get(nums.get(i)[2]));
                nums.set(idx, temp);
                res++;
            }
        }
        return res;
    }

    /**
     * Definition for a binary tree node.
     */
    private class TreeNode {
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
