package org.wshuai.leetcode;

/**
 * Created by Wei on 9/26/19.
 * #670 https://leetcode.com/problems/maximum-swap/
 */
public class MaximumSwap {
	public int maximumSwap(int num) {
		char[] arr = Integer.toString(num).toCharArray();
		int[] count = new int[10];
		for(int i = 0; i < arr.length; i++){
			// try to use the leftmost value to swap to get the maximum value
			// for 189269: 989261 > 981269
			count[arr[i]-'0'] = i;
		}
		for(int i = 0; i < arr.length; i++){
			for(int j = 9; j > arr[i] - '0'; j--){
				if(count[j] > i){
					char temp = arr[i];
					arr[i] = arr[count[j]];
					arr[count[j]] = temp;
					return Integer.parseInt(new String(arr));
				}
			}
		}
		return num;
	}
}
