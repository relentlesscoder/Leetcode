package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Wei on 7/6/2017.
 * #272 https://leetcode.com/problems/closest-binary-search-tree-value-ii/
 */
public class ClosestBinarySearchTreeValueII {
	public List<Integer> closestKValues(TreeNode root, double target, int k) {
		List<Integer> result = new ArrayList<Integer>();
		if (root == null) {
			return result;
		}
		double min = Double.MAX_VALUE;
		int idx = 0;
		int pos = 0;
		List<Integer> temp = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode current = root;
		while (current != null || !stack.empty()) {
			if (current != null) {
				stack.push(current);
				current = current.left;
			} else {
				TreeNode parent = stack.pop();
				temp.add(parent.val);
				double diff = Math.abs(parent.val - target);
				if (diff < min) {
					min = diff;
					pos = idx;
				}
				idx++;
				current = parent.right;
			}
		}

		result.add(temp.get(pos));
		int left = pos - 1;
		int right = pos + 1;
		while (result.size() < k) {
			double leftDiff = Double.MAX_VALUE;
			double rightDiff = Double.MAX_VALUE;
			if (left >= 0) {
				leftDiff = Math.abs(temp.get(left) - target);
			}
			if (right < temp.size()) {
				rightDiff = Math.abs(temp.get(right) - target);
			}
			if (leftDiff < rightDiff) {
				result.add(temp.get(left));
				left--;
			} else {
				result.add(temp.get(right));
				right++;
			}
		}

		return result;
	}
}
