package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/29/2023.
 * #2094 https://leetcode.com/problems/finding-3-digit-even-numbers/
 */
public class Finding3DigitEvenNumbers {

	// time O(n), space O(n)
	public int[] findEvenNumbers(int[] digits) {
		int[] counter = new int[10];
		for (int d : digits) {
			counter[d]++;
		}
		List<Integer> list = new ArrayList<>();
		for (int i = 100; i < 1_000; i += 2) {
			int d0 = i % 10, d1 = (i / 10) % 10, d2 = i / 100;
			counter[d0]--;
			counter[d1]--;
			counter[d2]--;
			if (counter[d0] >= 0 && counter[d1] >= 0 && counter[d2] >= 0) {
				list.add(i);
			}
			counter[d0]++;
			counter[d1]++;
			counter[d2]++;
		}
		int[] res = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			res[i] = list.get(i);
		}
		return res;
	}
}
