package org.wshuai.leetcode;

/**
 * Created by Wei on 11/11/2019.
 * #0738 https://leetcode.com/problems/monotone-increasing-digits/
 */
public class MonotoneIncreasingDigits {
	// time O(n)
	public int monotoneIncreasingDigits(int N) {
		// 14521 -> 14499
		char[] arr = String.valueOf(N).toCharArray();
		int leftAsc = arr.length - 1;
		// find the leftmost desc pair
		for(int i = arr.length - 1; i > 0; i--){
			if(arr[i] < arr[i - 1]){
				leftAsc = i - 1;
				// decrease arr[i - 1] by 1 will maintain the property
				// if arr[i - 2] > arr[i - 1]
				arr[i - 1]--;
			}
		}
		for(int i = leftAsc + 1; i < arr.length; i++){
			arr[i] = '9';
		}
		return Integer.parseInt(new String(arr));
	}
}
