package org.wshuai.leetcode.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wei on 01/20/2016.
 * #0145 https://leetcode.com/problems/binary-tree-postorder-traversal/
 */
public class BinaryTreePostorderTraversal {

	// time O(n), space O(1)
    public List<Integer> postorderTraversalMorris(TreeNode root) {
        // #0144 前序遍历变形题
        // 按照顺序 parent -> right -> left 遍历, 即先右后左的前序遍历。
        // 按照顺序 left -> right -> parent 输出, 即将上一步遍历的结果反过来。
        LinkedList<Integer> res = new LinkedList<>();
        TreeNode curr = root;
        while (curr != null) {
            if (curr.right == null) {
                res.offerFirst(curr.val);
                curr = curr.left;
            } else {
                TreeNode pre = curr.right;
                while (pre.left != null && pre.left != curr) {
                    pre = pre.left;
                }
                if (pre.left == null) {
                    pre.left = curr;
                    res.offerFirst(curr.val);
                    curr = curr.right;
                } else {
                    pre.left = null;
                    curr = curr.left;
                }
            }
        }
        return res;
    }

    // time O(n), space O(n)
    public List<Integer> postorderTraversalIterative(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        // 按照 node -> right -> left 顺序遍历
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            // 按照 left -> right -> node 顺序输出
            res.offerFirst(curr.val);
            if (curr.left != null) {
                stack.push(curr.left);
            }
            if (curr.right != null) {
                stack.push(curr.right);
            }
        }
        return res;
    }

    // time O(n), space O(log(n))
    public List<Integer> postorderTraversalRecursive(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postorder(root, res);
        return res;
    }

    private void postorder(TreeNode node, List<Integer> res) {
        if (node == null) {
            return;
        }
        postorder(node.left, res);
        postorder(node.right, res);
        res.add(node.val);
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
