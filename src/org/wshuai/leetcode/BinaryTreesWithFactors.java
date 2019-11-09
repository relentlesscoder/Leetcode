package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/9/2019.
 * #823 https://leetcode.com/problems/binary-trees-with-factors/
 */
public class BinaryTreesWithFactors {
	private int MOD = 1_000_000_007;
	private Map<Integer, List<int[]>> map;
	private Map<Integer, Long> count;

	public int numFactoredBinaryTrees(int[] A) {
		map = new HashMap<>();
		count = new HashMap<>();
		Arrays.sort(A);
		for(int i = A.length - 1; i >= 0; i--){
			int left = 0;
			int right = i - 1;
			while(left <= right){
				long prod = A[left] * A[right];
				if(prod == A[i]){
					map.putIfAbsent(A[i], new ArrayList<>());
					map.get(A[i]).add(new int[]{A[left], A[right]});
					if(left != right){
						map.get(A[i]).add(new int[]{A[right], A[left]});
					}
					left++;
					right--;
				}else if(prod > A[i]){
					right--;
				}else{
					left++;
				}
			}
		}
		long res = 0;
		for(int i = 0; i < A.length; i++){
			res = (res + dfs(A[i])) % MOD;
		}
		return (int)res;
	}

	private long dfs(int val){
		if(!count.containsKey(val)){
			long curr = 1;
			if(map.containsKey(val)){
				List<int[]> pairs = map.get(val);
				for(int[] p : pairs){
					long left = dfs(p[0]);
					long right = dfs(p[1]);
					curr = (curr + (left * right));
				}
			}
			count.put(val, curr);
		}
		return count.get(val);
	}
}
