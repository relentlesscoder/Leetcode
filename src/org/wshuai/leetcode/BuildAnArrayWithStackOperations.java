package org.wshuai.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wei on 05/11/2020.
 * #1441 https://leetcode.com/problems/build-an-array-with-stack-operations/
 */
public class BuildAnArrayWithStackOperations {

	// time O(n)
	public List<String> buildArray(int[] target, int n) {
		LinkedList<String> res = new LinkedList<>();
		int m = target.length;
		for(int i = target[m - 1], j = m - 1; i >= 1; i--){
			if(j >= 0 && target[j] == i){
				j--;
			}else{
				res.offerFirst("Pop");
			}
			res.offerFirst("Push");
		}
		return res;
	}
}
