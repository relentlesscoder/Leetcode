package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 10/02/2016.
 * #0297 https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 */
public class SerializeAndDeserializeBinaryTree {

	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		if(root == null){
			return "";
		}
		StringBuilder res = new StringBuilder();
		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.offerLast(root);
		while(!queue.isEmpty()){
			TreeNode cur = queue.pollFirst();
			if(cur != null){
				res.append(cur.val + ",");
				queue.offerLast(cur.left);
				queue.offerLast(cur.right);
			}else{
				res.append("n,");
			}
		}
		return res.substring(0, res.length() - 1);
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		if(data.length() == 0){
			return null;
		}
		String[] vals = data.split(",");
		TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.offerLast(root);
		for(int i = 1; i < vals.length; i += 2){
			TreeNode parent = queue.pollFirst();
			if(!vals[i].equals("n")){
				parent.left = new TreeNode(Integer.parseInt(vals[i]));
				queue.offerLast(parent.left);
			}
			if(!vals[i + 1].equals("n")){
				parent.right = new TreeNode(Integer.parseInt(vals[i + 1]));
				queue.offerLast(parent.right);
			}
		}
		return root;
	}
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
