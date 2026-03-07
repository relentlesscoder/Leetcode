package org.wshuai.leetcode.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Wei on 09/08/2023.
 * #2385 https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected/
 */
public class AmountOfTimeForBinaryTreeToBeInfected {

    private int maxChild = 0;
    private int maxParent = 0;

    // time O(n), space O(h)
    public int amountOfTimeBinaryTreeDFS(TreeNode root, int start) {
        // 感染整个二叉树需要的总时间实际上就是求离 start 节点最远的叶子节点的距离。为什么
        // 是叶子节点而不是中间节点？因为如果是中间节点我们总可以从它延伸到一个更远的叶子节
        // 点。节点感染相邻节点可以有三个方向 - 通过左子节点感染左子树，通过右子节点感染右
        // 子树和通过父节点感染非 start 节点为根结点的子树外的节点。左子树和右子树中的叶子
        // 节点和 start 节点的最远距离可以用递归轻松求出来。怎么求通过父节点感染的最远叶子
        // 节点呢？当递归结束遇到 start 节点时，我们需要对所有他的祖先节点求出下面两者的和:
        //   1. 该祖先节点到 start 节点的距离 - 这可以在递归回到 start 节点时将距离重置
        //     为 0 来模拟从 start 出发到该节点的距离。
        //   2. 该祖先节点离另一侧子树的最远叶子节点的距离 - 这个可以在递归时一并求出。
        // 将两者相加即为通过父节点感染最远叶子节点需要的时间。
        maxChild = 0;
        maxParent = 0;
        dfs(root, start);
        return Math.max(maxParent, maxChild);
    }

    private int[] dfs(TreeNode node, int start) {
        // 数组中第一个数表示当前节点离子树中叶子节点的最大距离，第二个数表示当前节点子树
        // 中是否含有 start 节点的标志值。
        if (node == null) {
            return new int[]{0, 0};
        }
        // 递归左右子树
        int[] left = dfs(node.left, start), right = dfs(node.right, start);
        // 如果回到 start 节点
        if (node.val == start) {
            // 计算 start 节点离其左右子树叶子节点的最远距离
            maxChild = Math.max(left[0], right[0]);
            // 将距离重置为 0 并将是否含有 start 节点的标志值设为 1 。
            return new int[]{0, 1};
        }
        // 如果当前节点的左子树中含有 start 节点
        if (left[1] == 1) {
            // 则经过当前节点能感染最远的叶子节点的路径为:
            //   当前节点距离右子树中叶子节点的最远距离 + 当前节点到 start 的距离
            maxParent = Math.max(maxParent, right[0] + left[0] + 1);
            // 注意这里只能返回当前节点到 start 节点的距离
            return new int[]{left[0] + 1, 1};
        }
        // 如果当前节点的右子树中含有 start 节点
        if (right[1] == 1) {
            // 则经过当前节点能感染最远的叶子节点的路径为:
            //   当前节点距离左子树中叶子节点的最远距离 + 当前节点到 start 的距离
            maxParent = Math.max(maxParent, left[0] + right[0] + 1);
            // 注意这里也只能返回当前节点到 start 节点的距离
            return new int[]{right[0] + 1, 1};
        }
        // 没有遇到叶子节点，正常返回到两边叶子节点的最大距离
        return new int[]{Math.max(left[0], right[0]) + 1, 0};
    }

    // time O(n), space O(n)
    public int amountOfTimeGraphBFS(TreeNode root, int start) {
        int res = 0;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        buildGraph(root, null, graph);
        Set<Integer> visited = new HashSet<>();
        visited.add(start);
        LinkedList<Integer> queue = new LinkedList<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int curr = queue.poll();
                for (int next : graph.getOrDefault(curr, new ArrayList<>())) {
                    if (visited.add(next)) {
                        queue.offer(next);
                    }
                }
            }
            res++;
        }
        return res - 1;
    }

    // time O(n), space O(n)
    public int amountOfTimeGraphDFS(TreeNode root, int start) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        buildGraph(root, null, graph);
        Set<Integer> visited = new HashSet<>();
        return dfs(start, 0, 0, visited, graph);
    }

    private int dfs(int node, int max, int current, Set<Integer> visited,
                    Map<Integer, List<Integer>> graph) {
        if (visited.add(node)) {
            max = Math.max(current, max);
            for (int next : graph.getOrDefault(node, new ArrayList<>())) {
                max = Math.max(max, dfs(next, max, current + 1, visited, graph));
            }
        }
        return max;
    }

    private void buildGraph(TreeNode node, TreeNode parent, Map<Integer, List<Integer>> graph) {
        if (node == null) {
            return;
        }
        if (parent != null) {
            graph.computeIfAbsent(parent.val, val -> new ArrayList<>()).add(node.val);
            graph.computeIfAbsent(node.val, val -> new ArrayList<>()).add(parent.val);
        }
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
