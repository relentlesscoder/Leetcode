package org.wshuai.leetcode;

/**
 * Created by Wei on 8/19/19.
 * #1089 https://leetcode.com/problems/duplicate-zeros/
 */
public class DuplicateZeros {
	public void duplicateZeros(int[] arr) {
		int len = arr.length;
		int i = 0;
		int sum = 0;
		while (true) {
			sum += arr[i] == 0 ? 2 : 1;
			// break here to avoid add a extra 1 to i
			if (sum >= len) {
				break;
			}
			i++;
		}
		int j = len - 1;
		// handle the case that array length is odd and the last number is 0
		if (sum > len && arr[i] == 0) {
			arr[j--] = 0;
			i--;
		}
		while (j >= 0) {
			if (arr[i] != 0) {
				arr[j--] = arr[i];
			} else {
				arr[j--] = 0;
				arr[j--] = 0;
			}
			i--;
		}
		return;
	}
}
