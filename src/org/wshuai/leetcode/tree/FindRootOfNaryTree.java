package org.wshuai.leetcode.tree;

import java.util.List;

/**
 * Created by Wei on 07/12/2020.
 * #1506 https://leetcode.com/problems/find-root-of-n-ary-tree/
 */
public class FindRootOfNaryTree {

    // time O(n), space O(1)
    public Node findRoot(List<Node> tree) {
        // 除了根结点，每一个节点都会有且仅有一条边从其父节点出发到达它。我们
		// 可以在从父节点出发时减去子节点的值而在到达是加上子节点的值。这样整
		// 个树的这个值的和等于根结点的值(其他节点的值会一一抵消)。
        long sum = 0;
		// 计算整个树的出发到达的值的和
        for (Node node : tree) {
            sum += node.val;
            for (Node child : node.children) {
                sum -= child.val;
            }
        }
		// 找到根结点
        for (Node node : tree) {
            if (node.val == sum) {
                return node;
            }
        }
        return null;
    }

    /**
     * Definition for a Node.
     */
    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
