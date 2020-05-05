package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 08/10/2019.
 * #0821 https://leetcode.com/problems/shortest-distance-to-a-character/
 */
public class ShortestDistanceToACharacter {
	// time O(n)
	public int[] shortestToChar(String S, char C) {
		char[] arr = S.toCharArray();
		int n = arr.length;
		int[] res = new int[n];
		Arrays.fill(res, Integer.MAX_VALUE);
		for(int i = 0; i < n; i++){
			if(arr[i] != C){
				continue;
			}
			res[i] = 0;
			for(int j = i - 1, k = 0; j >= 0 && arr[j] != C; j--){
				res[j] = Math.min(res[j], ++k);
			}
			for(int j = i + 1, k = 0; j < n && arr[j] != C; j++){
				res[j] = Math.min(res[j], ++k);
			}
		}
		return res;
	}
}
