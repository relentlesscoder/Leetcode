package org.wshuai.leetcode;

/**
 * Created by Wei on 01/24/2017.
 * #0481 https://leetcode.com/problems/magical-string/
 */
public class MagicalString {
	// time O(n), space O(n)
	public int magicalString(int n) {
		if(n <= 0){
			return 0;
		}
		if(n <= 3){
			return 1;
		}
		int[] arr = new int[n + 1];
		arr[1] = 1;
		arr[2] = 2;
		arr[3] = 2;
		int res = 1, i = 3, j = 4;
		while(j <= n){
			int count = arr[i++];
			int val = (arr[j - 1] ^ 3);
			while(count-- > 0 && j <= n){
				arr[j++] = val;
				res += val == 1 ? 1 : 0;
			}
		}
		return res;
	}
}
