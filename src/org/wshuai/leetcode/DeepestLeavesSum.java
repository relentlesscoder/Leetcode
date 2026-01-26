package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 12/30/2019.
 * #1302 https://leetcode.com/problems/deepest-leaves-sum/
 */
public class DeepestLeavesSum {

    // time O(n), space O(n)
    public int deepestLeavesSum(TreeNode root) {
        // #1161 同样思路
        List<Integer> nums = new ArrayList<>();
        dfs(root, 0, nums);
        return nums.get(nums.size() - 1);
    }

    private void dfs(TreeNode root, int depth, List<Integer> nums) {
        if (root == null) {
            return;
        }
        if (nums.size() == depth) { // 加入该层第一个节点
            nums.add(root.val);
        } else { // 更新对应层的节点和
            nums.set(depth, root.val + nums.get(depth));
        }
        dfs(root.left, depth + 1, nums);
        dfs(root.right, depth + 1, nums);
    }

    // time O(n), space O(n)
    public int deepestLeavesSumBFS(TreeNode root) {
        // 依次计算每一层的节点和，最后一层的和即为答案
        int res = 0;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size(), sum = 0;
            while (size-- > 0) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res = sum;
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
