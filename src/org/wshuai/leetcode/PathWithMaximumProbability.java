package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Wei on 08/03/2020.
 * #1514 https://leetcode.com/problems/path-with-maximum-probability/
 */
public class PathWithMaximumProbability {

	// time O(E * log(V)), space O(E + V)
	public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
		List<NodeProbability>[] graph = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < edges.length; i++) {
			graph[edges[i][0]].add(new NodeProbability(edges[i][1], succProb[i]));
			graph[edges[i][1]].add(new NodeProbability(edges[i][0], succProb[i]));
		}
		double[] probs = new double[n];
		probs[start_node] = 1.0;
		PriorityQueue<NodeProbability> maxQueue = new PriorityQueue<>((a, b) -> Double.compare(b.getProbability(), a.getProbability()));
		maxQueue.offer(new NodeProbability(start_node, 1.0));
		while (!maxQueue.isEmpty()) {
			NodeProbability curr = maxQueue.poll();
			if (curr.getNode() == end_node) {
				return curr.getProbability();
			}
			for (NodeProbability next : graph[curr.getNode()]) {
				double currProb = next.getProbability() * curr.getProbability();
				if (probs[next.getNode()] < currProb) {
					probs[next.getNode()] = currProb;
					maxQueue.offer(new NodeProbability(next.getNode(), currProb));
				}
			}
		}
		return probs[end_node];
	}

	private class NodeProbability {

		private int node;

		private double probability;

		public NodeProbability(int node, double probability) {
			this.node = node;
			this.probability = probability;
		}

		public int getNode() {
			return this.node;
		}

		public double getProbability() {
			return this.probability;
		}

	}
}
