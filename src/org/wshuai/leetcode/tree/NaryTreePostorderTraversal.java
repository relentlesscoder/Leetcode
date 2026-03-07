package org.wshuai.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 08/08/2019.
 * #0590 https://leetcode.com/problems/n-ary-tree-postorder-traversal/
 */
public class NaryTreePostorderTraversal {

	// time O(n), space O(n)
	public List<Integer> postorder(Node root) {
		List<Integer> res = new ArrayList<>();
		dfs(root, res);
		return res;
	}

	private void dfs(Node root, List<Integer> res) {
		if (root == null) {
			return;
		}
		for (Node child : root.children) {
			dfs(child, res);
		}
		res.add(root.val);
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
