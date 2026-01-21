package org.wshuai.leetcode;

/**
 * Created by Wei on 05/24/2020.
 * #1457 https://leetcode.com/problems/pseudo-palindromic-paths-in-a-binary-tree/
 */
public class PseudoPalindromicPathsInABinaryTree {

    private int res = 0;

    // time O(n), space O(n)
    public int pseudoPalindromicPathsBitMask(TreeNode root) {
        // 优化 - 用一个整型取代数组保存 1 到 9 每个数的奇偶状态
        res = 0;
        dfs1(root, 0);
        return res;
    }

    private void dfs1(TreeNode root, int mask) {
        if (root == null) {
            return;
        }
        mask ^= (1 << root.val);
        if (root.left == null && root.right == null) {
            if (Integer.bitCount(mask) <= 1) {
                res++;
            }
        } else {
            dfs1(root.left, mask);
            dfs1(root.right, mask);
        }
    }

    // time O(n), space O(n)
    public int pseudoPalindromicPathsFreqArray(TreeNode root) {
        res = 0;
        dfs2(root, new int[10]);
        return res;
    }

    private void dfs2(TreeNode root, int[] freq) {
        if (root == null) {
            return;
        }
        freq[root.val] ^= 1;
        // 如果是叶子节点则判断是否可以形成回文
        if (root.left == null && root.right == null) {
            int sum = 0;
            for (int i = 1; i <= 9; i++) {
                sum += freq[i];
            }
            if (sum <= 1) {
                res++;
            }
        } else { // 不是叶子节点继续递归
            dfs2(root.left, freq);
            dfs2(root.right, freq);
        }
        // 活干完后恢复现场
        freq[root.val] ^= 1;
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
