package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 01/10/2024.
 * #2471 https://leetcode.com/problems/minimum-number-of-operations-to-sort-a-binary-tree-by-level/
 */
public class MinimumNumberOfOperationsToSortABinaryTreeByLevel {

    // time O(n * log(n)), space O(n)
    public int minimumOperations(TreeNode root) {
        int res = 0;
        List<TreeNode> curr = new ArrayList<>();
        curr.add(root);
        while (curr.size() > 0) {
            res += cycleSort(curr);
            List<TreeNode> next = new ArrayList<>();
            for (int i = 0; i < curr.size(); i++) {
                TreeNode node = curr.get(i);
                if (node.left != null) {
                    next.add(node.left);
                }
                if (node.right != null) {
                    next.add(node.right);
                }
            }
            curr = next;
        }
        return res;
    }

    private int cycleSort(List<TreeNode> nodes) {
        if (nodes.size() == 1) {
            return 0;
        }
        int res = 0, n = nodes.size();
        int[] nums = new int[n], sorted = new int[n];
        for (int i = 0; i < n; i++) {
            TreeNode node = nodes.get(i);
            nums[i] = node.val;
            sorted[i] = node.val;
        }
        Arrays.sort(sorted);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(sorted[i], i);
        }
        for (int i = 0; i < n; i++) {
            while (i != map.get(nums[i])) {
                int j = map.get(nums[i]);
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                res++;
            }
        }
        return res;
    }

    /**
     * Definition for a binary tree node.
     */
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
