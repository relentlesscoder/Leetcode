package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 01/31/2016.
 * #0109 https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
 */
public class ConvertSortedListToBinarySearchTree {
	// time O(n)
	public TreeNode sortedListToBST(LinkedListNode head) {
		List<Integer> list = new ArrayList<>();
		while(head != null){
			list.add(head.val);
			head = head.next;
		}
		if(list.size() == 0){
			return null;
		}
		return dfs(list, 0, list.size() - 1);
	}

	private TreeNode dfs(List<Integer> list, int i, int j){
		if(i > j){
			return null;
		}
		if(i == j){
			return new TreeNode(list.get(i));
		}
		int mid = i + (j - i) / 2;
		TreeNode root = new TreeNode(list.get(mid));
		root.left = dfs(list, i, mid - 1);
		root.right = dfs(list, mid + 1, j);
		return root;
	}
}
