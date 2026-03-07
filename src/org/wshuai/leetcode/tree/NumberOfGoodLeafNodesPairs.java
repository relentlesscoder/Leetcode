package org.wshuai.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 07/26/2020.
 * #1530 https://leetcode.com/problems/number-of-good-leaf-nodes-pairs/
 */
public class NumberOfGoodLeafNodesPairs {

    private int res = 0;

    // time O(n), space O(h)
    public int countPairsArray(TreeNode root, int distance) {
        // 因为 distance 上限很小可以有 array 来取代 list 来进行优化
        res = 0;
        dfsArray(root, distance);
        return res;
    }

    private int[] dfsArray(TreeNode root, int d) {
        int[] arr = new int[d + 1];
        if (root == null) {
            return arr;
        }
        if (root.left == null && root.right == null) {
            arr[1]++;
            return arr;
        }
        int[] left = dfsArray(root.left, d), right = dfsArray(root.right, d);
        for (int i = 1; i < d; i++) {
            for (int j = 1; j < d; j++) {
                if (i + j <= d) {
                    res += left[i] * right[j];
                }
            }
        }
        for (int i = 1; i < d - 1; i++) {
            if (left[i] > 0) {
                arr[i + 1] += left[i];
            }
            if (right[i] > 0) {
                arr[i + 1] += right[i];
            }
        }
        return arr;
    }

    // time O(n), space O(h)
    public int countPairsList(TreeNode root, int distance) {
        res = 0;
        dfsList(root, distance);
        return res;
    }

    private List<Integer> dfsList(TreeNode root, int d) {
        // 用一个 list 存当前节点的所有叶子节点到它的距离
        if (root == null) {
            return new ArrayList<>();
        }
        // 如果是叶子节点，则加入一个距离为 1 到 list 中。注意这里的距离为 1
        // 指的是叶子节点到它父节点的距离
        if (root.left == null && root.right == null) {
            return List.of(1);
        }
        // 递归左右子树
        List<Integer> left = dfsList(root.left, d), right = dfsList(root.right, d);
        // 连个叶子节点的最短距离就是它们到它们共同的前辈节点的距离之和。
        for (int l : left) {
            for (int r : right) {
                if (l + r <= d) {
                    res++;
                }
            }
        }
        // 合并所有叶子节点到当前节点下并将距离加 1
        List<Integer> list = new ArrayList<>();
        for (int l : left) {
            if (l + 1 >= d) {
                continue;
            }
            list.add(l + 1);
        }
        for (int r : right) {
            if (r + 1 >= d) {
                continue;
            }
            list.add(r + 1);
        }
        return list;
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
