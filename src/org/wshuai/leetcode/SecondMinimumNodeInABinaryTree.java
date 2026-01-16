package org.wshuai.leetcode;

/**
 * Created by Wei on 08/10/2019.
 * #0671 https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/
 */
public class SecondMinimumNodeInABinaryTree {

    // time O(n), space O(1)
    public int findSecondMinimumValueMorris(TreeNode root) {
        // 迷惑条件: 节点的值等于两个子节点较小的
        // 不用处理因为所有节点的值都算，直接找第二小的值就可以。
        long res = Long.MAX_VALUE, min = Long.MAX_VALUE;
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {
                if (curr.val < min) {
                    res = min;
                    min = curr.val;
                } else if (curr.val != min && curr.val < res) {
                    res = curr.val;
                }
                curr = curr.right;
            } else {
                TreeNode pre = curr.left;
                while (pre.right != null && pre.right != curr) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    pre.right = curr;
                    if (curr.val < min) {
                        res = min;
                        min = curr.val;
                    } else if (curr.val != min && curr.val < res) {
                        res = curr.val;
                    }
                    curr = curr.left;
                } else {
                    pre.right = null;
                    curr = curr.right;
                }
            }
        }
        return (int) res;
    }

    // time O(n), space O(log(n))
    public int findSecondMinimumValueRecursive(TreeNode root) {
        long[] min = new long[]{Long.MAX_VALUE, Long.MAX_VALUE};
        findMin(root, min);
        return (int) min[1];
    }

    private void findMin(TreeNode root, long[] min) {
        int curr = root.val;
        if (curr < min[0]) {
            min[1] = min[0];
            min[0] = curr;
        } else if (curr != min[0] && curr < min[1]) {
            min[1] = curr;
        }
        if (root.left == null && root.right == null) {
            return;
        }
        findMin(root.left, min);
        findMin(root.right, min);
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
