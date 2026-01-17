package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 06/06/2020.
 * #1469 https://leetcode.com/problems/find-all-the-lonely-nodes/
 */
public class FindAllTheLonelyNodes {

    // time O(n), space O(h)
    public List<Integer> getLonelyNodesRecursive(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        checkNode(root, res);
        return res;
    }

    private void checkNode(TreeNode node, List<Integer> res) {
        if (node.left == null && node.right == null) {
            return;
        }
        if (!(node.left != null && node.right != null)) {
            res.add(node.left == null ? node.right.val : node.left.val);
        }
        if (node.left != null) {
            checkNode(node.left, res);
        }
        if (node.right != null) {
            checkNode(node.right, res);
        }
    }

    // time O(n), space O(n)
    public List<Integer> getLonelyNodesIterative(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode curr = root;
        Deque<TreeNode> queue = new ArrayDeque<>();
        while (curr != null || !queue.isEmpty()) {
            if (curr != null) {
                queue.push(curr);
                curr = curr.left;
            } else {
                TreeNode node = queue.pop();
                checkNode1(node, res);
                curr = node.right;
            }
        }
        return res;
    }

    private void checkNode1(TreeNode node, List<Integer> res) {
        if (node.left == null && node.right == null) {
            return;
        }
        if (node.left != null && node.right != null) {
            return;
        }
        res.add(node.left == null ? node.right.val : node.left.val);
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
