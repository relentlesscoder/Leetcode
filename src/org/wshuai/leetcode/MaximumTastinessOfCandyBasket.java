package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/22/2023.
 * #2517 https://leetcode.com/problems/maximum-tastiness-of-candy-basket/
 */
public class MaximumTastinessOfCandyBasket {

	// time O(n * log(n) + n * log(max diff)), space O(1)
	public int maximumTastiness(int[] price, int k) {
		Arrays.sort(price);
		int n = price.length, low = 0, high = price[n - 1] - price[0];
		while (low < high) {
			int mid = (low + high + 1) >> 1, count = 1, prev = price[0];
			for (int i = 1; i < n; i++) {
				// Let's say you sort the prices : p1 , p2 , ... pn, for whatver the value of 'k' might be.
				// If you considering picking ,say, p2 and p4 in your basket, compare it to picking p1 and p4,
				// the absolute difference between p1 and p4 will be greater compared to p2 and p4. eg : 1 , 4 , 5 , 6.
				// The difference between (1,6) > (4,6) since they are in sorted order.
				// In order to maximize our difference, we should always pick the first element, and then the rest into the basket.
				if (price[i] - prev >= mid) {
					count++;
					prev = price[i];
				}
			}
			if (count >= k) {
				low = mid;
			} else {
				high = mid - 1;
			}
		}
		return low;
	}
}
