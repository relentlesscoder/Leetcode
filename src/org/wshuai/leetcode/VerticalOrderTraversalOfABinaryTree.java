package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 09/23/2019.
 * #0987 https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
 */
public class VerticalOrderTraversalOfABinaryTree {

	// time O(n*log(n)), space O(n)
	public List<List<Integer>> verticalTraversal(TreeNode root) {
		TreeMap<Integer, PriorityQueue<int[]>> map = new TreeMap<>();
		List<List<Integer>> res = new ArrayList<>();
		dfs(root, 0, 0, map);
		for(Map.Entry<Integer, PriorityQueue<int[]>> entry : map.entrySet()){
			PriorityQueue<int[]> pq = entry.getValue();
			List<Integer> list = new ArrayList<>();
			while(!pq.isEmpty()){
				list.add(pq.poll()[1]);
			}
			res.add(list);
		}
		return res;
	}

	private void dfs(TreeNode root, int x, int y, TreeMap<Integer, PriorityQueue<int[]>> map){
		if(root == null){
			return;
		}
		map.putIfAbsent(x, new PriorityQueue<int[]>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]));
		map.get(x).offer(new int[]{y, root.val});
		dfs(root.left, x - 1, y + 1, map);
		dfs(root.right, x + 1, y + 1, map);
	}
}


