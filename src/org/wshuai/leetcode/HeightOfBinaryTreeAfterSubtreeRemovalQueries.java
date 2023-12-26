package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 12/26/2023.
 * #2458 https://leetcode.com/problems/height-of-binary-tree-after-subtree-removal-queries/
 */
public class HeightOfBinaryTreeAfterSubtreeRemovalQueries {

    // time O(n), space O(n)
    public int[] treeQueries(TreeNode root, int[] queries) {
        // https://leetcode.com/problems/height-of-binary-tree-after-subtree-removal-queries/solutions/2757990/python-3-explanation-with-pictures-dfs/
        Map<Integer, int[][]> levelMax = new HashMap<>();
        Map<Integer, int[]> nodeResults = new HashMap<>();
        findSubtreeHeight(root, 0, levelMax, nodeResults);
        findQueryResult(root, levelMax, nodeResults);
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            res[i] = nodeResults.get(queries[i])[1];
        }
        return res;
    }

    private void findQueryResult(TreeNode node, Map<Integer, int[][]> levelMax, Map<Integer, int[]> nodeResults) {
        if (node == null) {
            return;
        }
        int[] res = nodeResults.get(node.val);
        int level = res[0];
        int[][] max = levelMax.get(res[0]);
        if (max[0][1] != node.val) { // current node has a cousin (nodes on the same level as node) that has the max height
            res[1] = level - 1 + max[0][0];
        } else if (max[1][1] == -1) { // current node has no cousin
            res[1] = level - 1;
        } else {
            res[1] = level - 1 + max[1][0]; // current node has the max height, find the cousin with the second max height
        }
        findQueryResult(node.left, levelMax, nodeResults);
        findQueryResult(node.right, levelMax, nodeResults);
    }

    private int findSubtreeHeight(TreeNode node, int level, Map<Integer, int[][]> levelMax, Map<Integer, int[]> nodeResults) {
        if (node == null) {
            return 0;
        }
        nodeResults.put(node.val, new int[]{level, -1});
        int left = findSubtreeHeight(node.left, level + 1, levelMax, nodeResults);
        int right = findSubtreeHeight(node.right, level + 1, levelMax, nodeResults);
        int height = 1 + Math.max(left, right);
        levelMax.putIfAbsent(level, new int[][]{
                {-1, -1},
                {-1, -1}
        });
        int[][] max = levelMax.get(level);
        if (height > max[0][0]) {
            max[1] = max[0];
            max[0] = new int[]{height, node.val};
        } else if (height > max[1][0]) {
            max[1] = new int[]{height, node.val};
        }
        return height;
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
