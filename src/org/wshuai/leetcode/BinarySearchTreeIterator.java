package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Wei on 01/26/2016.
 * #0173 https://leetcode.com/problems/binary-search-tree-iterator/
 */
public class BinarySearchTreeIterator {

	private List<Integer> list;
	private Iterator<Integer> itr;

	public BinarySearchTreeIterator(TreeNode root) {
		inorderTraversal(root);
		itr = list.iterator();
	}

	/** @return the next smallest number */
	public int next() {
		return itr.next();
	}

	/** @return whether we have a next smallest number */
	public boolean hasNext() {
		return itr.hasNext();
	}

	// time O(n), space O(1)
	// morris traversal
	private void inorderTraversal(TreeNode root){
		list = new ArrayList<>();
		TreeNode cur = root;
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
					prev.right = null;
					list.add(cur.val);
					cur = cur.right;
				}
			}else{
				list.add(cur.val);
				cur = cur.right;
			}
		}
	}
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */
