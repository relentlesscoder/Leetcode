package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 12/26/2019.
 * #936 https://leetcode.com/problems/stamping-the-sequence/
 */
public class StampingTheSequence {
	public int[] movesToStamp(String stamp, String target) {
		char[] S = stamp.toCharArray();
		char[] T = target.toCharArray();
		int N = T.length;
		List<Integer> res = new ArrayList<>();
		boolean[] visited = new boolean[N];
		int stars = 0;

		while(stars < N){
			boolean doneReplace = false;
			for(int i = 0; i <= N - S.length; i++){
				if(!visited[i] && canReplace(T, S, i)){
					stars += doReplace(T, S.length, i);
					doneReplace = true;
					visited[i] = true;
					res.add(i);
					if(stars == N){
						break;
					}
				}
			}

			if(!doneReplace){
				return new int[0];
			}
		}

		int[] arr = new int[res.size()];
		int i = res.size() - 1;
		for(int a : res){
			arr[i--] = a;
		}
		return arr;
	}

	private boolean canReplace(char[] T, char[] S, int k){
		for(int i = 0; i < S.length; i++){
			if(T[i + k] != '*' && T[i + k] != S[i]){
				return false;
			}
		}
		return true;
	}

	private int doReplace(char[] T, int len, int k){
		int res = 0;
		for(int i = 0; i < len; i++){
			if(T[i + k] != '*'){
				T[i + k] = '*';
				res++;
			}
		}
		return res;
	}
}
