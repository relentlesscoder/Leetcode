package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 01/11/2020.
 * #0089 https://leetcode.com/problems/gray-code/
 */
public class GrayCode {

	// time O(n^2)
	// https://leetcode.com/problems/gray-code/discuss/29891/Share-my-solution
	public List<Integer> grayCode(int n) {
		List<Integer> res = new ArrayList<>();
		for(int i = 0; i < (1 << n); i++){
			res.add(i ^ (i >> 1));
		}
		return res;
	}

	// time O(n^2)
	// https://leetcode.wang/leetCode-89-Gray-Code.html
	public List<Integer> grayCodeDP(int n) {
		List<Integer> res = new ArrayList<>();
		res.add(0);
		for(int i = 0; i < n; i++){
			int size = res.size();
			for(int j = size - 1; j >= 0; j--){
				res.add(res.get(j) | (1 << i));
			}
		}
		return res;
	}
}
