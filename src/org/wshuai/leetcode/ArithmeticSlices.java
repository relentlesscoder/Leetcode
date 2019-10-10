package org.wshuai.leetcode;

/**
 * Created by Wei on 10/31/16.
 * #413 https://leetcode.com/problems/arithmetic-slices/
 */
public class ArithmeticSlices {
	public int numberOfArithmeticSlices(int[] A) {
		if (A == null || A.length <= 2) {
			return 0;
		}

		int count = 0;
		int len = A.length;
		int[] aux = new int[len];
		for (int i = 0; i < len - 1; i++) {
			aux[i] = A[i + 1] - A[i];
		}
		//Add a different tail element to finish the last count
		aux[len - 1] = aux[len - 2] + 1;
		int c = 1;
		for (int i = 1; i < len; i++) {
			if (aux[i] == aux[i - 1]) {
				c++;
			} else {
				if (c > 1) {
					count += numberOfArithmeticSlicesUtil(c + 1);
				}
				c = 1;
			}
		}
		return count;
	}

	private int numberOfArithmeticSlicesUtil(int cnt) {
		int count = 0;
		int i = 1;
		while (cnt >= 3) {
			count += i;
			i++;
			cnt--;
		}
		return count;
	}
}
