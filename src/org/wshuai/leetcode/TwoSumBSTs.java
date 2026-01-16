package org.wshuai.leetcode;

/**
 * Created by Wei on 10/09/2019.
 * #1214 https://leetcode.com/problems/two-sum-bsts/
 */
public class TwoSumBSTs {

    // time O(n + m), space O(1)
    public boolean twoSumBSTsMorrisTraversal(TreeNode root1, TreeNode root2, int target) {
        // 利用 BST 的中序遍历是按照值由小到大排序的性质
        BSTIterator itr1 = new BSTIterator(root1); // 顺序遍历 root1
        BSTReverseIterator itr2 = new BSTReverseIterator(root2); // 逆序遍历 root2
        for (Integer v1 = itr1.next(), v2 = itr2.next(); v1 != null && v2 != null; ) {
            int sum = v1.intValue() + v2.intValue();
            if (sum == target) { // 找到和要求的一对
                return true;
            } else if (sum < target) { // 和太小找 v1 的下一个值
                v1 = itr1.next();
            } else { // 和太大找 v2 的下一个值
                v2 = itr2.next();
            }
        }
        return false;
    }

    private static class BSTIterator {

        private TreeNode curr;

        public BSTIterator(TreeNode root) {
            this.curr = root;
        }

        public Integer next() {
            while (curr != null) {
                if (curr.left == null) {
                    int val = curr.val;
                    curr = curr.right;
                    return val;
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
                        int val = curr.val;
                        curr = curr.right;
                        return val;
                    }
                }
            }
            return null;
        }
    }

    private static class BSTReverseIterator {

        private TreeNode curr;

        public BSTReverseIterator(TreeNode root) {
            this.curr = root;
        }

        public Integer next() {
            while (curr != null) {
                if (curr.right == null) {
                    int val = curr.val;
                    curr = curr.left;
                    return val;
                } else {
                    TreeNode pre = curr.right;
                    while (pre.left != null && pre.left != curr) {
                        pre = pre.left;
                    }
                    if (pre.left == null) {
                        pre.left = curr;
                        curr = curr.right;
                    } else {
                        pre.left = null;
                        int val = curr.val;
                        curr = curr.left;
                        return val;
                    }
                }
            }
            return null;
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
