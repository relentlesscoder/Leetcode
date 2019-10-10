package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 11/21/2016.
 * #313 https://leetcode.com/problems/super-ugly-number/
 */
public class SuperUglyNumber {
	//Same idea as https://leetcode.com/problems/ugly-number-ii/
	public int nthSuperUglyNumber(int n, int[] primes) {
		if (n < 1 || primes == null || primes.length == 0) {
			return 0;
		}
		int len = primes.length;
		int[] idxs = new int[len];
		int[] vals = new int[len];
		Arrays.fill(idxs, 0);
		List<Integer> lst = new ArrayList<Integer>();
		lst.add(1);
		while (lst.size() < n) {
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < len; i++) {
				int m = lst.get(idxs[i]) * primes[i];
				min = m < min ? m : min;
				vals[i] = m;
			}
			lst.add(min);
			for (int i = 0; i < len; i++) {
				if (vals[i] == min) {
					idxs[i]++;
				}
			}
		}
		return lst.get(lst.size() - 1);
	}
}
