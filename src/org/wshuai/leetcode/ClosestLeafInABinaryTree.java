package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/15/2019.
 * #742 https://leetcode.com/problems/closest-leaf-in-a-binary-tree/
 */
public class ClosestLeafInABinaryTree {
	private Map<TreeNode, Integer> map;
	private int min;
	private int res;

	public int findClosestLeaf(TreeNode root, int k) {
		res = -1;
		map = new HashMap<>();
		min = Integer.MAX_VALUE;
		findNode(root, k);
		findLeaf(root, -1);
		return res;
	}

	private void findNode(TreeNode node, int k){
		if(node == null){
			return;
		}
		if(node.val == k){
			map.put(node, 0);
			return;
		}

		findNode(node.left, k);
		findNode(node.right, k);

		if(map.containsKey(node.left)){
			map.put(node, map.get(node.left) + 1);
		}else if(map.containsKey(node.right)){
			map.put(node, map.get(node.right) + 1);
		}
	}

	private void findLeaf(TreeNode node, int curr){
		int d = map.containsKey(node) ? map.get(node) : curr + 1;
		if(node.left == null && node.right == null){
			if(d < min){
				min = d;
				res = node.val;
			}
			return;
		}
		if(node.left != null){
			findLeaf(node.left, d);
		}
		if(node.right != null){
			findLeaf(node.right, d);
		}
	}
}
