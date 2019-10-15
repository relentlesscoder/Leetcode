package org.wshuai.leetcode;

/**
 * Created by Wei on 10/15/2019.
 * #450 https://leetcode.com/problems/delete-node-in-a-bst/
 */
public class DeleteNodeInABST {

	// book CLRS p297
	public TreeNode deleteNode(TreeNode root, int key) {
		TreeNode R = new TreeNode(0);
		R.left = root;
		TreeNode parent = R;
		TreeNode curr = root;
		while(curr != null){
			if(curr.val == key){
				break;
			}
			parent = curr;
			if(curr.val > key){
				curr = curr.left;
			}else{
				curr = curr.right;
			}
		}
		if(curr != null){
			if(curr.left == null){
				replace(curr, curr.right, parent);
			}else if(curr.right == null){
				replace(curr, curr.left, parent);
			}else{
				TreeNode prev = null;
				TreeNode node = curr.right;
				while(node.left != null){
					prev = node;
					node = node.left;
				}
				curr.val = node.val;
				if(node == curr.right){
					curr.right = node.right;
				}else{
					replace(node, node.right, prev);
				}
			}
		}
		return R.left;
	}

	private void replace(TreeNode c, TreeNode r, TreeNode p){
		if(p.left == c){
			p.left = r;
		}else{
			p.right = r;
		}
	}
}
