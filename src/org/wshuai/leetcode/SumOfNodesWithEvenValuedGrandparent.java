package org.wshuai.leetcode;

/**
 * Created by Wei on 01/12/2020.
 * #1315 https://leetcode.com/problems/sum-of-nodes-with-even-valued-grandparent/
 */
public class SumOfNodesWithEvenValuedGrandparent {
	// time O(n)
	public int sumEvenGrandparent(TreeNode root) {
		return dfs(root, null, null);
	}

	private int dfs(TreeNode node, TreeNode parent, TreeNode grandParent){
		if(node == null){
			return 0;
		}
		int sum = 0;
		if(grandParent != null && grandParent.val % 2 == 0){
			sum += node.val;
		}
		sum += dfs(node.left, node, parent);
		sum += dfs(node.right, node, parent);
		return sum;
	}
}
