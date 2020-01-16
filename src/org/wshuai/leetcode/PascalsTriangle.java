package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 01/15/2020.
 * #0118 https://leetcode.com/problems/pascals-triangle/
 */
public class PascalsTriangle {
	// time O(n^2)
	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> res = new ArrayList<>();
		if(numRows <= 0){
			return res;
		}
		List<Integer> prev = new ArrayList<>();
		prev.add(1);
		res.add(prev);
		for(int i = 2; i <= numRows; i++){
			List<Integer> cur = new ArrayList<>();
			cur.add(1);
			for(int j = 1; j < i - 1; j++){
				cur.add(prev.get(j - 1) + prev.get(j));
			}
			cur.add(1);
			res.add(cur);
			prev = cur;
		}
		return res;
	}
}
