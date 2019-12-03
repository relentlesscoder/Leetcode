package org.wshuai.leetcode;

import java.util.ArrayList;

/**
 * Created by Wei on 12/3/19.
 * #431 https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree/
 */
public class EncodeNaryTreeToBinaryTree {
	// Next level first -> left, same level -> right
	// Encodes an n-ary tree to a binary tree.
	public TreeNode encode(NaryTreeNode root) {
		if(root == null){
			return null;
		}
		TreeNode result = new TreeNode(root.val);
		if(root.children.size() > 0){
			result.left = encode(root.children.get(0));
		}
		TreeNode cur = result.left;
		for(int i = 1; i < root.children.size(); i++){
			cur.right = encode(root.children.get(i));
			cur = cur.right;
		}
		return result;
	}

	// Decodes your binary tree to an n-ary tree.
	public NaryTreeNode decode(TreeNode root) {
		if(root == null){
			return null;
		}
		NaryTreeNode result = new NaryTreeNode(root.val, new ArrayList<NaryTreeNode>());
		TreeNode cur = root.left;
		while(cur != null){
			result.children.add(decode(cur));
			cur = cur.right;
		}
		return result;
	}
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(root));