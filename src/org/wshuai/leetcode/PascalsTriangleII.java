package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 9/20/2016.
 */
public class PascalsTriangleII {
	public List<Integer> getRow(int rowIndex) {
		List<Integer> lst = new ArrayList<Integer>();
		if (rowIndex < 0) {
			return lst;
		}
		int[] num = new int[rowIndex + 1];
		for (int i = 0; i < rowIndex + 1; i++) {
			int last = 0;
			for (int j = 0; j <= i; j++) {
				if (j == 0 || j == i) {
					last = 1;
					num[j] = 1;
				} else {
					int temp = num[j] + last;
					last = num[j];
					num[j] = temp;
				}
			}
		}
		for (int i = 0; i < rowIndex + 1; i++) {
			lst.add(num[i]);
		}
		return lst;
	}
}
