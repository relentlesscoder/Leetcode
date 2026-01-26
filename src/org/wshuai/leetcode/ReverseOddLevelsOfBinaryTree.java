package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 12/08/2023.
 * #2415 https://leetcode.com/problems/reverse-odd-levels-of-binary-tree/
 */
public class ReverseOddLevelsOfBinaryTree {

    // time O(n), space O(n)
    public TreeNode reverseOddLevelsDFS(TreeNode root) {
        // #0101 同样思路
        dfs(root.left, root.right, 1);
        return root;
    }

    private void dfs(TreeNode left, TreeNode right, int depth) {
        if (left == null) {
            return;
        }
        if (depth == 1) { // 奇数层则交换节点值
            int temp = left.val;
            left.val = right.val;
            right.val = temp;
        }
        // 以左对右右对左配对继续递归，这样能保证每一层都从前后各取一个节点两两互换
        dfs(left.left, right.right, depth ^ 1);
        dfs(left.right, right.left, depth ^ 1);
    }

    // time O(n), space O(n)
    public TreeNode reverseOddLevelsBFS(TreeNode root) {
        int depth = 0;
        List<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<TreeNode> next = new ArrayList<>();
            // 奇数层则交换节点值
            if (depth == 1) { // 从前后各取一个节点两两互换
                for (int i = 0, j = queue.size() - 1; i < j; i++, j--) {
                    int temp = queue.get(i).val;
                    queue.get(i).val = queue.get(j).val;
                    queue.get(j).val = temp;
                }
            }
            for (TreeNode curr : queue) {
                if (curr.left != null) {
                    next.add(curr.left);
                }
                if (curr.right != null) {
                    next.add(curr.right);
                }
            }
            queue = next;
            depth ^= 1;
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
