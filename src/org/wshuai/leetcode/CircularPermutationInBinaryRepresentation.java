package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/7/19.
 * #1238 https://leetcode.com/problems/circular-permutation-in-binary-representation/
 */
public class CircularPermutationInBinaryRepresentation {
	// https://leetcode.com/problems/circular-permutation-in-binary-representation/discuss/414153/Java-AC-solution%3A-generate-%22one-bit-diff%22-list-then-make-it-start-from-%22start%22
	public List<Integer> circularPermutation(int n, int start) {
		List<Integer> res = new ArrayList<>();
		List<Integer> temp = oneBitDiffPermutation(n);

		int i = 0;
		for(; i < temp.size(); i++){
			if(temp.get(i) == start){
				break;
			}
		}

		for(int j = i; j < temp.size(); j++){
			res.add(temp.get(j));
		}
		for(int k = 0; k < i; k++){
			res.add(temp.get(k));
		}
		return res;
	}

	private List<Integer> oneBitDiffPermutation(int n){
		List<Integer> temp = new ArrayList<>();
		temp.add(0);
		if(n == 0){
			return temp;
		}
		for(int i = 0; i < n; i++){
			for(int j = temp.size() - 1; j >= 0; j--){
				temp.add(temp.get(j) + (1 << i));
			}
		}
		return temp;
	}
}
