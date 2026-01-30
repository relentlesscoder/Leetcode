package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 10/02/2016.
 * #0297 https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 */
public class SerializeAndDeserializeBinaryTree {

	// time O(n), space O(n)
    private static class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder res = new StringBuilder();
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node == null) {
                    res.append("n,");
                } else {
                    res.append(node.val + ",");
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }
            return res.substring(0, res.length() - 1);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.isEmpty() || data.charAt(0) == 'n') {
                return null;
            }
            String[] nodes = data.split(",");
            TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            for (int i = 1; i < nodes.length; i += 2) {
                TreeNode parent = queue.poll();
                if (!nodes[i].equals("n")) {
                    parent.left = new TreeNode(Integer.parseInt(nodes[i]));
                    queue.offer(parent.left);
                }
                if (!nodes[i + 1].equals("n")) {
                    parent.right = new TreeNode(Integer.parseInt(nodes[i + 1]));
                    queue.offer(parent.right);
                }
            }
            return root;
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

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
}
