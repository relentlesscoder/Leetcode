package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 11/8/19.
 * #851 https://leetcode.com/problems/loud-and-rich/
 */
public class LoudAndRich {
	public int[] loudAndRich(int[][] richer, int[] quiet) {
		int N = quiet.length;
		int[] ind = new int[N];
		int[] res = new int[N];
		boolean[][] M = new boolean[N][N];
		for(int i = 0; i < N; i++){
			res[i] = i;
		}
		for(int[] r: richer){
			M[r[0]][r[1]] = true;
			ind[r[1]]++;
		}
		LinkedList<Integer> queue = new LinkedList<>();
		for(int i = 0; i < N; i++){
			if(ind[i] == 0){
				queue.offerLast(i);
			}
		}
		while(!queue.isEmpty()){
			int curr = queue.pollFirst();
			for(int i = 0; i < N; i++){
				if(M[curr][i]){
					if(quiet[res[curr]] < quiet[res[i]]){
						res[i] = res[curr];
					}
					ind[i]--;
					if(ind[i] == 0){
						queue.offerLast(i);
					}
				}
			}
		}
		return res;
	}
}
