package org.wshuai.leetcode;

/**
 * Created by Wei on 10/08/2023.
 * #2076 https://leetcode.com/problems/process-restricted-friend-requests/
 */
public class ProcessRestrictedFriendRequests {

	// time O(r1 * r2), space O(n)
	public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
		boolean[] res = new boolean[requests.length];
		UnionFind uf = new UnionFind(n);
		for (int i = 0; i < requests.length; i++) {
			int p1 = uf.find(requests[i][0]), p2 = uf.find(requests[i][1]); // find roots for the request
			boolean restricted = false;
			for (int[] re : restrictions) {
				int r1 = uf.find(re[0]), r2 = uf.find(re[1]); // find roots for the restriction
				if ((r1 == p1 && r2 == p2) || (r1 == p2 && r2 == p1)) {
					restricted = true;
					break;
				}
			}
			if (!restricted) {
				uf.union(p1, p2);
				res[i] = true;
			}
		}
		return res;
	}

	private class UnionFind {

		private int[] root;
		private int[] rank;

		public UnionFind(int n) {
			this.root = new int[n];
			this.rank = new int[n];
			for (int i = 0; i < n; i++) {
				this.root[i] = i;
				this.rank[i] = 1;
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
			if (rank[rootX] > rank[rootY]) {
				rank[rootX] += rank[rootY];
				root[rootY] = rootX;
			} else {
				rank[rootY] += rank[rootX];
				root[rootX] = rootY;
			}
		}
	}
}
