package org.wshuai.leetcode;

/**
 * Created by Wei on 10/24/19.
 * #667 https://leetcode.com/problems/beautiful-arrangement-ii/
 */
public class BeautifulArrangementII {
	// beautiful solution -
	// https://leetcode.com/problems/beautiful-arrangement-ii/discuss/106948/C%2B%2B-Java-Clean-Code-4-liner
	public int[] constructArray(int n, int k) {
		int[] res = new int[n];
		for(int i = 0, l = 1, r = n; l <= r; i++){
			res[i] = k > 1 ? (k-- % 2 != 0 ? l++ : r--) : l++;
		}
		return res;
	}
}
