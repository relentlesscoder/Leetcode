package org.wshuai.leetcode.other;

/**
 * Created by Wei on 01/15/2026.
 * #LCP44 https://leetcode.cn/problems/sZ59z6/
 */
public class LCP44 {

    // time O(n), space O(MAX)
    public int numColor(TreeNode root) {
        int res = 0;
        int[] freq = new int[1001];
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {
                if (freq[curr.val]++ == 0) {
                    res++;
                }
                curr = curr.right;
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
                    if (freq[curr.val]++ == 0) {
                        res++;
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
