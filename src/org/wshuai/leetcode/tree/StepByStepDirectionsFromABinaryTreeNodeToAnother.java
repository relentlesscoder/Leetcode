package org.wshuai.leetcode.tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * Created by Wei on 09/18/2023.
 * #2096 https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/
 */
public class StepByStepDirectionsFromABinaryTreeNodeToAnother {

    // time O(n), space O(n)
    public String getDirectionsLCA(TreeNode root, int startValue, int destValue) {
        // 先找到两个节点的 LCA ，在以 LCA 为根结点的二叉树找到路径
        StringBuilder startPath = new StringBuilder();
        StringBuilder destPath = new StringBuilder();
        // 找到两个节点的 LCA
        TreeNode lca = lowestCommonAncestor(root, startValue, destValue);
        // 递归以 LCA 为根结点的二叉树分别找到开始节点和结束节点的从它到根结点的路径 (从下往上)
        findNode(lca, startValue, startPath);
        findNode(lca, destValue, destPath);
        // 1. 将开始节点的路径全部置换成 U
        // 2. 将结束节点的路径翻转
        // 3. 将两个路径拼在一起即为答案
        return "U".repeat(startPath.length()) + destPath.reverse().toString();
    }

    private TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
        if (root == null || root.val == p || root.val == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }

    // time O(n), space O(n)
    public String getDirectionsCommonPath(TreeNode root, int startValue, int destValue) {
        // 递归二叉树分别找到开始节点和结束节点的从它到根结点的路径 (从下往上)
        StringBuilder sbStart = new StringBuilder(), sbDest = new StringBuilder();
        findNode(root, startValue, sbStart);
        findNode(root, destValue, sbDest);
        int i = sbStart.length() - 1, j = sbDest.length() - 1;
        // 反向遍历路径，找到根结点出发到两个节点最低公共祖先节点的共同路径
        for (; i >= 0 && j >= 0 && sbStart.charAt(i) == sbDest.charAt(j); i--, j--) {
        }
        // 1. 丢弃共同路径
        // 2. 将开始节点的路径全部置换成 U
        // 3. 将结束节点的路径翻转
        // 4. 将两个路径拼在一起即为答案
        return "U".repeat(i + 1) + sbDest.reverse().substring(sbDest.length() - 1 - j);
    }

    private boolean findNode(TreeNode node, int val, StringBuilder sb) {
        if (node == null) {
            return false;
        }
        // 找到目标节点
        if (node.val == val) {
            return true;
        }
        boolean res = false;
        // 递归左子树
        if (findNode(node.left, val, sb)) {
            res = true;
            // 更新 path 如果找到目标节点
            sb.append("L");
        } else if (findNode(node.right, val, sb)) { // // 递归右子树
            res = true;
            // 更新 path 如果找到目标节点
            sb.append("R");
        }
        return res;
    }

    // time O(n), space O(n)
    public String getDirectionsBFS(TreeNode root, int startValue, int destValue) {
        Map<Integer, int[]> graph = new HashMap<>();
        buildGraph(root, null, graph);
        Set<Integer> visited = new HashSet<>();
        LinkedList<Integer> nodeQueue = new LinkedList<>();
        LinkedList<String> pathQueue = new LinkedList<>();
        visited.add(startValue);
        nodeQueue.offerLast(startValue);
        pathQueue.offerLast("");
        while (!nodeQueue.isEmpty()) {
            int curr = nodeQueue.pollFirst();
            String path = pathQueue.pollFirst();
            if (curr == destValue) {
                return path;
            }
            int[] next = graph.get(curr);
            if (next[0] != -1 && visited.add(next[0])) {
                nodeQueue.offerLast(next[0]);
                pathQueue.offerLast(path + "U");
            }
            if (next[1] != -1 && visited.add(next[1])) {
                nodeQueue.offerLast(next[1]);
                pathQueue.offerLast(path + "L");
            }
            if (next[2] != -1 && visited.add(next[2])) {
                nodeQueue.offerLast(next[2]);
                pathQueue.offerLast(path + "R");
            }
        }
        return "";
    }

    // time O(n), space O(n)
    public String getDirectionsDFS(TreeNode root, int startValue, int destValue) {
        Map<Integer, int[]> graph = new HashMap<>();
        buildGraph(root, null, graph);
        Set<Integer> visited = new HashSet<>();
        return dfs(startValue, destValue, "", visited, graph);
    }

    private String dfs(int curr, int dest, String path, Set<Integer> visited, Map<Integer, int[]> graph) {
        if (curr == dest) {
            return path;
        }
        visited.add(curr);
        int[] next = graph.get(curr);
        if (next[0] != -1 && visited.add(next[0])) {
            String upPath = dfs(next[0], dest, path + "U", visited, graph);
            if (upPath != "") {
                return upPath;
            }
        }
        if (next[1] != -1 && visited.add(next[1])) {
            String leftPath = dfs(next[1], dest, path + "L", visited, graph);
            if (leftPath != "") {
                return leftPath;
            }
        }
        if (next[2] != -1 && visited.add(next[2])) {
            String rightPath = dfs(next[2], dest, path + "R", visited, graph);
            if (rightPath != "") {
                return rightPath;
            }
        }
        return "";
    }

    private void buildGraph(TreeNode node, TreeNode parent, Map<Integer, int[]> graph) {
        if (node == null) {
            return;
        }
        int[] nodes = new int[3];
        nodes[0] = parent != null ? parent.val : -1;
        nodes[1] = node.left != null ? node.left.val : -1;
        nodes[2] = node.right != null ? node.right.val : -1;
        graph.put(node.val, nodes);
        buildGraph(node.left, node, graph);
        buildGraph(node.right, node, graph);
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
