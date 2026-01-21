package org.wshuai.leetcode;

/**
 * Created by Wei on 09/24/2016.
 * #0230 https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 */
public class KthSmallestElementInABST {

    // time O(n), space O(1)
    public int kthSmallestMorris(TreeNode root, int k) {
        // Morris 中序遍历
        int res = 0, idx = 0;
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {
                res = curr.val;
                if (++idx == k) {
                    break;
                }
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
                    res = curr.val;
                    if (++idx == k) {
                        break;
                    }
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
