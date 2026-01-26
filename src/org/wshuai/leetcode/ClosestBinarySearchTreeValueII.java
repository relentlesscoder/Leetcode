package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wei on 07/06/2017.
 * #0272 https://leetcode.com/problems/closest-binary-search-tree-value-ii/
 */
public class ClosestBinarySearchTreeValueII {

    // time O(k + log(n)), space O(n)
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> small = new ArrayDeque<>(), large = new ArrayDeque<>();
        // 利用二叉树的性质进行搜索，将当前路径上的节点分别加入两个栈中
        while (root != null) {
            if (root.val <= target) {
                small.push(root);
                root = root.right;
            } else {
                large.push(root);
                root = root.left;
            }
        }
        while (k-- > 0) {
            double leftDiff = small.isEmpty() ? Double.MAX_VALUE : target - small.peek().val;
            double rightDiff = large.isEmpty() ? Double.MAX_VALUE : large.peek().val - target;
            // 如果左边的值较小，将左边的值加入答案中并将左子树的右节点加入栈中
            if (leftDiff <= rightDiff) {
                TreeNode node = small.pop();
                res.add(node.val);
                node = node.left;
                while (node != null) {
                    small.push(node);
                    node = node.right;
                }
            } else { // 如果右边的值较小，将右边的值加入答案中并将右子树的左节点加入栈中
                TreeNode node = large.pop();
                res.add(node.val);
                node = node.right;
                while (node != null) {
                    large.push(node);
                    node = node.left;
                }
            }
        }
        return res;
    }

    // time O(n), space O(n)
    public List<Integer> closestKValuesMorris(TreeNode root, double target, int k) {
        // Morris 遍历找到与目标值最接近的 k 个元素
        LinkedList<Integer> res = new LinkedList<>();
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {
                if (res.size() < k) {
                    res.add(curr.val);
                } else if (Math.abs(target - res.peek()) > Math.abs(target - curr.val)) {
                    res.poll();
                    res.add(curr.val);
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
                    if (res.size() < k) {
                        res.add(curr.val);
                    } else if (Math.abs(target - res.peek()) > Math.abs(target - curr.val)) {
                        res.poll();
                        res.add(curr.val);
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
