package org.wshuai.leetcode;

/**
 * Created by Wei on 11/14/2019.
 * #685 https://leetcode.com/problems/redundant-connection-ii/
 */
public class RedundantConnectionII {
	// great explanation https://leetcode.com/problems/redundant-connection-ii/discuss/278105/topic
	public int[] findRedundantDirectedConnection(int[][] edges) {
		int N = edges.length;
		int[] root = new int[N + 1];
		int[] parent = new int[N + 1];
		int[] edge1 = null;
		int[] edge2 = null;
		boolean cycleDetected = false;
		for(int[] edge : edges){
			int u = edge[0];
			int v = edge[1];
			if(root[u] == 0){
				root[u] = u;
			}
			if(root[v] == 0){
				root[v] = v;
			}
			if(parent[v] > 0){
				edge1 = new int[]{parent[v], v};
				edge2 = new int[]{u, v};
			}else{
				parent[v] = u;
				int r1 = find(u, root);
				int r2 = find(v, root);
				if(r1 == r2){
					cycleDetected = true;
					if(edge2 == null){
						edge2 = new int[]{u, v};
					}
				}else{
					root[r2] = r1;
				}
			}
		}
		if(edge1 != null && edge2 != null){
			return cycleDetected ? edge1 : edge2;
		}
		return edge2;
	}

	private int find(int u, int[] root){
		if(root[u] != u){
			root[u] = find(root[u], root);
		}
		return root[u];
	}
}
