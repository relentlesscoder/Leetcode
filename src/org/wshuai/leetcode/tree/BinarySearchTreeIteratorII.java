package org.wshuai.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 09/20/2020.
 * #1586 https://leetcode.com/problems/binary-search-tree-iterator-ii/
 */
public class BinarySearchTreeIteratorII {

    // time O(n), space O(n)
    private static class BSTIterator {

        private TreeNode curr;
        private final List<Integer> nodes;
        private int idx = -1;

        public BSTIterator(TreeNode root) {
            // 用 list 存已经遍历过的节点值，idx 表示当前的索引。如果索引在 list 的范围中
            // 则直接返回 list 中的值否则继续遍历二叉树。
            nodes = new ArrayList<>();
            curr = root;
        }

        public boolean hasNext() {
            return inRange(idx + 1) || curr != null;
        }

        public int next() {
            if (inRange(idx + 1)) {
                return nodes.get(++idx);
            }
            while (curr != null) {
                if (curr.left == null) {
                    int res = curr.val;
                    idx++;
                    nodes.add(res);
                    curr = curr.right;
                    return res;
                } else {
                    TreeNode pre = curr.left;
                    while (pre.right != null && pre.right != curr) {
                        pre = pre.right;
                    }
                    if (pre.right == null) {
                        pre.right = curr;
                        curr = curr.left;
                    } else {
                        pre.right = null;
                        int res = curr.val;
                        idx++;
                        nodes.add(res);
                        curr = curr.right;
                        return res;
                    }
                }
            }
            return -1;
        }

        public boolean hasPrev() {
            return inRange(idx - 1);
        }

        public int prev() {
            return nodes.get(--idx);
        }

        private boolean inRange(int index) {
            return index >= 0 && index < nodes.size();
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

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * boolean param_1 = obj.hasNext();
 * int param_2 = obj.next();
 * boolean param_3 = obj.hasPrev();
 * int param_4 = obj.prev();
 */
}
