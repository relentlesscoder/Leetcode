package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Wei on 9/19/2016.
 */
public class BinaryTreeLevelOrderTraversalII {
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> lst = new ArrayList<List<Integer>>();
		if (root == null) {
			return lst;
		}
		List<List<Integer>> aux = new ArrayList<List<Integer>>();
		List<Integer> ls = new ArrayList<Integer>();
		Queue<TreeNode> last = new LinkedList<TreeNode>();
		last.offer(root);
		Queue<TreeNode> curr = new LinkedList<TreeNode>();
		while (!last.isEmpty()) {
			TreeNode node = last.poll();
			ls.add(node.val);
			if (node.left != null) {
				curr.offer(node.left);
			}
			if (node.right != null) {
				curr.offer(node.right);
			}
			if (last.isEmpty()) {
				aux.add(ls);
				ls = new ArrayList<Integer>();
				last = curr;
				curr = new LinkedList<TreeNode>();
			}
		}
		int len = aux.size();
		for (int i = len - 1; i >= 0; i--) {
			lst.add(aux.get(i));
		}

		return lst;
	}
}
