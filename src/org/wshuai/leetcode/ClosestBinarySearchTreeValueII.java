package org.wshuai.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wei on 07/06/2017.
 * #0272 https://leetcode.com/problems/closest-binary-search-tree-value-ii/
 */
public class ClosestBinarySearchTreeValueII {
	// time O(n), space O(n)
	// morris traversal
	public List<Integer> closestKValues(TreeNode root, double target, int k) {
		LinkedList<Integer> res = new LinkedList<>();
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
					double diff = Math.abs(target - cur.val);
					if(res.size() < k){
						res.offerLast(cur.val);
					}else if(diff < Math.abs(res.peekFirst() - target)){
						res.pollFirst();
						res.offerLast(cur.val);
					}else{
						break;
					}
					cur = cur.right;
				}
			}else{
				double diff = Math.abs(target - cur.val);
				if(res.size() < k){
					res.offerLast(cur.val);
				}else if(diff < Math.abs(res.peekFirst() - target)){
					res.pollFirst();
					res.offerLast(cur.val);
				}else{
					break;
				}
				cur = cur.right;
			}
		}
		return res;
	}
}
