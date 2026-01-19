package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 01/18/2026.
 * #3319 https://leetcode.com/problems/k-th-largest-perfect-subtree-size-in-binary-tree/
 */
public class KthLargestPerfectSubtreeSizeInBinaryTree {

    private PriorityQueue<Integer> minQueue = new PriorityQueue<>();

    // time O(n + k * log(k)), space O(h + k)
    public int kthLargestPerfectSubtree(TreeNode root, int k) {
        // 维护一个长度为 k 的最小堆，递归二叉树将所有完美二叉树的大小放入堆中。
        // 最后返回堆顶元素即可。
        minQueue.clear();
        dfs(root, k);
        return minQueue.size() == k ? minQueue.peek() : -1;
    }

    private int dfs(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left, k), right = dfs(root.right, k);
        // 左子树和右子树有相同的节点则当前节点为一个完美子树根结点。注意由于我们是从
        // 叶子节点一路递归上来所以每个树中每个节点都满足这个要求因此只用判断左右子树
        // 节点一样就可以了。
        if (left >= 0 && left == right) {
            minQueue.offer(1 + left + right);
            // 维护最小堆的大小为 k
            if (minQueue.size() > k) {
                minQueue.poll();
            }
            return 1 + left + right;
        }
        // 发现非完美子树，则它的所有前辈节点都不会完美。
        return -1;
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
