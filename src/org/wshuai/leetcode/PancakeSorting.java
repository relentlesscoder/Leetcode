package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 9/30/2019.
 * #969 https://leetcode.com/problems/pancake-sorting/
 */
public class PancakeSorting {
	public List<Integer> pancakeSort(int[] A) {
		List<Integer> res = new ArrayList<>();
		Integer[] B = new Integer[A.length];
		for(int i = 0; i < A.length; i++){
			B[i] = i;
		}
		Arrays.sort(B, (i, j) -> A[j] - A[i]);

		int i = 0;
		int k = A.length;
		while(i < A.length - 1){
			if(B[i] == k - 1){
				i++;
				k--;
				continue;
			}
			int f1 = B[i] + 1;
			for(int j = 0; j < B.length; j++){
				if(B[j] < f1){
					B[j] = f1 - B[j] - 1;
				}
			}
			for(int j = 0; j < B.length; j++){
				if(B[j] <= k - 1){
					B[j] = k - B[j] - 1;
				}
			}
			res.add(f1);
			res.add(k);
			i++;
			k--;
		}

		return res;
	}
}
