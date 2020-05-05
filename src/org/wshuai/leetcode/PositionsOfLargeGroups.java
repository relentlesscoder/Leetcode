package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 08/21/2019.
 * #0830 https://leetcode.com/problems/positions-of-large-groups/
 */
public class PositionsOfLargeGroups {
	// time O(n)
	public List<List<Integer>> largeGroupPositions(String S) {
		List<List<Integer>> res = new ArrayList<>();
		S += "#";
		int count = 0;
		char prev = S.charAt(0);
		char[] arr = S.toCharArray();
		for(int i = 0; i < arr.length; i++){
			if(arr[i] == prev){
				count++;
			}else{
				if(count >= 3){
					res.add(Arrays.asList(i - count, i - 1));
				}
				count = 1;
				prev = arr[i];
			}
		}
		return res;
	}
}
