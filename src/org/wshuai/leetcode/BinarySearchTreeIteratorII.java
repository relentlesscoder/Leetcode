package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Wei on 09/20/2020.
 * #1586 https://leetcode.com/problems/binary-search-tree-iterator-ii/
 */
public class BinarySearchTreeIteratorII {

    private Stack<TreeNode> stack;
    private List<TreeNode> list;
    private int index;

    public BinarySearchTreeIteratorII(TreeNode root) {
        stack = new Stack<>();
        list = new ArrayList<>();
        index = -1;
        pushLeft(root);
    }

    private void pushLeft(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    private boolean inRange(int i) {
        return i >= 0 && i < list.size();
    }

    public boolean hasNext() {
        return inRange(index + 1) || !stack.isEmpty();
    }

    public int next() {
        int res = -1;
        if (inRange(index + 1)) {
            res = list.get(index + 1).val;
        } else {
            TreeNode next = stack.pop();
            pushLeft(next.right);
            list.add(next);
            res = next.val;
        }
        index++;
        return res;
    }

    public boolean hasPrev() {
        return inRange(index - 1);
    }

    public int prev() {
        return list.get(--index).val;
    }

    //Definition for a binary tree node.
    private class TreeNode {
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
