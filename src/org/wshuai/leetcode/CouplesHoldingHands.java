package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/14/2019.
 * #765 https://leetcode.com/problems/couples-holding-hands/
 */
public class CouplesHoldingHands {
	public int minSwapsCouples(int[] row) {
		int N = row.length;
		int M = N >> 1;
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < N; i++){
			map.put(row[i], i >> 1);
		}
		int[] root = new int[M];
		int[] rank = new int[M];
		for(int i = 0; i < M; i++){
			root[i] = i;
			rank[i] = 1;
		}
		int res = 0;
		for(int i = 0; i < N; i++){
			int j = i % 2 == 0 ? i + 1 : i - 1;
			int c1 = map.get(i);
			int c2 = map.get(j);
			if(c1 == c2){
				continue;
			}
			int r1 = find(c1, root);
			int r2 = find(c2, root);
			if(r1 != r2){
				res++;
			}
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
