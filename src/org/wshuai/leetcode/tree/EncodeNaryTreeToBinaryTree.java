package org.wshuai.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 12/03/2019.
 * #0431 https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree/
 */
public class EncodeNaryTreeToBinaryTree {

	// 题解: https://leetcode.cn/problems/encode-n-ary-tree-to-binary-tree/solutions/549684/javadi-gui-shi-yi-yang-de-dai-ma-by-mapl-e82q/
    private static class Codec {

        // time O(n), space O(n)
        public TreeNode encode(Node root) {
            if (root == null) {
                return null;
            }
            TreeNode treeNode = new TreeNode(root.val);
            treeNode.left = encodeHelper(root.children);
            return treeNode;
        }

        private TreeNode encodeHelper(List<Node> children) {
            if (children == null || children.isEmpty()) {
                return null;
            }
            TreeNode treeNode = new TreeNode(-1), curr = treeNode;
            for (Node node : children) {
                curr.right = encode(node);
                curr = curr.right;
            }
            return treeNode.right;
        }

		// time O(n), space O(n)
        public Node decode(TreeNode root) {
            if (root == null) {
                return null;
            }
            Node node = new Node(root.val);
            node.children = decodeHelper(root.left);
            return node;
        }

        private List<Node> decodeHelper(TreeNode root) {
            List<Node> children = new ArrayList<>();
            while (root != null) {
                children.add(decode(root));
                root = root.right;
            }
            return children;
        }
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

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(root));
}
