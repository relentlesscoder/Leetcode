package org.wshuai.leetcode;

/**
 * Created by Wei on 02/24/2020.
 * #1359 https://leetcode.com/problems/count-all-valid-pickup-and-delivery-options/
 */
public class CountAllValidPickupAndDeliveryOptions {
	private static final int mod = 1_000_000_007;

	// https://leetcode.com/problems/count-all-valid-pickup-and-delivery-options/discuss/516968/JavaC%2B%2BPython-Easy-and-Concise
	public int countOrders(int n) {
		long res = 1;
		for(int i = 1; i <= n; i++){
			res = res * (2 * i - 1) * i % mod;
		}
		return (int)res;
	}
}
