package org.wshuai.leetcode;

/**
 * Created by Wei on 09/20/2016.
 * #0270 https://leetcode.com/problems/closest-binary-search-tree-value/
 */
public class ClosestBinarySearchTreeValue {
	// time O(n), space O(1)
	// Morris traversal
	public int closestValue(TreeNode root, double target) {
		TreeNode cur = root;
		int res = 0;
		double diff = Double.MAX_VALUE;
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
					double curDiff = Math.abs(target - cur.val);
					// return the current min when hit the first increasing node
					if(curDiff > diff){
						return res;
					}else{
						diff = curDiff;
						res = cur.val;
					}
					cur = cur.right;
				}
			}else{
				double curDiff = Math.abs(target - cur.val);
				if(curDiff > diff){
					return res;
				}else{
					diff = curDiff;
					res = cur.val;
				}
				cur = cur.right;
			}
		}
		return res;
	}
}
