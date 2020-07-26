package org.wshuai.leetcode;

/**
 * Created by Wei on 07/26/2020.
 * #1528 https://leetcode.com/problems/shuffle-string/
 */
public class ShuffleString {

	// time O(n), space O(n)
	public String restoreString(String s, int[] indices) {
		char[] arr = s.toCharArray();
		for(int i = 0; i < indices.length; i++){
			while(indices[i] != i){
				int swap = indices[i];
				char c = arr[swap];
				arr[swap] = arr[i];
				arr[i] = c;
				int temp = indices[swap];
				indices[swap] = swap;
				indices[i] = temp;
			}
		}
		return new String(arr);
	}
}
