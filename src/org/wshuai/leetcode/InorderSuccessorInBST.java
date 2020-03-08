package org.wshuai.leetcode;

/**
 * Created by Wei on 11/21/2016.
 * #0285 https://leetcode.com/problems/inorder-successor-in-bst/
 */
public class InorderSuccessorInBST {
	// time O(n)
	// https://leetcode.com/problems/inorder-successor-in-bst/discuss/72653/Share-my-Java-recursive-solution
	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
		if(root == null){
			return null;
		}
		if(root.val <= p.val){
			return inorderSuccessor(root.right, p);
		}else{
			TreeNode left = inorderSuccessor(root.left, p);
			return (left != null) ? left : root;
		}
	}

	// time O(n), space O(1)
	public TreeNode inorderSuccessorMorris(TreeNode root, TreeNode p) {
		TreeNode cur = root;
		boolean seen = false;
		while(cur != null){
			if(cur.left != null){
				TreeNode prev = cur.left;
				while(prev.right != null && prev.right != cur){
					prev = prev.right;
				}
				if(prev.right == null){
					prev.right = cur;
					cur = cur.left;
				}else{
					if(seen){
						return cur;
					}
					if(cur == p){
						seen = true;
					}
					prev.right = null;
					cur = cur.right;
				}
			}else{
				if(seen){
					return cur;
				}
				if(cur == p){
					seen = true;
				}
				cur = cur.right;
			}
		}
		return null;
	}
}
