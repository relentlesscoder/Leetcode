package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 8/8/19.
 * #728 https://leetcode.com/problems/self-dividing-numbers/
 */
public class SelfDividingNumbers {
	public List<Integer> selfDividingNumbers(int left, int right) {
		List<Integer> res = new ArrayList<Integer>();
		for (int i = left; i <= right; i++) {
			boolean b = true;
			int j = i;
			while (j > 0) {
				int r = j % 10;
				if (r == 0 || i % r != 0) {
					b = false;
					break;
				}
				j = j / 10;
			}
			if (b) {
				res.add(i);
			}
		}
		return res;
	}
}
