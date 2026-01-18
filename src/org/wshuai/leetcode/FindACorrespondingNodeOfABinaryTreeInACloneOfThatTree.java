package org.wshuai.leetcode;

/**
 * Created by Wei on 03/13/2020.
 * #1379 https://leetcode.com/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree/
 */
public class FindACorrespondingNodeOfABinaryTreeInACloneOfThatTree {

    private TreeNode res;

    // time O(n), space O(h)
    public final TreeNode getTargetCopy(
            final TreeNode original,
            final TreeNode cloned,
            final TreeNode target
    ) {
        res = null;
        dfs(original, cloned, target);
        return res;
    }

    private void dfs(
            final TreeNode original,
            final TreeNode cloned,
            final TreeNode target
    ) {
        if (original == null || res != null) {
            return;
        }
        if (original == target) {
            res = cloned;
            return;
        }
        dfs(original.left, cloned.left, target);
        dfs(original.right, cloned.right, target);
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
