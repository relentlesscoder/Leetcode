package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 08/09/2019.
 * #0559 https://leetcode.com/problems/maximum-depth-of-n-ary-tree/
 */
public class MaximumDepthOfNaryTree {

	private int res = 0;

	// time O(n), space O(n)
	public int maxDepth(Node root) {
		res = 0;
		dfs(root, 1);
		return res;
	}

	private void dfs(Node root, int depth) {
		if (root == null) {
			return;
		}
		res = Math.max(res, depth);
		for (Node child : root.children) {
			dfs(child, depth + 1);
		}
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
