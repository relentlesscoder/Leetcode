package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Wei on 12/30/2019.
 * #1305 https://leetcode.com/problems/all-elements-in-two-binary-search-trees/
 */
public class AllElementsInTwoBinarySearchTrees {
	public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
		List<Integer> list1 = inOrder(root1);
		List<Integer> list2 = inOrder(root2);
		return mergeSortedList(list1, list2);
	}

	private List<Integer> inOrder(TreeNode root){
		List<Integer> res = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		TreeNode cur = root;
		while(!stack.isEmpty() || cur != null){
			if(cur == null){
				TreeNode prev = stack.pop();
				res.add(prev.val);
				cur = prev.right;
			}else{
				stack.push(cur);
				cur = cur.left;
			}
		}
		return res;
	}

	private List<Integer> mergeSortedList(List<Integer> list1, List<Integer> list2){
		List<Integer> res = new ArrayList<>();
		int i = 0, j = 0;
		while(i < list1.size() || j < list2.size()){
			if(i == list1.size()){
				res.add(list2.get(j++));
				continue;
			}
			if(j == list2.size()){
				res.add(list1.get(i++));
				continue;
			}
			int val1 = list1.get(i);
			int val2 = list2.get(j);
			if(val1 < val2){
				res.add(list1.get(i++));
			}else{
				res.add(list2.get(j++));
			}
		}
		return res;
	}
}
