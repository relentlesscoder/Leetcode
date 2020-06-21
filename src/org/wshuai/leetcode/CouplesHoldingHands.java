package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/14/2019.
 * #0765 https://leetcode.com/problems/couples-holding-hands/
 */
public class CouplesHoldingHands {
	// time O(n*log(n)), space O(n)
	public int minSwapsCouples(int[] row) {
		int res = 0, m = row.length, n = m >> 1;
		int[] seats = new int[n];
		List<int[]> edges = new ArrayList<>();
		Arrays.fill(seats, -1);
		for(int i = 0; i < m; i++){
			int s = (row[i] >> 1);
			if(seats[s] >= 0){
				edges.add(new int[]{i >> 1, seats[s]});
			}else{
				seats[s] = (i >> 1);
			}
		}
		int[] root = new int[n], rank = new int[n];
		for(int i = 0; i < n; i++){
			root[i] = i;
			rank[i] = 1;
		}
		// for each cyclic swap sets with k nodes, we need k - 1 swap
		
		// for instance [0, 2, 1, 4, 5, 3]
		// we have cyclic swap set 0->1, 0->2, 1->2 and 2 swap needed
		for(int[] e : edges){
			int ru = findRoot(e[0], root), rv = findRoot(e[1], root);
			if(ru == rv){
				continue;
			}
			res++;
			if(rank[ru] > rank[rv]){
				rank[ru] += rank[rv];
				root[rv] = ru;
			}else{
				rank[rv] += rank[ru];
				root[ru] = rv;
			}
		}
		return res;
	}

	private int findRoot(int r, int[] root){
		if(r != root[r]){
			root[r] = findRoot(root[r], root);
		}
		return root[r];
	}
}
