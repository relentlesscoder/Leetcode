package org.wshuai.algorithm.sorting;

import org.wshuai.utility.ArrayUtil;

/**
 * Created by Wei on 8/23/15.
 */
public class InsertionSort {
	public static void sort(Comparable[] array) {
		if (array == null || array.length <= 1) {
			return;
		}

		for (int i = 1; i < array.length; i++) {
			int j = i;
			Comparable value = array[j];
			while (j > 0 && ArrayUtil.less(value, array[j - 1])) {
				ArrayUtil.exchange(array, j, j - 1);
				j--;
			}
			array[j] = value;
		}
	}
}
