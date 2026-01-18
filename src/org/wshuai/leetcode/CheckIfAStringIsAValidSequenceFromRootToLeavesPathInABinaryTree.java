package org.wshuai.leetcode;

/**
 * Created by Wei on 05/01/2020.
 * #1430 https://leetcode.com/problems/check-if-a-string-is-a-valid-sequence-from-root-to-leaves-path-in-a-binary-tree/
 */
public class CheckIfAStringIsAValidSequenceFromRootToLeavesPathInABinaryTree {

    // time O(n), space O(h)
    public boolean isValidSequence(TreeNode root, int[] arr) {
        return dfs(root, arr, 0);
    }

    private boolean dfs(TreeNode root, int[] arr, int idx) {
        // 节点为空或者索引溢出则当前路径不可行
        if (root == null || idx == arr.length) {
            return false;
        }
        if (root.val != arr[idx]) { // 值不匹配则当前路径也不可行
            return false;
        } else if (root.left == null // 匹配到数组最后一个值并且当前节点为叶子则路径可行
                && root.right == null
                && idx == arr.length - 1) {
            return true;
        }
        // 遍历左右子树
        return dfs(root.left, arr, idx + 1)
                || dfs(root.right, arr, idx + 1);
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
