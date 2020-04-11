package org.wshuai.leetcode;

/**
 * Created by Wei on 09/26/2019.
 * #0670 https://leetcode.com/problems/maximum-swap/
 */
public class MaximumSwap {
	// time O(n)
	public int maximumSwap(int num) {
		char[] arr = Integer.toString(num).toCharArray();
		int n = arr.length;
		int[] index = new int[10];
		for(int i = 0; i < n; i++){
			// try to use the leftmost value to swap to get the maximum value
			// for 189269: 989261 > 981269
			index[arr[i] - '0'] = i;
		}
		for(int i = 0; i < n; i++){
			for(char j = '9'; j > arr[i]; j--){
				if(index[j - '0'] > i){
					arr[index[j - '0']] = arr[i];
					arr[i] = j;
					return Integer.parseInt(new String(arr));
				}
			}
		}
		return num;
	}
}
