package org.wshuai.leetcode;

/**
 * Created by Wei on 08/19/2019.
 * #0783 https://leetcode.com/problems/minimum-distance-between-bst-nodes/
 */
public class MinimumDistanceBetweenBSTNodes {

    // time O(n), space O(1)
    public int minDiffInBSTMorris(TreeNode root) {
		// 同 #0530
        int res = Integer.MAX_VALUE, last = -1;
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {
                if (last != -1) {
                    res = Math.min(res, curr.val - last);
                }
                last = curr.val;
                curr = curr.right;
            } else {
                TreeNode node = curr.left;
                while (node.right != null && node.right != curr) {
                    node = node.right;
                }
                if (node.right == null) {
                    node.right = curr;
                    curr = curr.left;
                } else {
                    node.right = null;
                    if (last != -1) {
                        res = Math.min(res, curr.val - last);
                    }
                    last = curr.val;
                    curr = curr.right;
                }
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
