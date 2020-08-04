package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Wei on 08/03/2020.
 * #1514 https://leetcode.com/problems/path-with-maximum-probability/
 */
public class PathWithMaximumProbability {

	public double maxProbabilityDFS(int n, int[][] edges, double[] succProb, int start, int end) {
		Map<Integer, Map<Integer, Double>> adj = new HashMap<>();
		for(int i = 0; i < edges.length; i++){
			int[] edge = edges[i];
			adj.putIfAbsent(edge[0], new HashMap<>());
			adj.putIfAbsent(edge[1], new HashMap<>());
			adj.get(edge[0]).put(edge[1], succProb[i]);
			adj.get(edge[1]).put(edge[0], succProb[i]);
		}
		double[] res = new double[n];

		/* DFS
		dfs(start, end, adj, res, 1.0);
		*/

		// BFS
		LinkedList<NodeProb> queue = new LinkedList<>();
		queue.offerLast(new NodeProb(start, 1.0));
		res[start] = 1.0;
		while(!queue.isEmpty()){
			NodeProb cur = queue.pollFirst();
			if(adj.containsKey(cur.node)){
				for(Map.Entry<Integer, Double> entry : adj.get(cur.node).entrySet()){
					int next = entry.getKey();
					double nextProb = entry.getValue();
					double newProb = cur.prob * nextProb;
					if(res[next] >= newProb){
						continue;
					}
					res[next] = newProb;
					queue.offerLast(new NodeProb(next, newProb));
				}
			}
		} //

		return res[end];
	}

	private void dfs(int cur, int end, Map<Integer, Map<Integer, Double>> adj, double[] res, double prob){
		if(prob <= 0.00005){
			return;
		}
		if(res[cur] >= prob){
			return;
		}
		res[cur] = prob;
		if(cur == end){
			return;
		}
		if(adj.containsKey(cur)){
			for(Map.Entry<Integer, Double> entry : adj.get(cur).entrySet()){
				int next = entry.getKey();
				double nextProb = entry.getValue();
				dfs(next, end, adj, res, prob * nextProb);
			}
		}
	}

	private class NodeProb{

		int node;

		double prob;

		public NodeProb(int node, double prob){
			this.node = node;
			this.prob = prob;
		}
	}
}
