package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Wei on 11/07/2023.
 * #2196 https://leetcode.com/problems/create-binary-tree-from-descriptions/
 */
public class CreateBinaryTreeFromDescriptions {

	// time O(n), space O(n)
	public TreeNode createBinaryTree(int[][] descriptions) {
		Map<Integer, TreeNode> map = new HashMap<>();
		Set<Integer> children = new HashSet<>();
		for (int[] d : descriptions) {
			map.putIfAbsent(d[0], new TreeNode(d[0]));
			map.putIfAbsent(d[1], new TreeNode(d[1]));
			TreeNode parent = map.get(d[0]), child = map.get(d[1]);
			if (d[2] == 1) {
				parent.left = child;
			} else {
				parent.right = child;
			}
			children.add(d[1]);
		}
		for (int[] d : descriptions) {
			if (!children.contains(d[0])) {
				return map.get(d[0]);
			}
		}
		return null;
	}
}
