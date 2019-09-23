package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/21/2016.
 * #264 https://leetcode.com/problems/ugly-number-ii/
 */
public class UglyNumberII {
	public int nthUglyNumber(int n) {
		if (n < 1) {
			return 0;
		}
		List<Integer> lst = new ArrayList<Integer>();
		lst.add(1);
		int i = 0;
		int j = 0;
		int k = 0;
		while (lst.size() < n) {
			int m1 = lst.get(i) * 2;
			int m2 = lst.get(j) * 3;
			int m3 = lst.get(k) * 5;

			int min = Math.min(m1, Math.min(m2, m3));
			lst.add(min);
			if (min == m1) {
				i++;
			}
			if (min == m2) {
				j++;
			}
			if (min == m3) {
				k++;
			}
		}
		return lst.get(lst.size() - 1);
	}
}
