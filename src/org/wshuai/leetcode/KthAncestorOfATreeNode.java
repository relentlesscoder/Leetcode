package org.wshuai.leetcode;

/**
 * Created by Wei on 10/01/2020.
 * #1483 https://leetcode.com/problems/kth-ancestor-of-a-tree-node/
 */
public class KthAncestorOfATreeNode {

	private int[][] ancestors;
	private int size;

	// time O(n*log(n)), space O(n*log(n))
	// https://leetcode.com/problems/kth-ancestor-of-a-tree-node/discuss/686268/Explanation-with-c%2B%2B-sample-code
	public KthAncestorOfATreeNode(int n, int[] parent) {
		size = (int)Math.ceil(Math.log(n)/Math.log(2)) + 1;
		ancestors = new int[size][n];
		ancestors[0] = parent;
		for(int i = 1; i < size; i++){
			for(int j = 0; j < n; j++){
				int node = ancestors[i - 1][j];
				ancestors[i][j] = node == -1 ? -1 : ancestors[i - 1][node];
			}
		}
	}

	// time O(log(k))
	public int getKthAncestor(int node, int k) {
		for(int i = 0; i < size; i++){
			if((k & (1 << i)) != 0){
				node = ancestors[i][node];
				if(node == -1){
					return -1;
				}
			}
		}
		return node;
	}
}
