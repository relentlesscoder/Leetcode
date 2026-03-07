package org.wshuai.leetcode.tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Wei on 12/05/2020.
 * #1660 https://leetcode.com/problems/correct-a-binary-tree/
 */
public class CorrectABinaryTree {

    // time O(n), space O(n)
    public TreeNode correctBinaryTreeDFS(TreeNode root) {
        correct(root, null, new HashSet<>());
        return root;
    }

    private void correct(TreeNode root, TreeNode parent, Set<TreeNode> visited) {
        // 从右往左递归，问题节点应该已经先被遍历过了
        if (root == null) {
            return;
        }
        // 找到问题节点
        if (root.right != null && visited.contains(root.right)) {
            root.right = null;
            // 删除问题节点
            if (parent.left == root) {
                parent.left = null;
            } else if (parent.right == root) {
                parent.right = null;
            }
            return;
        }
        visited.add(root);
        // 先右后左
        correct(root.right, root, visited);
        correct(root.left, root, visited);
    }

    // time O(n), space O(n)
    public TreeNode correctBinaryTreeBFS(TreeNode root) {
        TreeNode target = null;
        // 哈希表保存子节点到父节点
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        Set<TreeNode> queue = new HashSet<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Set<TreeNode> next = new HashSet<>();
            for (TreeNode curr : queue) {
                if (curr.left != null) {
                    next.add(curr.left);
                    parentMap.put(curr.left, curr);
                }
                // 如果当前节点的右节点已经在当前层节点的节点表中则找到目标节点
                if (curr.right != null && queue.contains(curr.right)) {
                    target = curr;
                    break;
                } else if (curr.right != null) {
                    next.add(curr.right);
                    parentMap.put(curr.right, curr);
                }
            }
            queue = next;
        }
        // 删除错误的指针
        target.right = null;
        // 删除问题节点
        TreeNode parent = parentMap.get(target);
        if (parent.left == target) {
            parent.left = null;
        } else if (parent.right == target) {
            parent.right = null;
        }
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
