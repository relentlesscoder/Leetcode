package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 01/19/2026.
 * #2792 https://leetcode.com/problems/count-nodes-that-are-great-enough/
 */
public class CountNodesThatAreGreatEnough {

    private int res = 0;

    // time O(n * k), space O(h + k)
    public int countGreatEnoughNodes(TreeNode root, int k) {
        res = 0;
        dfs(root, k);
        return res;
    }

    private List<Integer> dfs(TreeNode root, int k) {
        // 因为 k 值很小，可以对每个节点维护一个大小最大为 k 的 list 存以当前节点为根结点的
        // 子树中最小的 k 个节点的值。
        if (root == null) {
            return new ArrayList<>();
        }
        // 递归左右子树
        List<Integer> list = new ArrayList<>(),
                left = dfs(root.left, k),
                right = dfs(root.right, k);
        // 合并 sorted list
        for (int i = 0, j = 0, s = 0; s++ < k && (i < left.size() || j < right.size()); ) { // O(k)
            int l = i == left.size() ? Integer.MAX_VALUE : left.get(i);
            int r = j == right.size() ? Integer.MAX_VALUE : right.get(j);
            if (l < r) {
                list.add(left.get(i++));
            } else {
                list.add(right.get(j++));
            }
        }
        // list 的大小为 k 并且最大值小于当前节点的值
        if (list.size() == k && root.val > list.get(k - 1)) {
            res++;
        } else { // 两种情况需要把当前节点值加入到 list
            // list 的 大小小于 k
            if (list.size() < k) {
                list.add(root.val);
            } else if (root.val < list.get(k - 1)) {
                // list 的大小等于 k 但是最大值小于当前节点值，把最大值替换成当前节点的值
                list.set(k - 1, root.val);
            }
            // 交换排序将当前节点的值放到正确的位置
            for (int x = list.size() - 1; x > 0; x--) { // O(k)
                if (list.get(x) < list.get(x - 1)) {
                    int temp = list.get(x);
                    list.set(x, list.get(x - 1));
                    list.set(x - 1, temp);
                }
            }
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
