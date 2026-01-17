package org.wshuai.leetcode;

/**
 * Created by Wei on 01/12/2020.
 * #1315 https://leetcode.com/problems/sum-of-nodes-with-even-valued-grandparent/
 */
public class SumOfNodesWithEvenValuedGrandparent {

    // time O(n), space O(h)
    public int sumEvenGrandparent(TreeNode root) {
        // 递归二叉树，对每个节点传入父节点和祖父节点。统计祖父节点为偶数的节点的和。
        return dfs(root, -1, -1);
    }

    private int dfs(TreeNode root, int parent, int grandParent) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        if (grandParent > 0 && (grandParent & 1) == 0) {
            res += root.val;
        }
        res += dfs(root.left, root.val, parent);
        res += dfs(root.right, root.val, parent);
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
