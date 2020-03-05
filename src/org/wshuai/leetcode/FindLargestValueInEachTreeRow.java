package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wei on 02/20/2017.
 * #0515 https://leetcode.com/problems/find-largest-value-in-each-tree-row/
 */
public class FindLargestValueInEachTreeRow {
	// time O(n), space O(n)
	public List<Integer> largestValues(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if(root == null){
			return res;
		}
		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.offerLast(root);
		while(!queue.isEmpty()){
			int size = queue.size(), max = Integer.MIN_VALUE;
			while(size-- > 0){
				TreeNode cur = queue.pollFirst();
				max = Math.max(max, cur.val);
				if(cur.left != null){
					queue.offerLast(cur.left);
				}
				if(cur.right != null){
					queue.offerLast(cur.right);
				}
			}
			res.add(max);
		}
		return res;
	}
}
