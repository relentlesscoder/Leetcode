package org.wshuai.leetcode.tree;

/**
 * Created by Wei on 09/20/2016.
 * #0270 https://leetcode.com/problems/closest-binary-search-tree-value/
 */
public class ClosestBinarySearchTreeValue {

    // time O(n), space O(n)
    public int closestValue(TreeNode root, double target) {
        // 利用二叉树的性质来不断缩小节点值与目标值的差从而找到最小差值。
        int res = -1;
        double diff = Double.MAX_VALUE;
        while (root != null) {
            // 如果当前值已经大于目标值，那肯定无法从它的右子树找到更小差值所以
            // 应该去左子树。
            if (root.val > target) {
                double d = root.val - target;
                if (d < diff || (d == diff && root.val < res)) {
                    diff = d;
                    res = root.val;
                }
                root = root.left;
            } else {
                // 如果当前值已经小于目标值，那肯定无法从它的左子树找到更小差值所以
                // 应该去右子树。
                double d = target - root.val;
                if (d == 0) { // 优化: 提前结束如果节点值等于目标值
                    return root.val;
                }
                if (d < diff || (d == diff && root.val < res)) {
                    diff = d;
                    res = root.val;
                }
                root = root.right;
            }
        }
        return res;
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
