package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wei on 07/19/2017.
 * #0637 https://leetcode.com/problems/average-of-levels-in-binary-tree/
 */
public class AverageOfLevelsInBinaryTree {

	// time O(n), space O(n)
	public List<Double> averageOfLevels(TreeNode root) {
		List<Double> res = new ArrayList<>();
		if(root == null){
			return res;
		}
		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.offerLast(root);
		while(!queue.isEmpty()){
			int size = queue.size(), count = size;
			double sum = 0.0;
			while(count-- > 0){
				TreeNode cur = queue.pollFirst();
				sum += cur.val;
				if(cur.left != null){
					queue.offerLast(cur.left);
				}
				if(cur.right != null){
					queue.offerLast(cur.right);
				}
			}
			res.add(sum / size);
		}
		return res;
	}
}
