package org.wshuai.leetcode;

/**
 * Created by Wei on 11/14/2019.
 * #0685 https://leetcode.com/problems/redundant-connection-ii/
 */
public class RedundantConnectionII {
	// https://leetcode.com/problems/redundant-connection-ii/discuss/278105/topic
	public int[] findRedundantDirectedConnection(int[][] edges) {
		int N = edges.length;
		int[] root = new int[N + 1], parent = new int[N + 1];
		int[] edge1 = null, edge2 = null;
		boolean cycleDetected = false;
		for(int i = 1; i <= N; i++){
			root[i] = i;
		}
		for (int[] edge : edges) {
			int u = edge[0], v = edge[1];
			if (parent[v] > 0) {
				// case 2 & case 3 will find one node has in-degree two
				// for case 3, we can skip any of the two edge
				// for case 2, if we skip the edge out of the cycle we
				// will still see cycle again in future so we need to remove edge1
				edge1 = new int[]{parent[v], v};
				edge2 = new int[]{u, v};
			} else {
				parent[v] = u;
				int r1 = find(u, root), r2 = find(v, root);
				if (r1 == r2) {
					cycleDetected = true;
					if (edge2 == null) {
						edge2 = new int[]{u, v};
					}
				} else {
					root[r2] = r1;
				}
			}
		}
		if (edge1 != null && edge2 != null) {
			return cycleDetected ? edge1 : edge2;
		}
		// for case 1, we always remove edge2
		return edge2;
	}

	private int find(int u, int[] root) {
		if (root[u] != u) {
			root[u] = find(root[u], root);
		}
		return root[u];
	}
}
