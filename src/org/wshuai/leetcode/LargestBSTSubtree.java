package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 2/20/17.
 * #333 https://leetcode.com/problems/largest-bst-subtree/
 */
public class LargestBSTSubtree {
	//Recursive
	public int largestBSTSubtree(TreeNode root) {
		Map<TreeNode, int[]> map = new HashMap<TreeNode, int[]>();
		RefType rf = new RefType();
		largestBSTSubtreeUtil(root, map, rf);
		return rf.val;
	}

	private void largestBSTSubtreeUtil(TreeNode root, Map<TreeNode, int[]> map, RefType rf) {
		if (root == null) {
			return;
		}
		largestBSTSubtreeUtil(root.left, map, rf);
		largestBSTSubtreeUtil(root.right, map, rf);
		int[] arr = new int[3];
		if (root.left == null && root.right == null) {
			arr[0] = 1;
			arr[1] = root.val;
			arr[2] = root.val;
			map.put(root, arr);
			rf.val = rf.val < 1 ? 1 : rf.val;
		} else if (root.left == null) {
			if (map.containsKey(root.right)) {
				int[] arr1 = map.get(root.right);
				if (arr1[1] > root.val) {
					arr[0] = 1 + arr1[0];
					arr[1] = root.val;
					arr[2] = arr1[2];
					map.put(root, arr);
					rf.val = rf.val < arr[0] ? arr[0] : rf.val;
				}
			}
		} else if (root.right == null) {
			if (map.containsKey(root.left)) {
				int[] arr1 = map.get(root.left);
				if (arr1[2] < root.val) {
					arr[0] = 1 + arr1[0];
					arr[1] = arr1[1];
					arr[2] = root.val;
					map.put(root, arr);
					rf.val = rf.val < arr[0] ? arr[0] : rf.val;
				}
			}
		} else if (map.containsKey(root.left) && map.containsKey(root.right)) {
			int[] arr1 = map.get(root.left);
			int[] arr2 = map.get(root.right);
			if (root.val > arr1[2] && root.val < arr2[1]) {
				arr[0] = 1 + arr1[0] + arr2[0];
				arr[1] = arr1[1];
				arr[2] = arr2[2];
				map.put(root, arr);
				rf.val = rf.val < arr[0] ? arr[0] : rf.val;
			}
		}
	}
}
