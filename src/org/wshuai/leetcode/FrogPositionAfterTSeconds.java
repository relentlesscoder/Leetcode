package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 03/09/2020.
 * #1377 https://leetcode.com/problems/frog-position-after-t-seconds/
 */
public class FrogPositionAfterTSeconds {

	// time O(n), space O(n)
	public double frogPosition(int n, int[][] edges, int t, int target) {
		int time = 0;
		Map<Integer, List<Integer>> adj = new HashMap<>();
		for (int[] e : edges) {
			adj.putIfAbsent(e[0], new ArrayList<>());
			adj.putIfAbsent(e[1], new ArrayList<>());
			adj.get(e[0]).add(e[1]);
			adj.get(e[1]).add(e[0]);
		}
		boolean[] visited = new boolean[n + 1];
		LinkedList<NodeState> queue = new LinkedList<>();
		queue.offerLast(new NodeState(1, 1.0));
		visited[1] = true;
		while (!queue.isEmpty() && time <= t) {
			int size = queue.size();
			while (size-- > 0) {
				NodeState cur = queue.pollFirst();
				if (time == t && cur.id == target) {
					return cur.prob;
				}
				int count = 0;
				List<Integer> temp = new ArrayList<>();
				if (adj.containsKey(cur.id)) {
					List<Integer> child = adj.get(cur.id);
					for (int next : child) {
						if (visited[next]) {
							continue;
						}
						count++;
						visited[next] = true;
						temp.add(next);
					}
				}
				if (count == 0) {
					// if cannot move, stay at the same node with 100% probability
					queue.offerLast(cur);
				} else {
					double prob = cur.prob / count;
					for (int next : temp) {
						queue.offerLast(new NodeState(next, prob));
					}
				}
			}
			time++;
		}
		return 0;
	}

	private class NodeState {
		int id;
		double prob;

		public NodeState(int id, double prob) {
			this.id = id;
			this.prob = prob;
		}
	}
}
