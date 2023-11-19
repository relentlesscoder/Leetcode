package org.wshuai.leetcode;

/**
 * Created by Wei on 12/05/2020.
 * #1672 https://leetcode.com/problems/richest-customer-wealth/
 */
public class RichestCustomerWealth {

	// time O(m*n)
	public int maximumWealth(int[][] accounts) {
		int max = 0, m = accounts.length, n = accounts[0].length;
		for(int i = 0; i < m; i++){
			int sum = 0;
			for(int j = 0; j < n; j++){
				sum += accounts[i][j];
			}
			max = Math.max(max, sum);
		}
		return max;
	}
}
