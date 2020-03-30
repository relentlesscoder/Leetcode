package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 08/30/2019.
 * #0654 https://leetcode.com/problems/maximum-binary-tree/
 */
public class MaximumBinaryTree {
	// time O(n*log(n))
	public TreeNode constructMaximumBinaryTree(int[] nums) {
		TreeNode root = buildTree(nums, 0, nums.length - 1);
		return root;
	}

	private TreeNode buildTree(int[] nums, int l, int r) {
		if (l < 0 || r >= nums.length || l > r) {
			return null;
		}
		int idx = -1;
		int max = Integer.MIN_VALUE;
		for (int i = l; i <= r; i++) {
			if (nums[i] > max) {
				max = nums[i];
				idx = i;
			}
		}
		TreeNode node = new TreeNode(max);
		node.left = buildTree(nums, l, idx - 1);
		node.right = buildTree(nums, idx + 1, r);
		return node;
	}

	// time O(n), space O(n)
	public TreeNode constructMaximumBinaryTreeQueue(int[] nums) {
		LinkedList<TreeNode> queue = new LinkedList<>();
		for(int num : nums){
			TreeNode cur = new TreeNode(num);
			while(!queue.isEmpty() && queue.peekLast().val < num){
				cur.left = queue.pollLast();
			}
			if(!queue.isEmpty()){
				queue.peekLast().right = cur;
			}
			queue.offerLast(cur);
		}
		return queue.isEmpty() ? null : queue.pollFirst();
	}
}
