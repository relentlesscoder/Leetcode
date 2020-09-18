package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/10/2016.
 * #0133 https://leetcode.com/problems/clone-graph/
 */
public class CloneGraph {

	// time O(n)
	public UndirectedGraphNode cloneGraphDFS(UndirectedGraphNode node) {
		if(node == null){
			return null;
		}
		Map<UndirectedGraphNode, UndirectedGraphNode> mapping = new HashMap<>();
		mapping.put(node, new UndirectedGraphNode(node.val));
		dfs(node, mapping);
		return mapping.get(node);
	}

	private void dfs(UndirectedGraphNode node, Map<UndirectedGraphNode, UndirectedGraphNode> mapping){
		for(UndirectedGraphNode next : node.neighbors){
			if(mapping.containsKey(next)){
				mapping.get(node).neighbors.add(mapping.get(next));
			}else{
				mapping.put(next, new UndirectedGraphNode(next.val));
				mapping.get(node).neighbors.add(mapping.get(next));
				dfs(next, mapping);
			}
		}
	}

	// time O(n), space O(n)
	public UndirectedGraphNode cloneGraphBFS(UndirectedGraphNode node) {
		if(node == null){
			return null;
		}
		Map<UndirectedGraphNode, UndirectedGraphNode> mapping = new HashMap<>();
		LinkedList<UndirectedGraphNode> queue = new LinkedList<>();
		queue.offerLast(node);
		mapping.put(node, new UndirectedGraphNode(node.val));
		while(!queue.isEmpty()){
			UndirectedGraphNode cur = queue.pollFirst();
			for(UndirectedGraphNode next : cur.neighbors){
				if(!mapping.containsKey(next)){
					UndirectedGraphNode copy = new UndirectedGraphNode(next.val);
					mapping.put(next, copy);
					mapping.get(cur).neighbors.add(copy);
					queue.offerLast(next);
				}else{
					mapping.get(cur).neighbors.add(mapping.get(next));
				}
			}
		}
		return mapping.get(node);
	}

	private class UndirectedGraphNode {
		int val;

		List<UndirectedGraphNode> neighbors;

		UndirectedGraphNode(int x) {
			val = x;
			neighbors = new ArrayList<UndirectedGraphNode>();
		}
	}
}

