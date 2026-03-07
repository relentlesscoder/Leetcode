package org.wshuai.leetcode.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/30/2016.
 * #0106 https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {

    // time O(n), space O(n)
    public TreeNode buildTree(int[] inorder, int[] postorder) {
		// #0105 同样的思路
        // 对后序数组中的每个根节点 - 最右边那个节点，利用中序遍历算出左子树和右子树的大小。
        // 递归左右子树在前序数组中的区间以构造二叉树。
        int n = inorder.length;
        // 节点值到中序遍历索引的哈希表
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        return dfs(0, n - 1, 0, n - 1,
                postorder, map);
    }

    private TreeNode dfs(int postLeft, int postRight, int inLeft, int inRight,
                         int[] postorder, Map<Integer, Integer> inorder) {
        if (postLeft > postRight || inLeft > inRight) {
            return null;
        }
        // 当前子树的区间的根结点就是后序数组中最右边的节点
        TreeNode root = new TreeNode(postorder[postRight]);
        // 找到他在中序数组中的位置
        int idx = inorder.get(postorder[postRight]);
        // 计算左子树所在区间
        root.left = dfs(postLeft, postLeft + idx - inLeft - 1,
                inLeft, idx - 1, postorder, inorder);
        // 计算右子树所在区间
        root.right = dfs(postLeft + idx - inLeft, postRight - 1,
                idx + 1, inRight, postorder, inorder);
        return root;
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
