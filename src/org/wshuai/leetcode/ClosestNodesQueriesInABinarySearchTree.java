package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 01/21/2026.
 * #2476 https://leetcode.com/problems/closest-nodes-queries-in-a-binary-search-tree/
 */
public class ClosestNodesQueriesInABinarySearchTree {

    // time O(m + n * log(n)), space O(n)
    public List<List<Integer>> closestNodesMorris(TreeNode root, List<Integer> queries) {
        List<List<Integer>> res = new ArrayList<>();
        int n = queries.size();
        Integer[] ids = new Integer[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
            res.add(new ArrayList<>(Arrays.asList(-1, -1)));
        }
        Arrays.sort(ids, (a, b) -> queries.get(a) - queries.get(b));
        int last = -1, idx = 0;
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {
                while (idx < n && curr.val >= queries.get(ids[idx])) {
                    res.get(ids[idx]).set(0, curr.val == queries.get(ids[idx]) ? curr.val : last);
                    res.get(ids[idx]).set(1, curr.val);
                    idx++;
                }
                last = curr.val;
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
                    while (idx < n && curr.val >= queries.get(ids[idx])) {
                        res.get(ids[idx]).set(0, curr.val == queries.get(ids[idx]) ? curr.val : last);
                        res.get(ids[idx]).set(1, curr.val);
                        idx++;
                    }
                    last = curr.val;
                    curr = curr.right;
                }
            }
        }
        while (idx < n) {
            res.get(ids[idx++]).set(0, last);
        }
        return res;
    }

    // time O(m + n * log(n)), space O(m + n)
    public List<List<Integer>> closestNodesStack(TreeNode root, List<Integer> queries) {
        List<List<Integer>> res = new ArrayList<>();
        int n = queries.size();
        Integer[] ids = new Integer[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
            res.add(new ArrayList<>(Arrays.asList(-1, -1)));
        }
        Arrays.sort(ids, (a, b) -> queries.get(a) - queries.get(b));
        int last = -1, idx = 0;
        TreeNode curr = root;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                TreeNode node = stack.pop();
                while (idx < n && node.val >= queries.get(ids[idx])) {
                    res.get(ids[idx]).set(0, node.val == queries.get(ids[idx]) ? node.val : last);
                    res.get(ids[idx]).set(1, node.val);
                    idx++;
                }
                last = node.val;
                curr = node.right;
            }
        }
        while (idx < n) {
            res.get(ids[idx++]).set(0, last);
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
