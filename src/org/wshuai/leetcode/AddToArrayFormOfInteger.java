package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Wei on 8/20/19.
 * #989 https://leetcode.com/problems/add-to-array-form-of-integer/
 */
public class AddToArrayFormOfInteger {
	public List<Integer> addToArrayForm(int[] A, int K) {
		List<Integer> lst = new ArrayList<>();
		int c = 0;
		int j = A.length - 1;
		while (j >= 0 || c > 0 || K != 0) {
			int sum = (j >= 0 ? A[j] : 0) + (K != 0 ? K % 10 : 0) + c;
			lst.add(sum % 10);
			c = sum / 10;
			j--;
			K /= 10;
		}
		Collections.reverse(lst);
		return lst;
	}
}
