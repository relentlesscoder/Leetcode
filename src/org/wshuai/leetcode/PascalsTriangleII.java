package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 09/20/2016.
 * #0119 https://leetcode.com/problems/pascals-triangle-ii/
 */
public class PascalsTriangleII {
	// time O(n^2)
	public List<Integer> getRow(int rowIndex) {
		List<Integer> prev = new ArrayList<>();
		if(++rowIndex <= 0){
			return prev;
		}
		prev.add(1);
		if(rowIndex == 1){
			return prev;
		}
		for(int i = 2; i <= rowIndex; i++){
			List<Integer> cur = new ArrayList<>();
			cur.add(1);
			for(int j = 1; j < i - 1; j++){
				cur.add(prev.get(j - 1) + prev.get(j));
			}
			cur.add(1);
			prev = cur;
			cur = new ArrayList<>();
		}
		return prev;
	}
}
