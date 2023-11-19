package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/22/2020.
 * #1627 https://leetcode.com/problems/graph-connectivity-with-threshold/
 */
public class GraphConnectivityWithThreshold {

	// time O(n*log(n)), space O(n)
	public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
		int[] root = new int[n + 1], rank = new int[n + 1];
		for(int i = 1; i <= n; i++){
			root[i] = i;
			rank[i] = 1;
		}
		for(int i = threshold + 1; i <= n; i++){
			for(int j = i * 2; j <= n; j += i){
				int r1 = find(i, root), r2 = find(j, root);
				if(r1 == r2){
					continue;
				}
				if(rank[r1] > rank[r2]){
					rank[r1] += rank[r2];
					root[r2] = r1;
				}else{
					rank[r2] += rank[r1];
					root[r1] = r2;
				}
			}
		}
		List<Boolean> res = new ArrayList<>();
		for(int[] q : queries){
			res.add(find(q[0], root) == find(q[1], root));
		}
		return res;
	}

	private int find(int r, int[] root){
		if(r != root[r]){
			root[r] = find(root[r], root);
		}
		return root[r];
	}
}
