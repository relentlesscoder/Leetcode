package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 11/21/16.
 * #1261 https://leetcode.com/problems/find-elements-in-a-contaminated-binary-tree/
 */
public class FindElementsInAContaminatedBinaryTree {
	private Set<Integer> map;

	public FindElementsInAContaminatedBinaryTree(TreeNode root) {
		map = new HashSet<>();
		setNode(root, 0);
	}

	public boolean find(int target) {
		return map.contains(target);
	}

	private void setNode(TreeNode curr, int val){
		if(curr == null){
			return;
		}
		curr.val = val;
		map.add(val);
		setNode(curr.left, (val << 1) + 1);
		setNode(curr.right, (val << 1) + 2);
	}
}

/**
 * Your FindElements object will be instantiated and called as such:
 * FindElements obj = new FindElements(root);
 * boolean param_1 = obj.find(target);
 */
