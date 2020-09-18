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
		int[] range = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
		columnRange(root, range, 0);
		for(int i = range[0]; i <= range[1]; i++){
			res.add(new ArrayList<>());
		}
		LinkedList<TreeNode> nodes = new LinkedList<>();
		LinkedList<Integer> columns = new LinkedList<>();
		nodes.offerLast(root);
		columns.offerLast(-range[0]);
		while(!nodes.isEmpty()){
			TreeNode node = nodes.pollFirst();
			int col = columns.pollFirst();
			res.get(col).add(node.val);
			if(node.left != null){
				nodes.offerLast(node.left);
				columns.offerLast(col - 1);
			}
			if(node.right != null){
				nodes.offerLast(node.right);
				columns.offerLast(col + 1);
			}
		}
		return res;
	}

	private void columnRange(TreeNode root, int[] range, int cur){
		if(root == null){
			return;
		}
		range[0] = Math.min(range[0], cur);
		range[1] = Math.max(range[1], cur);
		columnRange(root.left, range, cur - 1);
		columnRange(root.right, range, cur + 1);
	}
}
