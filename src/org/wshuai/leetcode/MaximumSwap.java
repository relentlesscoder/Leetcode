package org.wshuai.leetcode;

/**
 * Created by Wei on 09/26/2019.
 * #0670 https://leetcode.com/problems/maximum-swap/
 */
public class MaximumSwap {

	// time O(n)
	public int maximumSwap(int num) {
		int[] map = new int[10];
		char[] arr = Integer.toString(num).toCharArray();
		for(int i = 0; i < arr.length; i++){
			map[arr[i] - '0'] = i;
		}
		for(int i = 0; i < arr.length; i++){
			int cur = arr[i] - '0';
			for(int j = 9; j > cur; j--){
				if(map[j] > i){
					arr[map[j]] = arr[i];
					arr[i] = (char)('0' + j);
					return Integer.parseInt(String.valueOf(arr));
				}
			}
		}
		return num;
	}
}
