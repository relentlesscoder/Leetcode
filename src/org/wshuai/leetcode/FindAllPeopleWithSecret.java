package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/01/2023.
 * #2092 https://leetcode.com/problems/find-all-people-with-secret/
 */
public class FindAllPeopleWithSecret {

	// time O(n * log(n)), space O(n)
	public List<Integer> findAllPeopleBFS(int n, int[][] meetings, int firstPerson) {
		List<int[]>[] graph = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int[] m : meetings) {
			graph[m[0]].add(new int[]{m[1], m[2]});
			graph[m[1]].add(new int[]{m[0], m[2]});
		}
		boolean[] visited = new boolean[n];
		PriorityQueue<int[]> minQueue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		minQueue.offer(new int[]{0, 0});
		minQueue.offer(new int[]{firstPerson, 0});
		while (!minQueue.isEmpty()) {
			int[] curr = minQueue.poll();
			if (visited[curr[0]]) {
				continue;
			}
			visited[curr[0]] = true;
			for (int[] next : graph[curr[0]]) {
				if (!visited[next[0]] && curr[1] <= next[1]) {
					minQueue.offer(new int[]{next[0], next[1]});
				}
			}
		}
		List<Integer> res = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (visited[i]) {
				res.add(i);
			}
		}
		return res;
	}

	// time O(n * log(n)), space O(n)
	public List<Integer> findAllPeopleUnionFind(int n, int[][] meetings, int firstPerson) {
		Arrays.sort(meetings, (a, b) -> a[2] - b[2]);
		UnionFind uf = new UnionFind(n);
		uf.union(0, firstPerson);
		int currentTime = 0;
		Set<Integer> people = new HashSet<>();
		for (int i = 0; i < meetings.length; i++) {
			if (meetings[i][2] != currentTime) {
				for (int p : people) {
					// reset node that with no secret after each timestamp so the nodes will not affect future union results
					// for example:
					// time 0 -> [0, 1]
					// time 1 -> [1, 2], [3, 4]
					// time 2 -> [2, 3]
					// if we don't reset 4 after time 1, then 4 will be incorrectly added to the secret list after time 2
					if (uf.find(p) != uf.find(0)) {
						uf.reset(p);
					}
				}
				currentTime = meetings[i][2];
				people = new HashSet<>();
			}
			uf.union(meetings[i][0], meetings[i][1]);
			people.add(meetings[i][0]);
			people.add(meetings[i][1]);
		}
		return uf.findAllPeopleWithSecret();
	}

	private class UnionFind {

		private int[] root, rank;

		public UnionFind(int n) {
			root = new int[n];
			rank = new int[n];
			for (int i = 0; i < n; i++) {
				root[i] = i;
				rank[i] = 1;
			}
		}

		public int find(int x) {
			if (root[x] != x) {
				root[x] = find(root[x]);
			}
			return root[x];
		}

		public void union(int x, int y) {
			int rootX = find(x), rootY = find(y);
			if (rootX == rootY) {
				return;
			}
			if (rank[rootX] >= rank[rootY]) {
				root[rootY] = rootX;
				rank[rootX] += rank[rootY];
			} else {
				root[rootX] = rootY;
				rank[rootY] += rank[rootX];
			}
		}

		public void reset(int x) {
			root[x] = x;
			rank[x] = 1;
		}

		public List<Integer> findAllPeopleWithSecret() {
			int r = find(0);
			List<Integer> res = new ArrayList<>();
			for (int i = 0; i < root.length; i++) {
				if (find(i) == r) {
					res.add(i);
				}
			}
			return res;
		}
	}
}
