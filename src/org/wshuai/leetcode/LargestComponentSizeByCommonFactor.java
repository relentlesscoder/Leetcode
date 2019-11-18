package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/18/19.
 * #952 https://leetcode.com/problems/largest-component-size-by-common-factor/
 */
public class LargestComponentSizeByCommonFactor {
	private int[] root;
	private int[] rank;

	public int largestComponentSize(int[] A) {
		int N = A.length;
		Map<Integer, Integer> map = new HashMap<>();
		root = new int[N];
		rank = new int[N];
		for(int i = 0; i < N; i++){
			root[i] = i;
			rank[i] = 1;
		}
		for(int i = 0; i < N; i++){
			int a = A[i];
			for (int j = 2; j * j <= a; j++){
				if(a % j == 0){
					if(!map.containsKey(j)){
						map.put(j, i);
					}else{
						union(i, map.get(j));
					}
					if(!map.containsKey(a / j)){
						map.put(a / j, i);
					}else{
						union(i, map.get(a / j));
					}
				}
			}
			if(!map.containsKey(a)){
				map.put(a, i);
			}else{
				union(i, map.get(a));
			}
		}
		int max = 0;
		for(int i = 0; i < N; i++){
			if(root[i] == i){
				max = Math.max(rank[i], max);
			}
		}
		return max;
	}

	private int find(int i, int[] root){
		if(i != root[i]){
			root[i] = find(root[i], root);
		}
		return root[i];
	}

	private void union(int i, int j){
		int r1 = find(i, root);
		int r2 = find(j, root);
		if(r1 == r2){
			return;
		}
		if(rank[r1] > rank[r2]){
			root[r2] = r1;
			rank[r1] += rank[r2];
		}else{
			root[r1] = r2;
			rank[r2] += rank[r1];
		}
	}
}
