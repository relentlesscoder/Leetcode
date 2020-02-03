package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/15/2016.
 * #0314 https://leetcode.com/problems/binary-tree-vertical-order-traversal/
 */
public class BinaryTreeVerticalOrderTraversal {
	// time O(n)
	public List<List<Integer>> verticalOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if(root == null){
			return res;
		}
		int[] range = new int[2];
		getColumnRange(root, range, 0);
		for(int i = range[0]; i <= range[1]; i++){
			res.add(new ArrayList<>());
		}
		LinkedList<TreeNode> nodes = new LinkedList<>();
		LinkedList<Integer> columns = new LinkedList<>();
		nodes.offerLast(root);
		columns.offerLast(-range[0]);
		while(!nodes.isEmpty()){
			TreeNode cur = nodes.pollFirst();
			int col = columns.pollFirst();
			res.get(col).add(cur.val);
			if(cur.left != null){
				nodes.offerLast(cur.left);
				columns.offerLast(col - 1);
			}
			if(cur.right != null){
				nodes.offerLast(cur.right);
				columns.offerLast(col + 1);
			}
		}
		return res;
	}

	private void getColumnRange(TreeNode root, int[] range, int cur){
		if(root == null){
			return;
		}
		range[0] = Math.min(cur, range[0]);
		range[1] = Math.max(cur, range[1]);

		getColumnRange(root.left, range, cur - 1);
		getColumnRange(root.right, range, cur + 1);
	}
}
