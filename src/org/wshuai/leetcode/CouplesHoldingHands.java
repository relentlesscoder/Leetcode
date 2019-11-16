package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/14/2019.
 * #765 https://leetcode.com/problems/couples-holding-hands/
 */
public class CouplesHoldingHands {
	public int minSwapsCouples(int[] row) {
		int N = row.length;
		int M = N >> 1;
		List<int[]> edges = new ArrayList<>();
		int[] couches = new int[M];
		Arrays.fill(couches, -1);
		for(int i = 0; i < N; i++){
			int v = row[i] >> 1;
			if(couches[v] >= 0){
				edges.add(new int[]{i >> 1, couches[v]});
			}else{
				couches[v] = (i >> 1);
			}
		}
		int[] root = new int[M];
		int[] rank = new int[M];
		for(int i = 0; i < M; i++){
			root[i] = i;
		}
		int res = 0;
		for(int i = 0; i < edges.size(); i++){
			int c1 = edges.get(i)[0];
			int c2 = edges.get(i)[1];
			if(c1 == c2){
				continue;
			}
			int r1 = find(c1, root);
			int r2 = find(c2, root);
			if(r1 == r2){
				continue;
			}
			res++;
			if(rank[r1] > rank[r2]){
				root[r2] = r1;
				rank[r1] += rank[r2];
			}else{
				root[r1] = r2;
				rank[r2] += rank[r1];
			}
		}
		return res;
	}

	private int find(int i, int[] root){
		if(i != root[i]){
			root[i] = find(root[i], root);
		}
		return root[i];
	}
}
