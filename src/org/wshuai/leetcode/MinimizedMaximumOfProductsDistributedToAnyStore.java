package org.wshuai.leetcode;

/**
 * Created by Wei on 09/21/2023.
 * #2064 https://leetcode.com/problems/minimized-maximum-of-products-distributed-to-any-store/
 */
public class MinimizedMaximumOfProductsDistributedToAnyStore {

	// time O(n * log(10^5)), space O(1)
	public int minimizedMaximum(int n, int[] quantities) {
		int left = 1, right = 100_000;
		while (left < right) {
			int mid = left + (right - left) / 2, store = 0;
			for (int q : quantities) {
				store += (q + mid - 1) / mid; // get the ceil of (q / mid)
			}
			if (store <= n) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return right;
	}
}
