package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/29/16.
 * #113 https://leetcode.com/problems/path-sum-ii/
 */
public class PathSumII {
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> lst = new ArrayList<List<Integer>>();
		pathSumUtil(lst, root, sum, 0, new ArrayList<Integer>());
		return lst;
	}

	private void pathSumUtil(List<List<Integer>> lst, TreeNode root, int sum, int csum, List<Integer> curr) {
		if (root == null) {
			return;
		}
		int nsum = csum + root.val;
		if (root.left == null && root.right == null) {
			if (sum == nsum) {
				List<Integer> ls = new ArrayList<Integer>(curr);
				ls.add(root.val);
				lst.add(ls);
			}
			return;
		}
		curr.add(root.val);
		pathSumUtil(lst, root.left, sum, nsum, curr);
		curr.remove(curr.size() - 1);

		curr.add(root.val);
		pathSumUtil(lst, root.right, sum, nsum, curr);
		curr.remove(curr.size() - 1);
	}
}
