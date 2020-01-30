package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 09/19/2016.
 * #0293 https://leetcode.com/problems/flip-game/
 */
public class FlipGame {
	// time O(n^2) (counting the cost of arr to string)
	public List<String> generatePossibleNextMoves(String s) {
		List<String> res = new ArrayList<>();
		char[] arr = s.toCharArray();
		for(int i = 1; i < arr.length; i++){
			if(arr[i] == '+' && arr[i - 1] == '+'){
				arr[i] = '-';
				arr[i - 1] = '-';
				// time O(n)
				res.add(new String(arr));
				arr[i] = '+';
				arr[i - 1] = '+';
			}
		}
		return res;
	}
}
