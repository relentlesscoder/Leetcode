package org.wshuai.leetcode;

/**
 * Created by Wei on 12/12/2019.
 * #1231 https://leetcode.com/problems/divide-chocolate/
 */
public class DivideChocolate {

	// time O(n * log(avg(total sweetness) - min(sweetness))), space O(1)
	public int maximizeSweetness(int[] sweetness, int k) {
		int n = k + 1, low = (int) 1e5, high = 0;
		for (int sweet : sweetness) {
			low = Math.min(low, sweet);
			high += sweet;
		}
		high /= n;
		while (low < high) {
			int mid = low + (high - low + 1) / 2, sum = 0, assigned = 0; // mid = (left + right) / 2 to find first element valid, mid = (left + right + 1) / 2to find last element valid
			for (int sweet : sweetness) {
				sum += sweet;
				if (sum >= mid) {
					assigned++;
					sum = 0;
				}
			}
			if (assigned >= n) {
				low = mid;
			} else {
				high = mid - 1;
			}
		}
		return low;
	}
}
