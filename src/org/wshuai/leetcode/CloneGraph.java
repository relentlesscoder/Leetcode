package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/10/2016.
 * #0133 https://leetcode.com/problems/clone-graph/
 */
public class CloneGraph {

	// time O(n)
	public Node cloneGraph(Node node) {
		if(node == null){
			return null;
		}
		Map<Node, Node> map = new HashMap<>();
		dfs(node, map);
		return map.get(node);
	}

	private void dfs(Node node, Map<Node, Node> map){
		map.put(node, new Node(node.val));
		for(Node next : node.neighbors){
			if(!map.containsKey(next)){ // next has not been visited
				dfs(next, map);
			}
			map.get(node).neighbors.add(map.get(next));
		}
	}

	// time O(n), space O(n)
	public Node cloneGraphBFS(Node node) {
		if(node == null){
			return null;
		}
		Map<Node, Node> mapping = new HashMap<>();
		LinkedList<Node> queue = new LinkedList<>();
		queue.offerLast(node);
		mapping.put(node, new Node(node.val));
		while(!queue.isEmpty()){
			Node cur = queue.pollFirst();
			for(Node next : cur.neighbors){
				if(!mapping.containsKey(next)){
					Node copy = new Node(next.val);
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

	private class Node {
		int val;

		List<Node> neighbors;

		Node(int x) {
			val = x;
			neighbors = new ArrayList<Node>();
		}
	}
}

