package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wei on 10/20/2019.
 * #0662 https://leetcode.com/problems/maximum-width-of-binary-tree/
 */
public class MaximumWidthOfBinaryTree {
	// time O(n)
	public int widthOfBinaryTree(TreeNode root) {
		return dfs(root, 0, 1, new ArrayList<Integer>(), new ArrayList<Integer>());
	}

	private int dfs(TreeNode root, int level, int index, List<Integer> start, List<Integer> end){
		if(root == null){
			return 0;
		}
		// add the first element of the level
		if(start.size() == level){
			start.add(index);
			end.add(index);
		}else{
			// keep updating until reaching the last element of the level
			end.set(level, index);
		}
		int cur = end.get(level) - start.get(level) + 1;
		int left = dfs(root.left, level + 1, 2 * index, start, end);
		int right = dfs(root.right, level + 1, 2 * index + 1, start, end);
		return Math.max(cur, Math.max(left, right));
	}

	// time O(n), space O(n)
	public int widthOfBinaryTreeBFS(TreeNode root) {
		if(root == null){
			return 0;
		}
		int width = 1;
		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.offerLast(root);
		while(!queue.isEmpty()){
			while(!queue.isEmpty() && queue.peekFirst() == null){
				queue.pollFirst();
			}
			while(!queue.isEmpty() && queue.peekLast() == null){
				queue.pollLast();
			}
			int size = queue.size();
			width = Math.max(size, width);
			while(size > 0){
				TreeNode curr = queue.pollFirst();
				queue.offerLast(curr == null ? null : curr.left);
				queue.offerLast(curr == null ? null : curr.right);
				size--;
			}
		}
		return width;
	}
}
