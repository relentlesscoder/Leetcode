package org.wshuai.leetcode;

/**
 * Created by Wei on 04/05/2020.
 * #1399 https://leetcode.com/problems/count-largest-group/
 */
public class CountLargestGroup {
	// time O(n*d)
	public int countLargestGroup(int n) {
		int[] count = new int[37];
		int res = 0, max = 0;
		for(int i = 1; i <= n; i++){
			int dsum = digitSum(i);
			max = Math.max(max, ++count[dsum]);
		}
		for(int i = 1; i <= 36; i++){
			res += count[i] == max ? 1 : 0;
		}
		return res;
	}

	private int digitSum(int n){
		int res = 0;
		while(n > 0){
			res += n % 10;
			n /= 10;
		}
		return res;
	}
}
