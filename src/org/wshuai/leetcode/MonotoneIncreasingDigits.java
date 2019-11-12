package org.wshuai.leetcode;

/**
 * Created by Wei on 11/11/2019.
 * #738 https://leetcode.com/problems/monotone-increasing-digits/
 */
public class MonotoneIncreasingDigits {
	// 14521 -> 14499
	public int monotoneIncreasingDigits(int N) {
		char[] arr = String.valueOf(N).toCharArray();
		int leftAsc = arr.length - 1;
		for(int i = arr.length - 1; i > 0; i--){
			if(arr[i] < arr[i - 1]){
				leftAsc = i - 1;
				arr[i - 1]--;
			}
		}
		for(int i = leftAsc + 1; i < arr.length; i++){
			arr[i] = '9';
		}
		return Integer.parseInt(new String(arr));
	}
}
