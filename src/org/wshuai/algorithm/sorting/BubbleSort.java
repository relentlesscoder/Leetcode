package org.wshuai.algorithm.sorting;

import org.wshuai.utility.ArrayUtil;

/**
 * Created by Wei on 8/23/15.
 */
public class BubbleSort {
	public static void sort(Comparable[] array) {
		if (array == null || array.length <= 1) {
			return;
		}

		for (int i = array.length - 1; i >= 0; i--) {
			for (int j = 1; j <= i; j++) {
				if (ArrayUtil.less(array[j], array[j - 1])) {
					ArrayUtil.exchange(array, j, j - 1);
				}
			}
		}
	}
}
