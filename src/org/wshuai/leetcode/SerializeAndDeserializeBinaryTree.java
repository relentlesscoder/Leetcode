package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 10/02/2016.
 * #0297 https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 */
public class SerializeAndDeserializeBinaryTree {
	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		if (root == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.offerLast(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			TreeNode node = queue.pollFirst();
			if (node != null) {
				sb.append(Integer.toString(node.val) + ",");
				queue.offer(node.left);
				queue.offer(node.right);
			} else {
				sb.append("n,");
			}
		}
		return sb.substring(0, sb.length() - 1);
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		if (data == null || data.isEmpty()) {
			return null;
		}
		String[] vals = data.split(",");
		int len = vals.length;
		LinkedList<TreeNode> queue = new LinkedList<>();
		TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
		queue.offer(root);
		int i = 1;
		while (i < len && !queue.isEmpty()) {
			TreeNode parent = queue.poll();
			String left = vals[i], right = vals[i + 1];
			if (!left.equals("n")) {
				TreeNode LeftNode = new TreeNode(Integer.parseInt(left));
				parent.left = LeftNode;
				queue.offer(LeftNode);
			}
			if (!right.equals("n")) {
				TreeNode rightNode = new TreeNode(Integer.parseInt(right));
				parent.right = rightNode;
				queue.offer(rightNode);
			}
			i += 2;
		}
		return root;
	}
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
