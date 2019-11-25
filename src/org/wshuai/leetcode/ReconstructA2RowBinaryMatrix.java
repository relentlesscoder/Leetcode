package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/25/19.
 * #1253 https://leetcode.com/problems/reconstruct-a-2-row-binary-matrix/
 */
public class ReconstructA2RowBinaryMatrix {
	public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
		int N = colsum.length;
		int sum = 0;
		int bothOnes = 0;
		for(int i = 0; i < N; i++){
			if(colsum[i] == 2){
				bothOnes++;
			}
			sum += colsum[i];
		}
		if(sum != upper + lower){
			return new ArrayList<>();
		}
		List<List<Integer>> res = new ArrayList<>();
		res.add(new ArrayList<>());
		res.add(new ArrayList<>());
		for(int i = 0; i < N; i++){
			if(colsum[i] == 0){
				res.get(0).add(0);
				res.get(1).add(0);
			}else if(colsum[i] == 2){
				if(upper <= 0 || lower <= 0){
					return new ArrayList<>();
				}
				res.get(0).add(1);
				res.get(1).add(1);
				upper--;
				lower--;
				bothOnes--;
			// need to check if upper still has extra 1 to use
			}else if(upper > bothOnes){
				res.get(0).add(1);
				res.get(1).add(0);
				upper--;
			}else if(lower > bothOnes){
				res.get(0).add(0);
				res.get(1).add(1);
				lower--;
			}else{
				return new ArrayList<>();
			}
		}
		return res;
	}
}
