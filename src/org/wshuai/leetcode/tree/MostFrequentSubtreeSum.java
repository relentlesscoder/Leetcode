package org.wshuai.leetcode.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 02/26/2017.
 * #0508 https://leetcode.com/problems/most-frequent-subtree-sum/
 */
public class MostFrequentSubtreeSum {

    private int maxFreq = 0;
    private final Map<Integer, Integer> sumMap = new HashMap<>();

    public int[] findFrequentTreeSum(TreeNode root) {
        // 递归二叉树，对每个节点计算以它为根结点的子树的节点和。维护一个哈希表存节点和及频率和
        // 一个变量存节点和的频率的最大值。递归结束后，在哈希表中找到所有频率等于最大频率的节点
        // 和组成数组即为答案。
        maxFreq = 0;
        sumMap.clear();
        dfs(root);
        List<Integer> nums = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : sumMap.entrySet()) {
            if (entry.getValue() == maxFreq) {
                nums.add(entry.getKey());
            }
        }
        int[] res = new int[nums.size()];
        for (int i = 0; i < nums.size(); i++) {
            res[i] = nums.get(i);
        }
        return res;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = root.val + dfs(root.left) + dfs(root.right);
        int cnt = sumMap.merge(sum, 1, Integer::sum);
        maxFreq = Math.max(maxFreq, cnt);
        return sum;
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
